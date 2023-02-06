package com.go0ose.spaceapp.presentation.screens.map.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.Marker

class MapAdapter(
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<MapViewHolder>() {

    var items: MutableList<Marker> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        return MapViewHolder.newInstance(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<Marker>) {
        val diffCallback = MapDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}

