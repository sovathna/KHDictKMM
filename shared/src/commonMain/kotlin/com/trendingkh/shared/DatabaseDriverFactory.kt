package com.trendingkh.shared

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {

  fun createDriver(): SqlDriver

}