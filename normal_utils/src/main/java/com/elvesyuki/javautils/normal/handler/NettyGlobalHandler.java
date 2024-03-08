package com.elvesyuki.javautils.normal.handler;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：luohuan
 * @date ：Created in 2024/3/8 16:08
 * @description：
 * @modified By：
 */
public class NettyGlobalHandler {

    /**
     * 定义一个channel组，管理所有的channel GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
     */
    private static ChannelGroup globalChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 存放通道和通道对应信息，用户寻找指定通道信息
     */
    private static ConcurrentHashMap<String, Channel> globalChannelMap = new ConcurrentHashMap<>();

    private NettyGlobalHandler() {
    }

    /**
     * 获取channel组
     * @return
     */
    public static ChannelGroup getChannelGroup() {
        return globalChannelGroup;
    }

    public static void addChannel(Channel channel) {
        globalChannelGroup.add(channel);
        globalChannelMap.put(channel.id().asLongText(), channel);
    }

    public static void removeChannel(Channel channel) {
        globalChannelGroup.remove(channel);
        globalChannelMap.remove(channel.id().asLongText());
    }

    public static Channel findChannel(String id) {
        return globalChannelMap.get(id);
    }

    public static void send2All(TextWebSocketFrame tws) {
        getChannelGroup().writeAndFlush(tws);
    }

}
