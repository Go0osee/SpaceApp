package com.go0ose.spaceapp.presentation.screens.map.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.go0ose.spaceapp.databinding.ItemMarkerBinding
import com.google.android.gms.maps.model.Marker

class MarkerAdapter : RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder>() {

    var items: MutableList<Marker> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder {
        return MarkerViewHolder(
            ItemMarkerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun addMarker(marker: Marker) {
        items.add(marker)
        notifyItemInserted(items.size + 1)
    }

    fun removeMarker(marker: Marker) {
        val position = items.indexOfFirst { it.id == marker.id }
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class MarkerViewHolder(
        private val binding: ItemMarkerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Marker) {
            with(binding) {
                markerName.text = item.title
                longitudeValue.text = item.position.longitude.toString()
                widthValue.text = item.position.latitude.toString()

                buttonDelete.setOnClickListener {
                    item.remove()
                    removeMarker(item)
                }
            }
        }
    }
}

