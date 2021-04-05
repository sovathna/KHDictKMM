package com.trendingkh.shared

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class SplashRepository(private val client: HttpClient, private val service: DownloadService) {


  suspend fun downloadDatabase(file: DBFile, progress: (Long, Long) -> Unit): Boolean {
    val isExists = service.isDatabaseExists(file)
    if (isExists) return true
    val url = "https://github.com/sovathna/Khmer-Dictionary/raw/master/db/room_sqlite.zip"
    return client.get<HttpStatement>(url).execute { response ->
      service.saveDatabase(file, response, progress)
    }
  }

}