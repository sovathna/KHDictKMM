package com.trendingkh.shared

import io.ktor.client.statement.*

expect class DBFile

expect class DownloadService {
  suspend fun saveDatabase(des: DBFile, response: HttpResponse, progress: (Long, Long) -> Unit): Boolean

  suspend fun isDatabaseExists(des: DBFile): Boolean
}