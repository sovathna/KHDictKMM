package com.trendingkh.shared

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.trendingkh.shared.db.AppDatabase

actual class DatabaseDriverFactory(private val context: Context) {
  actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(AppDatabase.Schema, context, "dict.db")
  }

}