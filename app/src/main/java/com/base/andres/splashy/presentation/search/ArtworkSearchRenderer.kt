package com.base.andres.splashy.presentation.search

interface ArtworkSearchRenderer {
    fun setFullSize()
    fun setMiniSize()
    fun setImage(imageUrl: String)
    fun setTitle(title: String)
}