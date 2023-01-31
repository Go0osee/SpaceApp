package com.go0ose.spaceapp.presentation.screens.main.recycler

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.go0ose.domain.models.Photo
import com.go0ose.spaceapp.databinding.ItemPhotoBinding
import com.go0ose.spaceapp.utils.setImageByUrl

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


    private fun TextView.setShader() {
        this.paint.shader = LinearGradient(0f,
            0f,
            this.paint.measureText(this.text.toString()),
            this.textSize,
            intArrayOf(
                Color.parseColor("#C00F9E"),
                Color.parseColor("#DB3259"),
            ),
            null,
            Shader.TileMode.REPEAT)
    }
}
