package com.base.andres.splashy.presentation

import android.content.Context
import com.base.andres.splashy.presentation.search.ArtworkSearchActivity

class Navigator {
    fun navigateToArtworkSearchScreen(context: Context) {
        val intentToLaunch = ArtworkSearchActivity.getCallingIntent(context)
        context.startActivity(intentToLaunch)
    }
}