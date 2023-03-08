package com.diiser.navigation.home

import android.content.Context
import com.diiser.navigation.IntentUtils

object HomeNavigation {

    private const val HOME_ACTIVITY_URI = "com.diiser.home.HomeActivity"

    fun openHomeActivity(context: Context) =
        IntentUtils.openFeatureIntentWithoutStack(context, HOME_ACTIVITY_URI)

}