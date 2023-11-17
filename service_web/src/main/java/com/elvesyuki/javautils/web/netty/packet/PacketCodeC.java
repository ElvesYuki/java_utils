package com.elvesyuki.javautils.web.netty.packet;

import com.elvesyuki.javautils.web.netty.serialize.JSONSerializer;
import com.elvesyuki.javautils.web.netty.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author ：luohuan
 * @date ：Created in 2023/11/16 16:46
 * @description：
 * @modified By：
 */
public class PacketCodeC {

    private static PacketCodeC INSTANCE;

    private PacketCodeC() {
    }

    public static PacketCodeC getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PacketCodeC();
        }
        return INSTANCE;
    }

    private static final int MAGIC_NUMBER = 12345678;

    public ByteBuf encode(Packet packet) {
        // 1.创建ByteBuf对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2.序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3.实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        int readInt = byteBuf.readInt();

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        JSONSerializer jsonSerializer = new JSONSerializer();
        Class<? extends Packet> packetType = getPacketType(command);

        Packet packet = jsonSerializer.deserialize(bytes, packetType);

        return packet;
    }

    public static Class<? extends Packet> getPacketType(byte command) {

        if (Command.LOGIN_REQUEST.equals(command)) {
            return LoginRequestPacket.class;
        } else if (Command.LOGIN_RESPONSE.equals(command)) {
            return LoginResponsePacket.class;
        } else {
            return null;
        }
    }

}
