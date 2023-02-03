package com.go0ose.spaceapp.utils

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.ConnectivityManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.isOnline(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        return true
    }
    return false
}

fun Context.showToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.setImageByUrl(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun TextView.setShader() {
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