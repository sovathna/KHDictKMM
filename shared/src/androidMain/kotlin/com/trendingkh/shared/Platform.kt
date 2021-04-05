package com.trendingkh.shared

import com.trendingkh.shared.db.AppDatabase
import java.util.zip.ZipInputStream

actual class Platform actual constructor() {

  actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"

}