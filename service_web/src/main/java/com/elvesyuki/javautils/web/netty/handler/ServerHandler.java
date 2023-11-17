package com.elvesyuki.javautils.web.netty.handler;

import com.elvesyuki.javautils.web.netty.packet.LoginResponsePacket;
import com.elvesyuki.javautils.web.netty.packet.LoginRequestPacket;
import com.elvesyuki.javautils.web.netty.packet.Packet;
import com.elvesyuki.javautils.web.netty.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：luohuan
 * @date ：Created in 2023/11/16 16:27
 * @description：
 * @modified By：
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodeC.getInstance().decode(requestByteBuf);

        // 判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 登录校验
            if (valid(loginRequestPacket)) {
                // 校验成功
                loginResponsePacket.setSuccess(true);
            } else {
                // 校验失败
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败");
            }
            // 编码
            ByteBuf responseByteBuf = PacketCodeC.getInstance().encode(loginResponsePacket);

            ctx.channel().writeAndFlush(responseByteBuf);

        }


    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
