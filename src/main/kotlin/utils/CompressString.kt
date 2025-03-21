package com.example.utils

import org.apache.commons.io.output.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

fun compressString(data: String): ByteArray {
    val bos = ByteArrayOutputStream()
    GZIPOutputStream(bos).use { gzip ->
        gzip.write(data.toByteArray(Charsets.UTF_8))
    }
    return bos.toByteArray()
}