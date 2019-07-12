package com.base.andres.splashy.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.base.andres.splashy.R
import com.base.andres.splashy.presentation.base.BaseActivity
import com.base.andres.splashy.presentation.base.Emptiable
import com.base.andres.splashy.presentation.base.Errorable
import com.base.andres.splashy.presentation.base.Loadable
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.view_empty.*
import kotlinx.android.synthetic.main.view_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtworkSearchActivity: BaseActivity(), Loadable, Emptiable, Errorable {
    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, ArtworkSearchActivity::class.java)
        }
    }

    private val viewModel: ArtworkSearchViewModel by viewModel()
    private lateinit var adapter: ArtworkSearchAdapter

    override fun getLayoutResource(): Int {
        return R.layout.activity_search
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        setupArtworkList()
        button_search.setOnClickListener {
            closeKeyboard()
            viewModel.onSearchClick(edit_search.text.toString())
        }
    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupArtworkList() {
        adapter = ArtworkSearchAdapter(viewModel)
        recycler_search.adapter = adapter
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is ArtworkSearchViewModel.ArtworkSearchState.Loading -> showLoading()
                is ArtworkSearchViewModel.ArtworkSearchState.Empty -> showEmpty()
                is ArtworkSearchViewModel.ArtworkSearchState.Error -> showError()
                is ArtworkSearchViewModel.ArtworkSearchState.Success -> adapter.notifyDataSetChanged()
            }
            adapter.notifyDataSetChanged()
        })
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
        }
    }
}