package com.timothycox.gsra_app.util;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import java.util.HashSet;
import java.util.Set;

public class NetworkStateReceiver extends BroadcastReceiver {

    protected Set<NetworkStateReceiverListener> listeners;
    protected Boolean connected;
    protected AlertDialog networkDisconnectedDialog;

    public NetworkStateReceiver(Context context) {
        listeners = new HashSet<>();
        connected = null;
        networkDisconnectedDialog = new AlertDialog.Builder(context)
                .setTitle("Network Disconnected")
                .setMessage("Your network has been disconnected. This app requires internet connectivity to function. Please reconnect and try again.")
                .setPositiveButton("Enable WiFi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WifiManager wifiManager = (WifiManager)
                                context.getSystemService(Context.WIFI_SERVICE);
                        wifiManager.setWifiEnabled(true);
                    }
                })
                .setNegativeButton("Not now", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .create();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null)
            return;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
            networkDisconnectedDialog.dismiss();
        } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
            connected = false;
            // todo does this cause memory leak from not dismissing?
            networkDisconnectedDialog.show();
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
        void networkAvailable();
        void networkUnavailable();
    }
}