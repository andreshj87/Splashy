package com.base.andres.splashy.presentation.base

import android.app.AlertDialog
import android.content.Context
import com.base.andres.splashy.R

class ErrorDialog {
    fun show(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.error_title))
            .setMessage(context.getString(R.string.error_default_message))
            .setPositiveButton(context.getString(R.string.done)) { dialog, _ -> dialog?.dismiss() }
            .create()
            .show()
    }
}