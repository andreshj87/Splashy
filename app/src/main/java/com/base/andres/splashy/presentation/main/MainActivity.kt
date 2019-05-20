package com.base.andres.splashy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.base.andres.splashy.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.artworkIds.observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                showError(false)
                showResults(it)
            } else {
                showError()
            }
        })
        mainViewModel.firstArtwork.observe(this, Observer {
            if (it != null) {
                text_artwork_highlight.text = "${it.title} by ${it.artist}"
            }
        })
        mainViewModel.search()
    }

    private fun showResults(results: List<Int>) {
        var textValues = ""
        for (searchResult in results) {
            textValues = "$textValues, $searchResult"
        }
        text_artwork_results.text = textValues
    }

    private fun showError(show: Boolean = true) {
        when (show) {
            true -> text_error.visibility = View.VISIBLE
            false -> text_error.visibility = View.GONE
        }
    }
}
