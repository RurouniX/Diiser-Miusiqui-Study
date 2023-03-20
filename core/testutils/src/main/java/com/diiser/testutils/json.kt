package com.diiser.testutils

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

inline fun <reified T> readJsonFile(inputStream: InputStream): T {

    val buffered = BufferedReader(InputStreamReader(inputStream))
    val sb = java.lang.StringBuilder()
    var line: String? = buffered.readLine()
    while (line != null) {
        sb.append(line)
        line = buffered.readLine()
    }
    return Gson().fromJson(sb.toString(), T::class.java)

}