package com.go0ose.spaceapp.presentation.screens.map.recycler

import com.google.android.gms.maps.model.Marker

interface OnItemClickListener {
    fun onItemClickDelete(marker: Marker)
}