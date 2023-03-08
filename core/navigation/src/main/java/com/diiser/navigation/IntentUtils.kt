package com.diiser.navigation

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.view.ContentInfoCompat.Flags
import timber.log.Timber

object IntentUtils {


    private fun getActionsIntent(
        context: Context, className: String
    ): Intent {
        return Intent(context, Class.forName(className))
    }

    private fun openFeature(
        context: Context,
        className: String,
        flags: Int,
        extras: Intent.() -> Unit = {}
    ) {
        try {
            getActionsIntent(context, className).apply(extras).also {
                it.flags = flags
                context.startActivity(it)
            }
        } catch (error: Exception) {
            Timber.e(error)
        }
    }

    fun openFeatureIntent(context: Context, className: String, extras: Intent.() -> Unit = {}) {
        openFeature(context, className, Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    fun openFeatureIntentWithoutStack(
        context: Context,
        className: String,
        extras: Intent.() -> Unit = {}
    ) {
        openFeature(
            context,
            className,
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK,
            extras
        )
    }
    //TODO fazer com Result

    //TODO corrigir a flag
    fun createPendingIntent(
        context: Context,
        className: String,
        extras: Intent.() -> Unit = {}
    ): PendingIntent {
        return getActionsIntent(context, className).apply(extras).let {
            PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_ONE_SHOT)
        }
    }

}