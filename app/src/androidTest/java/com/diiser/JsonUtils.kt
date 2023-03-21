package com.diiser

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.diiser.model.home.SearchModel
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader


fun response200Home() = readJson(context , "result200home.json", SearchModel::class.java)
fun response200HomeJson() =
    assets.open("result200home.json").bufferedReader().use { it.readText() }

private val assets = InstrumentationRegistry.getInstrumentation().context.assets
private val context = InstrumentationRegistry.getInstrumentation().context

private inline fun <reified T> readJson(context: Context, fileName: String, clazz: Class<T>): T {
    val jsonString = getJsonFromAssets(context, fileName)
    return GsonBuilder().create().fromJson(jsonString, clazz)
}

const val ASSET_BASE_PATH = "../app/src/androidTest/assets/"


@Throws(IOException::class)
fun readJsonFile(filename: String): String? {
    val br = BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + filename)))
    val sb = StringBuilder()
    var line: String = br.readLine()
    while (line != null) {
        sb.append(line)
        line = br.readLine()
    }
    return sb.toString()
}


fun getJsonFromAssets(context: Context, fileName: String): String {
    val jsonString: String
    try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()
        jsonString = String(buffer, Charsets.UTF_8)
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
    return jsonString
}
