package com.hackthe6ix.proprice.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

public class CompressUtils {

    public static byte[] deflate(byte[] encoded_bytes) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Deflater compresser = new Deflater(Deflater.BEST_COMPRESSION, true);
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(stream, compresser);
        deflaterOutputStream.write(encoded_bytes);
        deflaterOutputStream.close();

        return stream.toByteArray();
    }

    public static byte[] inflate(byte[] encoded_bytes) throws IOException {
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        Inflater decompresser = new Inflater(true);
        InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(stream2, decompresser);
        inflaterOutputStream.write(encoded_bytes);
        inflaterOutputStream.close();

        return stream2.toByteArray();
    }
}
