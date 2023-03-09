package com.diiser.player

import android.content.Intent

object IntentExtensions {

    fun Intent.getParcelable(key: String, clazz: Class<*>) =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU)
            extras?.getParcelable(key, clazz)
        else
            extras?.getParcelable(key)
}

