package com.elvesyuki.javautils.normal.utils;

import com.elvesyuki.javautils.normal.dto.XmoException;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ：luohuan
 * @date ：Created in 2021/7/2 上午10:25
 * @description：
 * @modified By：
 */
@Component
public class HashUtils {

    /**
     * Returns MD5 hash of given data and it's length.1
     *
     * @param data must be {@link RandomAccessFile}, {@link BufferedInputStream} or byte array.
     * @param len length of data to be read for hash calculation.
     */
    public static String md5Hash(Object data, int len)
            throws NoSuchAlgorithmException, IOException {

        if (data != null
                && !(data instanceof InputStream
                || data instanceof RandomAccessFile
                || data instanceof byte[])) {
            byte[] bytes;
            if (data instanceof CharSequence) {
                bytes = data.toString().getBytes(StandardCharsets.UTF_8);
            } else {
                bytes = HashXmlUtils.marshal(data).getBytes(StandardCharsets.UTF_8);
            }

            data = bytes;
            len = bytes.length;
        }

        MessageDigest md5Digest = MessageDigest.getInstance("MD5");

        if (data instanceof BufferedInputStream || data instanceof RandomAccessFile) {
            updateDigests(data, len, null, md5Digest);
        } else if (data instanceof byte[]) {
            md5Digest.update((byte[]) data, 0, len);
        } else {
            throw new XmoException(
                    "Unknown data source to calculate sha256 hash. This should not happen.");
        }

//        return BaseEncoding.base64().encode(md5Digest.digest());
        return byteArrToHex(md5Digest.digest());
    }

    /**
     * Returns MD5 hash of given data and it's length.
     *
     * @param data must be {@link RandomAccessFile}, {@link BufferedInputStream} or byte array.
     * @param len length of data to be read for hash calculation.
     */
    public static String sha256Hash(Object data, int len)
            throws NoSuchAlgorithmException, IOException {

        if (data != null
                && !(data instanceof InputStream
                || data instanceof RandomAccessFile
                || data instanceof byte[])) {
            byte[] bytes;
            if (data instanceof CharSequence) {
                bytes = data.toString().getBytes(StandardCharsets.UTF_8);
            } else {
                bytes = HashXmlUtils.marshal(data).getBytes(StandardCharsets.UTF_8);
            }

            data = bytes;
            len = bytes.length;
        }

        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

        if (data instanceof BufferedInputStream || data instanceof RandomAccessFile) {
            updateDigests(data, len, sha256Digest, null);
        } else if (data instanceof byte[]) {
            sha256Digest.update((byte[]) data, 0, len);
        } else {
            throw new XmoException(
                    "Unknown data source to calculate sha256 hash. This should not happen.");
        }

//        return BaseEncoding.base64().encode(md5Digest.digest());
        return byteArrToHex(sha256Digest.digest());
    }

    /**
     * Returns MD5 hash of given data and it's length.
     *
     * @param data must be {@link RandomAccessFile}, {@link BufferedInputStream} or byte array.
     * @param len length of data to be read for hash calculation.
     */
    public static String sha1Hash(Object data, int len)
            throws NoSuchAlgorithmException, IOException {

        if (data != null
                && !(data instanceof InputStream
                || data instanceof RandomAccessFile
                || data instanceof byte[])) {
            byte[] bytes;
            if (data instanceof CharSequence) {
                bytes = data.toString().getBytes(StandardCharsets.UTF_8);
            } else {
                bytes = HashXmlUtils.marshal(data).getBytes(StandardCharsets.UTF_8);
            }

            data = bytes;
            len = bytes.length;
        }

        MessageDigest md5Digest = MessageDigest.getInstance("SHA-1");

        if (data instanceof BufferedInputStream || data instanceof RandomAccessFile) {
            updateDigests(data, len, null, md5Digest);
        } else if (data instanceof byte[]) {
            md5Digest.update((byte[]) data, 0, len);
        } else {
            throw new XmoException(
                    "Unknown data source to calculate sha256 hash. This should not happen.");
        }

//        return BaseEncoding.base64().encode(md5Digest.digest());
        return byteArrToHex(md5Digest.digest());
    }

    /** Updated MessageDigest with bytes read from file and stream. */
    private static int updateDigests(
            Object inputStream, int len, MessageDigest sha256Digest, MessageDigest md5Digest)
            throws IOException {
        RandomAccessFile file = null;
        BufferedInputStream stream = null;
        if (inputStream instanceof RandomAccessFile) {
            file = (RandomAccessFile) inputStream;
        } else if (inputStream instanceof BufferedInputStream) {
            stream = (BufferedInputStream) inputStream;
        }

        // hold current position of file/stream to reset back to this position.
        long pos = 0;
        if (file != null) {
            pos = file.getFilePointer();
        } else {
            stream.mark(len);
        }

        // 16KiB buffer for optimization
        byte[] buf = new byte[16384];
        int bytesToRead = buf.length;
        int bytesRead = 0;
        int totalBytesRead = 0;
        while (totalBytesRead < len) {
            if ((len - totalBytesRead) < bytesToRead) {
                bytesToRead = len - totalBytesRead;
            }

            if (file != null) {
                bytesRead = file.read(buf, 0, bytesToRead);
            } else {
                bytesRead = stream.read(buf, 0, bytesToRead);
            }

            if (bytesRead < 0) {
                // reached EOF
                throw new XmoException(
                        "Insufficient data.  bytes read " + totalBytesRead + " expected " + len);
            }

            if (bytesRead > 0) {
                if (sha256Digest != null) {
                    sha256Digest.update(buf, 0, bytesRead);
                }

                if (md5Digest != null) {
                    md5Digest.update(buf, 0, bytesRead);
                }

                totalBytesRead += bytesRead;
            }
        }

        // reset back to saved position.
        if (file != null) {
            file.seek(pos);
        } else {
            stream.reset();
        }

        return totalBytesRead;
    }

    /**
     * 数组转16进制字符串
     * @param btArr
     * @return
     */
    private static String byteArrToHex(byte[] btArr) {
        char[] HexCharArr = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        char[] strArr = new char[btArr.length * 2];
        int i = 0;
        for (byte bt : btArr) {
            strArr[i++] = HexCharArr[bt>>>4 & 0xf];
            strArr[i++] = HexCharArr[bt & 0xf];
        }
        return new String(strArr);
    }

}
