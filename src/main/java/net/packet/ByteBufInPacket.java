package net.packet;

import io.netty.buffer.ByteBuf;

import java.awt.*;

public class ByteBufInPacket implements InPacket {
    private final ByteBuf byteBuf;

    public ByteBufInPacket(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    @Override
    public byte[] getBytes() {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        int readerIndex = byteBuf.readerIndex();
        byteBuf.getBytes(readerIndex, bytes);
        return bytes;
    }

    @Override
    public byte readByte() {
        return byteBuf.readByte();
    }

    @Override
    public short readShort() {
        return byteBuf.readShortLE();
    }

    @Override
    public int readInt() {
        return byteBuf.readIntLE();
    }

    @Override
    public long readLong() {
        return byteBuf.readLongLE();
    }

    @Override
    public Point readPoint() {
        final short x = byteBuf.readShortLE();
        final short y = byteBuf.readShortLE();
        return new Point(x, y);
    }

    @Override
    public String readString() {
        short length = readShort();
        char[] characters = new char[length];
        for(int i = 0; i < length; i++) {
            characters[i] = (char) readByte();
        }

        return String.valueOf(characters);
    }

    @Override
    public byte[] readBytes(int numberOfBytes) {
        byte[] bytes = new byte[numberOfBytes];
        byteBuf.readBytes(bytes);
        return bytes;
    }

    @Override
    public void skip(int numberOfBytes) {
        byteBuf.skipBytes(numberOfBytes);
    }

    @Override
    public int available() {
        return byteBuf.readableBytes();
    }

    @Override
    public void seek(int byteOffset) {
        byteBuf.readerIndex(byteOffset);
    }

    @Override
    public int getPosition() {
        return byteBuf.readerIndex();
    }
}
