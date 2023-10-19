package com.wzq.sample.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import com.wzq.sample.App
import com.wzq.sample.util.PrefsSafety
import java.net.Inet4Address

/**
 * create by wzq on 2023/10/17
 *
 */
class NetworkWatcher : ConnectivityManager.NetworkCallback() {

//    private val _state = MutableSharedFlow<Boolean>()

    private val connectivityManager by lazy {
        App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun start() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                connectivityManager.registerDefaultNetworkCallback(this)
            }

            else -> {
                val request = NetworkRequest.Builder()
                    .addTransportType(android.net.NetworkCapabilities.TRANSPORT_CELLULAR)
                    .addTransportType(android.net.NetworkCapabilities.TRANSPORT_WIFI).build()
                connectivityManager.requestNetwork(request, this)
            }
        }
    }

    fun stop() {
        connectivityManager.unregisterNetworkCallback(this)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        updateNetworkInfo(network)
    }

    private fun updateNetworkInfo(network: Network) {
        val properties = connectivityManager.getLinkProperties(network) ?: return
        properties.linkAddresses.forEach {
            val address = it.address
            if (address is Inet4Address && !address.isLoopbackAddress) {//&& address.isSiteLocalAddress
                address.hostAddress.also { n4 ->
                    if (!n4.isNullOrEmpty()) {
                        PrefsSafety.write("ip", n4)
                    }
                }
            }
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
    }
}