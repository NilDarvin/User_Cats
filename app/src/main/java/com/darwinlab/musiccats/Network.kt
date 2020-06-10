package com.darwinlab.musiccats

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.io.IOUtils
import java.io.InputStream
import java.io.StringWriter
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

suspend fun getPlayList(): String {
        //Работаем в фоновом процессе ибо не используем View
        return withContext(Dispatchers.IO) {
            try {
            val inputStream: InputStream
            val result: String
            val url: URL = URL("https://api.deezer.com/user/2529/playlists")
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                conn.connect()

                // receive response as inputStream
                inputStream = conn.inputStream
                result = if (inputStream != null)
                    convertInputStreamToString(inputStream)
                else
                    "Error : Stream null"
                //Возвращаем ответ в контекст
                return@withContext result
            } catch (e: Exception) {
                return@withContext e.message.toString()
            }
        }
}


fun convertInputStreamToString(inputStream: InputStream?): String {
    val writer = StringWriter()
    IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8)
    return writer.toString()
}




