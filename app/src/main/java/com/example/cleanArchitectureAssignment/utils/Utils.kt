package com.example.cleanArchitectureAssignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.cleanArchitectureAssignment.R


class Utils {
    companion object {
        const val grantType = "password"
        const val DeviceToken =
            "dFT5ypmNR2-ArYe9b13mcR%3AAPA91bHiVJZr-xYC5vhUcsFS8_gVEqeze9vuPQm2rem0uzRojm26NpM_GigPeUzC8UJX_T7UjzeH4P-0JVTM9VogiEVp6RVBf377ORnhFRSEP2JksxJadPTssz-y7Ayyv_GP-I7VaGXj"
        const val mailto = "mailto:"
        const val googleMapUrl = "http://maps.google.com/maps?q=loc:"
    }

    fun imgLoadUsingUrl(ctx: Context, url: String, imgView: ImageView) {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_login)
            .error(R.drawable.ic_login)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()
        Glide.with(ctx)
            .load(url)
            .apply(options)
            .into(imgView)

    }

    fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}