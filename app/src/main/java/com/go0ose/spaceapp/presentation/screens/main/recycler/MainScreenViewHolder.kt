package com.go0ose.spaceapp.presentation.screens.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.go0ose.domain.models.Photo
import com.go0ose.spaceapp.databinding.ItemPhotoBinding
import com.go0ose.spaceapp.utils.setImageByUrl
import com.go0ose.spaceapp.utils.setShader

class MainScreenViewHolder(
    private val binding: ItemPhotoBinding,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            onItemClickListener: OnItemClickListener,
        ) =
            MainScreenViewHolder(
                ItemPhotoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClickListener
            )
    }

    fun bind(item: Photo) {
        with(binding) {
            container.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }

            photo.setImageByUrl(item.imgSrc)
            roverName.text = item.rover.name
            cameraFullName.text = item.camera.fullName
            showImage.setShader()
        }
    }
}
