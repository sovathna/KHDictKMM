package com.trendingkh.shared

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

actual typealias DBFile = File

actual class DownloadService {


//  @Suppress("BlockingMethodInNonBlockingContext")
//  actual suspend fun downloadZipDB(file: DBFile) {
//    val client = HttpClient(AndroidClientEngine(AndroidEngineConfig()))
//    val url = "https://github.com/sovathna/Khmer-Dictionary/raw/master/db/room_sqlite.zip"
//    Log.d("AppLog", "Path: ${path.absolutePath}")
//    client.get<HttpStatement>(url).execute { response ->
//      val channel = response.receive<ByteReadChannel>()
////      val bufferSize = 4 * 1024
////      val byteBuffer = ByteArray(bufferSize)
////      do {
////        val read = channel.readAvailable(byteBuffer)
////        if (read > 0) {
////          outputStream.write(byteBuffer, 0, read)
////          Log.d("AppLog", "Writing")
////        }
////      } while (read >= 0)
////      outputStream.flush()
//      val zipInStream = ZipInputStream(channel.toInputStream())
//      zipInStream.nextEntry?.let {
//        val bufferSize = 4 * 1024
//        val byteBuffer = ByteArray(bufferSize)
//        val outputStream = FileOutputStream(path)
//        do {
//          val read = zipInStream.read(byteBuffer)
//          if (read > 0) {
//            outputStream.write(byteBuffer, 0, read)
//            Log.d("AppLog", "Writing")
//          }
//        } while (read >= 0)
//        zipInStream.closeEntry()
//        outputStream.flush()
//      }
//      Log.d("AppLog", "Done")
//
//    }
//  }

  @Suppress("BlockingMethodInNonBlockingContext")
  actual suspend fun saveDatabase(des: DBFile, response: HttpResponse, progress: (Long, Long) -> Unit): Boolean {
    try {
      val channel = response.receive<ByteReadChannel>()
      val bufferSize = 4 * 1024
      val byteBuffer = ByteArray(bufferSize)
      var total = 0L
      val out = ByteArrayOutputStream()
      do {
        val read = channel.readAvailable(byteBuffer)
        if (read > 0) {
          out.write(byteBuffer, 0, read)
          total += read
          progress(total, response.contentLength() ?: 0)
        }
      } while (read >= 0)
      progress(response.contentLength() ?: 0, response.contentLength() ?: 0)
      val zipInStream = ZipInputStream(ByteArrayInputStream(out.toByteArray()))
      zipInStream.nextEntry?.let {
        val bufferSize = 4 * 1024
        val byteBuffer = ByteArray(bufferSize)
        val outputStream = FileOutputStream(des)
        var total = 0L
        do {
          val read = zipInStream.read(byteBuffer)
          if (read > 0) {
            outputStream.write(byteBuffer, 0, read)
            total += read
            progress(total, it.size)
          }
        } while (read >= 0)
        zipInStream.closeEntry()
        outputStream.flush()
        progress(it.size, it.size)

      }
      return true
    } catch (e: Exception) {
      e.printStackTrace()
      return false
    }
  }

  actual suspend fun isDatabaseExists(des: DBFile): Boolean {
    return des.exists()
  }

}