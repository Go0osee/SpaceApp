package com.go0ose.spaceapp.presentation.screens.main.recycler

import com.go0ose.domain.models.Photo

interface OnItemClickListener {
    fun onItemClick(photo: Photo)
}