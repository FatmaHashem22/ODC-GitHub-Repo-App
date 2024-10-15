package com.example.odcgithubrepoapp.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtils {
     fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return activeNetworkInfo != null && activeNetworkInfo.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}