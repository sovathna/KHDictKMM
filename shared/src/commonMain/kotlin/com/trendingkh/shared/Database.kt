package com.trendingkh.shared

import com.trendingkh.shared.db.AppDatabase
import com.trendingkh.shared.db.Dict

internal class Database(factory: DatabaseDriverFactory) {

  private val db = AppDatabase(factory.createDriver())
  private val query = db.appDatabaseQueries

  internal fun addWord(word: String, definition: String) {
    query.insertWord(word, definition)
  }

  internal fun getWordById(id: Long): Dict {
    return query.selectWordById(id).executeAsOne()
  }

}