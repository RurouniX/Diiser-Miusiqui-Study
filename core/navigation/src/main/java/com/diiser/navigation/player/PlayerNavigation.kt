package com.diiser.navigation.player

import android.content.Context
import com.diiser.model.home.DataModel
import com.diiser.navigation.IntentUtils

object PlayerNavigation {

    private const val PLAYER_ACTIVITY_URI = "com.diiser.player.PlayerActivity"
    const val EXTRA_DATAMODEL = "EXTRA_DATAMODEL"

    fun openPlayerActivity(context: Context, dataModel: DataModel) =
        IntentUtils.openFeatureIntent(context, PLAYER_ACTIVITY_URI) {
            putExtra(EXTRA_DATAMODEL, dataModel)
        }

}