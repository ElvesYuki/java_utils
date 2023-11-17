package com.elvesyuki.javautils.web.netty.handler;

import com.elvesyuki.javautils.web.netty.packet.LoginRequestPacket;
import com.elvesyuki.javautils.web.netty.packet.LoginResponsePacket;
import com.elvesyuki.javautils.web.netty.packet.Packet;
import com.elvesyuki.javautils.web.netty.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author ：luohuan
 * @date ：Created in 2023/11/16 16:27
 * @description：
 * @modified By：
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(Long.valueOf(System.currentTimeMillis()).intValue());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        // 编码
        ByteBuf byteBuf = PacketCodeC.getInstance().encode(loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.getInstance().decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.getSuccess()) {
                logger.info(new Date() + ":客户端登陆成功");
            } else {
                logger.info(new Date() + ":客户端登录失败，原因:" + loginResponsePacket.getReason());
            }

        }


    }
}
