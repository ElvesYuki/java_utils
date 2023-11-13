package com.elvesyuki.test;

import com.alibaba.excel.EasyExcel;
import com.elvesyuki.javautils.web.WebApplication;
import com.elvesyuki.javautils.web.object.ExamData;
import com.elvesyuki.javautils.web.listener.ExamExcelListener;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LuoHuan
 * @date 2021/7/23
 */
@RunWith(SpringRunner.class)
// @SpringBootTest(classes = WebApplication.class)
public class otherUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(otherUtilsTest.class);

    @Test
    public void test01() {
        logger.info("test01");
    }

    @Test
    public void testLocalTime() {

        LocalTime localTime = LocalTime.now();
//        Instant.now()

        logger.info(String.valueOf(localTime.getNano()));
    }


    @Test
    public void testpp() {
        String fileName = "D:\\坪山区_PS_坪山外国语学校文源校区_008 (1).xlsx";
        EasyExcel.read(fileName, ExamData.class, new ExamExcelListener()).sheet().doRead();
    }


    @Test
    public void testIOServer() {

        try {
            ServerSocket serverSocket = new ServerSocket(18000);

            // 接受新连接线程
            new Thread(() -> {
                while (true) {
                    try {

                        // 阻塞方法获取新连接
                        Socket socket = serverSocket.accept();

                        // 为每一个新连接都创建一个新线程，负责读取数据
                        new Thread(() -> {
                            try {
                                int len;
                                byte[] data = new byte[1024];
                                InputStream inputStream = socket.getInputStream();
                                // 按字节流方式读取数据
                                while ((len = inputStream.read(data)) != -1) {
                                    logger.info(new String(data, 0 ,len));
                                }
                            } catch (Exception e) {
                                logger.error(e.getMessage());
                            }

                        }).start();

                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
            }).start();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        try {
            Thread.sleep(2000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testIOClient() {

        try {

            new Thread(() -> {

                try {
                    Socket socket = new Socket("127.0.0.1", 18000);
                    while (true) {
                        try {

                            socket.getOutputStream().write((new Date() + ":Hello World").getBytes(StandardCharsets.UTF_8));
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }).start();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void testNIOServer() {

        try {
            Selector serverSelector = Selector.open();
            Selector clientSelector = Selector.open();

            new Thread(() -> {
                try {
                    // 对应IO编程中的服务端启动
                    ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                    listenerChannel.socket().bind(new InetSocketAddress(18000));
                    listenerChannel.configureBlocking(false);
                    listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                    while (true) {
                        // 检测是否有新连接，这里的1指阻塞的时间为1ms
                        try {
                            if (serverSelector.select(1) > 0) {
                                Set<SelectionKey> set = serverSelector.selectedKeys();
                                Iterator<SelectionKey> keyIterator = set.iterator();
                                while (keyIterator.hasNext()) {
                                    SelectionKey key = keyIterator.next();
                                    if (key.isAcceptable()) {
                                        try {
                                            // 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                            SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                            clientChannel.configureBlocking(false);
                                            clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                        } catch (Exception e) {
                                            logger.error(e.getMessage());
                                        } finally {
                                            keyIterator.remove();
                                        }
                                    }

                                }
                            }

                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }

                } catch (Exception e) {
                    logger.error(e.getMessage());
                }

            }).start();


            new Thread(() -> {
                try {

                    while (true) {
                        // 批量轮询哪些链接有数据可读，这里的1指阻塞时间为1ms
                        // 检测是否有新连接，这里的1指阻塞的时间为1ms
                        try {
                            if (clientSelector.select(1) > 0) {
                                Set<SelectionKey> set = clientSelector.selectedKeys();
                                Iterator<SelectionKey> keyIterator = set.iterator();
                                while (keyIterator.hasNext()) {
                                    SelectionKey key = keyIterator.next();
                                    if (key.isAcceptable()) {
                                        try {
                                            SocketChannel clientChannel = (SocketChannel) key.channel();
                                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                            // 面向buffer
                                            clientChannel.read(byteBuffer);
                                            byteBuffer.flip();
                                            logger.info(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                        } catch (Exception e) {
                                            logger.error(e.getMessage());
                                        } finally {
                                            keyIterator.remove();
                                            key.interestOps(SelectionKey.OP_READ);
                                        }
                                    }

                                }
                            }

                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }

                } catch (Exception e) {
                    logger.error(e.getMessage());
                }

            }).start();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }



        try {
            Thread.sleep(2000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void NettyServer() {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                logger.info(msg);
                            }
                        });
                    }
                }).bind(18000);

        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void NettyClient() {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        Channel channel = bootstrap.connect("127.0.0.1", 18000).channel();
        while (true) {
            channel.writeAndFlush(new Date() + ":HelloWorld!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
