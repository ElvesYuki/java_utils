package com.elvesyuki.javautils.normal.config;

import com.elvesyuki.javautils.normal.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ：luohuan
 * @date ：Created in 2024/3/8 16:05
 * @description：
 * @modified By：
 */
public class WebSocketConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    /**
     * webSocket协议名
     */
    private static final String WEBSOCKET_PROTOCOL = "WebSocket";

    private int port = 9014;

    private String path = "/home";

    private EventLoopGroup bossGroup;

    private EventLoopGroup workGroup;

    @PostConstruct
    public void init() {
        // 需要开启一个新的线程来执行netty server 服务器
        CompletableFuture.runAsync(this::start);
    }

    @PreDestroy
    public void destroy() {
        if (bossGroup != null) {
            try {
                bossGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (workGroup != null) {
            try {
                workGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void start() {
        // bossGroup就是parentGroup，是负责处理TCP/IP连接的
        bossGroup = new NioEventLoopGroup();
        // workerGroup就是childGroup,是负责处理Channel(通道)的I/O事件
        workGroup = new NioEventLoopGroup();

        ServerBootstrap sb = new ServerBootstrap();
        sb.option(ChannelOption.SO_BACKLOG, 1024);
        sb.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        logger.info("收到新连接:{}", ch.localAddress());
                        // HttpServerCodec：将请求和应答消息解码为HTTP消息
                        ch.pipeline().addLast(new HttpServerCodec());
                        // ChunkedWriteHandler：向客户端发送HTML5文件
                        ch.pipeline().addLast(new ChunkedWriteHandler());
                        // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
                        ch.pipeline().addLast(new HttpObjectAggregator(8192));
                        ch.pipeline().addLast(new WebSocketServerProtocolHandler(path, WEBSOCKET_PROTOCOL, true, 655350));
                        // 进行设置心跳检测
                        ch.pipeline().addLast(new IdleStateHandler(60, 30, 1800, TimeUnit.SECONDS));
                        // 配置通道处理 来进行业务处理
                        ch.pipeline().addLast(new WebSocketHandler());
                    }
                });

        // 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
        try {
            ChannelFuture channelFuture = sb.bind(port).sync();
            logger.info("Netty服务启动成功，端口==》{}", port);
            logger.info("Server started and listen on:{}", channelFuture.channel().localAddress());
            // 成功绑定到端口之后,给channel增加一个 管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程。
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            logger.error("Netty服务启动失败");
        }


    }

}
