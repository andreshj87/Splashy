package com.base.andres.splashy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.base.andres.splashy.R
import com.base.andres.splashy.domain.entity.Artwork
import com.base.andres.splashy.presentation.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_navigate_to_search.setOnClickListener {
            navigator.navigateToArtworkSearchScreen(this)
        }
    }
}
