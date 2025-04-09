package com.example.utils

import java.io.ByteArrayOutputStream
import java.io.BufferedOutputStream
import java.util.zip.GZIPOutputStream

fun compressString(data: String): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    BufferedOutputStream(byteArrayOutputStream).use { bufferedOutput ->
        GZIPOutputStream(bufferedOutput).use { gzip ->
            gzip.write(data.toByteArray(Charsets.UTF_8))
        }
    }
    return byteArrayOutputStream.toByteArray()
}