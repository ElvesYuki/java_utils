package com.elvesyuki.javautils.normal.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：luohuan
 * @date ：Created in 2024/3/8 16:07
 * @description：
 * @modified By：
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("建立新连接，{}", ctx.channel().id().asLongText());
        NettyGlobalHandler.addChannel(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("销毁连接，{}", ctx.channel().id().asLongText());
        NettyGlobalHandler.removeChannel(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        logger.info("服务器收到消息：{}", msg.text());
        // ctx.channel().writeAndFlush(new TextWebSocketFrame("收到消息:" + msg.text()));
        NettyGlobalHandler.getChannelGroup().writeAndFlush(new TextWebSocketFrame("收到消息:" + msg.text()));

    }
}