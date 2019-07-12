package com.base.andres.splashy.presentation.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.base.andres.splashy.R
import com.base.andres.splashy.presentation.Navigator
import com.base.andres.splashy.presentation.base.BaseActivity
import com.base.andres.splashy.presentation.base.Emptiable
import com.base.andres.splashy.presentation.base.Errorable
import com.base.andres.splashy.presentation.base.Loadable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), Loadable, Emptiable, Errorable {
    private val viewModel: MainViewModel by viewModel()
    private val navigator: Navigator by inject()
    private lateinit var recentAdapter: ArtworkRecentAdapter

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button_navigate_to_search.setOnClickListener {
            navigator.navigateToArtworkSearchScreen(this)
        }
        recentAdapter = ArtworkRecentAdapter(viewModel)
        recycler_recent.adapter = recentAdapter
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is MainViewModel.MainState.Loading -> showLoading()
                is MainViewModel.MainState.Empty -> showEmpty()
                is MainViewModel.MainState.Error -> showError()
                is MainViewModel.MainState.Success -> recentAdapter.notifyDataSetChanged()
            }
        })
        viewModel.onInitialize()
    }
}
