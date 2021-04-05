package com.trendingkh.shared

import com.trendingkh.shared.db.Dict
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.utils.io.*
import kotlinx.coroutines.coroutineScope

class RepositoryImpl(factory: DatabaseDriverFactory) : Repository {

  private val db = Database(factory)

  override suspend fun addWord(word: String, definition: String) {
    return coroutineScope {
      db.addWord(word, definition)
    }
  }

  override suspend fun getWordById(id: Long): Dict {
    return coroutineScope {
      db.getWordById(id)
    }
  }

  override suspend fun downloadZipDB() {

  }
}