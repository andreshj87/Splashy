package com.base.andres.splashy.presentation.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.view_empty.*
import kotlinx.android.synthetic.main.view_loading.*

abstract class BaseActivity: AppCompatActivity() {
    private val errorDialog = ErrorDialog()

    abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
    }

    fun showLoading() {
        view_loading?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        view_loading?.visibility = View.GONE
    }

    fun showEmpty() {
        view_empty?.visibility = View.VISIBLE
    }

    fun hideEmpty() {
        view_empty?.visibility = View.GONE
    }

    fun showError() {
        errorDialog.show(this)
    }
}