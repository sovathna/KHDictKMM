package com.trendingkh.shared

import io.ktor.client.*
import io.ktor.utils.io.*

actual class DBFile

actual class DownloadService(private val client: HttpClient) {

  actual suspend fun saveDatabase(des: DBFile, channel: ByteReadChannel): Boolean {
    TODO("Not yet implemented")
  }

  actual suspend fun isDatabaseExists(des: DBFile): Boolean {
    TODO("Not yet implemented")
  }


}