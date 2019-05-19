package com.base.andres.splashy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.base.andres.splashy.R
import com.base.andres.splashy.domain.SearchArt
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {
    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainText: TextView = findViewById(R.id.text_art_search_result)
        mainViewModel.artSearch.observe(this, Observer {
            if (it != null) {
                val values: List<Int> = it
                var textValues = ""
                for (searchResult in values) {
                    textValues = "$textValues, $searchResult"
                }
                mainText.text = textValues
            }
        })
        mainViewModel.search()
    }
}
