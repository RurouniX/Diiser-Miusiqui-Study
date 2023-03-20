package com.diiser.navigation

import android.content.Intent

object IntentExtensions {

    inline fun <reified T> Intent.getParcelable(key: String): T? =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU)
            extras?.getParcelable(key, T::class.java)
        else
            extras?.getParcelable(key)
}

