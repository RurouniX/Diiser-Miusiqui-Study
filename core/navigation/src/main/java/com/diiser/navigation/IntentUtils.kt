package com.diiser.navigation

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import timber.log.Timber

object IntentUtils {


    private fun getActionsIntent(
        context: Context, className: String
    ): Intent {
        return Intent(context, Class.forName(className))
    }

    fun openFeatureIntent(context: Context, className: String, extras: Intent.() -> Unit = {}) {
        try {
            getActionsIntent(context, className).apply(extras).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(it)
            }
        } catch (error: Exception) {
            Timber.e(error)
        }
    }

    fun openFeatureIntentWithoutStack(
        context: Context,
        className: String,
        extras: Intent.() -> Unit = {}
    ) {
        try {
            getActionsIntent(context, className).apply(extras).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(it)
            }
        } catch (error: Exception) {
            Timber.e(error)
        }
    }
    //TODO faz com Result

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