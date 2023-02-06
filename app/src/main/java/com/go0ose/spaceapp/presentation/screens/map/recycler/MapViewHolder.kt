package com.go0ose.spaceapp.presentation.screens.map.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.go0ose.spaceapp.R
import com.go0ose.spaceapp.databinding.ItemMarkerBinding
import com.google.android.gms.maps.model.Marker

class MapViewHolder(
    private val binding: ItemMarkerBinding,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            onItemClickListener: OnItemClickListener,
        ) =
            MapViewHolder(
                ItemMarkerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClickListener
            )
    }

    fun bind(item: Marker) {
        with(binding) {
            if(item.title!!.isEmpty()){
                markerName.text = root.context.getString(R.string.no_name)
            } else {
                markerName.text = item.title
            }

            longitudeValue.text = item.position.longitude.toString()
            widthValue.text = item.position.latitude.toString()

            buttonDelete.setOnClickListener {
                onItemClickListener.onItemClickDelete(item)
            }
        }
    }
}