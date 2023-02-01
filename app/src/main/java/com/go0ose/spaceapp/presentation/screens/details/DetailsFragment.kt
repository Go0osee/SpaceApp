package com.go0ose.spaceapp.presentation.screens.details

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.github.terrakok.cicerone.Router
import com.go0ose.spaceapp.SpaseApp
import com.go0ose.spaceapp.databinding.FragmentDetailsBinding
import com.go0ose.spaceapp.presentation.screens.base.BaseFragment
import com.go0ose.spaceapp.utils.setImageByUrl
import moxy.ktx.moxyPresenter
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Provider

class DetailsFragment(private val photo: String) : BaseFragment<FragmentDetailsBinding>(), IDetailsView  {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: Provider<DetailsPresenter>

    private val presenter by moxyPresenter() { presenterProvider.get() }

    override fun binding() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaseApp.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            marsPhoto.setImageByUrl(photo)

            tutorial.setOnClickListener{
                presenter.hideTutorial()
            }
            buttonBack.setOnClickListener {
                presenter.back()
            }
            buttonShare.setOnClickListener {
                shareImage()
            }
        }
    }

    private fun shareImage() {
        val imageView: ImageView = binding.marsPhoto
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image.jpeg")
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.close()
        val imageUri: Uri = FileProvider.getUriForFile(requireContext(), "com.go0ose.spaceapp.provider", file)
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "image/jpeg"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(shareIntent, "Share image using"))
    }

    override fun showTutorial() {
        binding.tutorial.visibility = View.VISIBLE
    }

    override fun hideTutorial() {
        binding.tutorial.visibility = View.GONE
    }
}