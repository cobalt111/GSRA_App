package com.timothycox.gsra_app.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashSet;
import java.util.Set;

public class NetworkStateReceiver extends BroadcastReceiver {

    protected Set<NetworkStateReceiverListener> listeners;
    protected Boolean connected;
    protected ConnectivityManager connectivityManager;
    protected boolean isWifiConnected;
    protected boolean isMobileConnected;

    public NetworkStateReceiver() {
        listeners = new HashSet<NetworkStateReceiverListener>();
        connected = null;
    }

//    // check if connected
//    boolean isConnected() {
//        for (Network network : connectivityManager.getAllNetworks()) {
//            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
//            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                isWifiConnected |= networkInfo.isConnected();
//            }
//            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
//                isMobileConnected |= networkInfo.isConnected();
//            }
//        }
//
//        return isWifiConnected || isMobileConnected;
//    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null)
            return;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
            connected = false;
        }

        notifyStateToAll();
    }

    private void notifyStateToAll() {
        for (NetworkStateReceiverListener listener : listeners)
            notifyState(listener);
    }

    private void notifyState(NetworkStateReceiverListener listener) {
        if (connected == null || listener == null)
            return;

        if (connected)
            listener.networkAvailable();
        else
            listener.networkUnavailable();
    }

    public void addListener(NetworkStateReceiverListener listener) {
        listeners.add(listener);
        notifyState(listener);
    }

    public void removeListener(NetworkStateReceiverListener listener) {
        listeners.remove(listener);
    }

    public interface NetworkStateReceiverListener {
        public void networkAvailable();
        public void networkUnavailable();
    }
}