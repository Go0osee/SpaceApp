package com.go0ose.spaceapp.presentation.screens.map.recycler

import androidx.recyclerview.widget.DiffUtil
import com.google.android.gms.maps.model.Marker

class MapDiffCallback(private val oldItems: List<Marker>, private val newItems: List<Marker>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }
}