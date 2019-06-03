package com.base.andres.splashy.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.base.andres.splashy.R
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtworkSearchActivity: AppCompatActivity() {
    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, ArtworkSearchActivity::class.java)
        }
    }

    private val viewModel: ArtworkSearchViewModel by viewModel()
    private lateinit var adapter: ArtworkSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        button_search.setOnClickListener {
            viewModel.onSearchClick(edit_search.text.toString())
        }
        adapter = ArtworkSearchAdapter(viewModel)
        recycler_search.adapter = adapter
        viewModel.artworksFound.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
    }
}