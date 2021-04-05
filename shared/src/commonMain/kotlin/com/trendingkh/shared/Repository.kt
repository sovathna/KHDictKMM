package com.trendingkh.shared

import com.trendingkh.shared.db.Dict

interface Repository {

  suspend fun addWord(word: String, definition: String)

  suspend fun getWordById(id: Long): Dict

  suspend fun downloadZipDB()

}