package com.nibodev.gd.gbversion;

import static com.nibodev.androidutil.AndroidUtility.console;
import static unified.vpn.sdk.OpenVpnTransport.TRANSPORT_ID_TCP;
import static unified.vpn.sdk.OpenVpnTransport.TRANSPORT_ID_UDP;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import unified.vpn.sdk.AuthMethod;
import unified.vpn.sdk.Callback;
import unified.vpn.sdk.ClientInfo;
import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.HydraTransport;
import unified.vpn.sdk.HydraTransportConfig;
import unified.vpn.sdk.HydraVpnTransportException;
import unified.vpn.sdk.NetworkRelatedException;
import unified.vpn.sdk.OpenVpnTransportConfig;
import unified.vpn.sdk.PartnerApiException;
import unified.vpn.sdk.SdkNotificationConfig;
import unified.vpn.sdk.SessionConfig;
import unified.vpn.sdk.SessionInfo;
import unified.vpn.sdk.TrackingConstants;
import unified.vpn.sdk.TrafficRule;
import unified.vpn.sdk.TransportConfig;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.UnifiedSdkConfig;
import unified.vpn.sdk.User;
import unified.vpn.sdk.Vpn;
import unified.vpn.sdk.VpnException;
import unified.vpn.sdk.VpnPermissionDeniedException;
import unified.vpn.sdk.VpnPermissionRevokedException;
import unified.vpn.sdk.VpnState;
import unified.vpn.sdk.VpnStateListener;
import unified.vpn.sdk.VpnTransportException;

public class VpnManager implements VpnStateListener
{
    private String TAG = getClass().getSimpleName();
    private String selectedCountry = "";
    private String base_host;
    private String carrier_id;
    private String ServerIPaddress = "00.000.000.00";
    private UnifiedSdk unifiedSdk;

    private Handler handler;

    private static VpnManager instance;

    public VpnManager(String target_country, String host, String carrier)
    {
        selectedCountry = target_country;
        base_host = host;
        carrier_id = carrier;
        handler = new Handler(Looper.getMainLooper());
        initHydraSdk();
    }

    public void wait_for_connection()
    {
        synchronized (this)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect()
    {
        UnifiedSdk.addVpnStateListener(this);
        isLoggedIn(new Callback<Boolean>()
        {
            @Override
            public void success(@NonNull Boolean aBoolean)
            {
                if (!aBoolean)
                {
                    handler.post(()-> loginToVpn());
                }
                else
                {
                    handler.post(()-> connectToVpn());
                }
            }

            @Override
            public void failure(@NonNull VpnException e)
            {

            }
        });
    }

    public void disconnect()
    {
        disconnectFromVnp();
        UnifiedSdk.removeVpnStateListener(this);
    }

    private void initHydraSdk()
    {
//        SharedPreferences prefs = getPrefs();
        ClientInfo clientInfo = ClientInfo.newBuilder()
                .addUrl(base_host)
                .carrierId(carrier_id)
                .build();
        List<TransportConfig> transportConfigList = new ArrayList<>();
        transportConfigList.add(HydraTransportConfig.create());
        transportConfigList.add(OpenVpnTransportConfig.tcp());
        transportConfigList.add(OpenVpnTransportConfig.udp());
        UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);
        UnifiedSdkConfig config = UnifiedSdkConfig.newBuilder().build();
        unifiedSdk = UnifiedSdk.getInstance(clientInfo, config);
        SdkNotificationConfig notificationConfig = SdkNotificationConfig.newBuilder().disabled()
                .build();
        UnifiedSdk.update(notificationConfig);
        UnifiedSdk.setLoggingLevel(Log.VERBOSE);
    }

//    public void setNewHostAndCarrier(String hostUrl, String carrierId)
//    {
//        SharedPreferences prefs = getPrefs();
//        if (TextUtils.isEmpty(hostUrl))
//        {
//            prefs.edit().remove(BuildConfig.STORED_HOST_URL_KEY).apply();
//        } else
//        {
//            prefs.edit().putString(BuildConfig.STORED_HOST_URL_KEY, hostUrl).apply();
//        }
//        if (TextUtils.isEmpty(carrierId))
//        {
//            prefs.edit().remove(BuildConfig.STORED_CARRIER_ID_KEY).apply();
//        } else
//        {
//            prefs.edit().putString(BuildConfig.STORED_CARRIER_ID_KEY, carrierId).apply();
//        }
//        initHydraSdk();
//    }

//    public SharedPreferences getPrefs()
//    {
//        return getSharedPreferences(BuildConfig.SHARED_PREFS, Context.MODE_PRIVATE);
//    }

//    private void createNotificationChannel()
//    {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//        {
//            CharSequence name = getResources().getString(R.string.app_name) + "";
//            String description = getResources().getString(R.string.app_name) + "" + getString(R.string.notify);
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(getPackageName(), name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }

    @Override
    public void vpnStateChanged(@NonNull VpnState vpnState)
    {
        switch (vpnState)
        {
            case IDLE:
                console(TAG,"state: idle");
                break;
            case CONNECTED:
                synchronized (this)
                {
                    notifyAll();
                }
                console(TAG, "state: connected");
                break;
            case CONNECTING_VPN:
                console(TAG, "state: connecting");
                break;
            case ERROR:
                console(TAG, "state: error");
                break;
            case UNKNOWN:
                console(TAG, "state: unknown");
                break;
            case DISCONNECTING:
                synchronized (this)
                {
                    notifyAll();
                }
                console(TAG, "state: disconnecting");
                break;
            case CONNECTING_PERMISSIONS:
                console(TAG, "state: connecting permissions");
                break;
            case CONNECTING_CREDENTIALS:
                console(TAG, "state: connecting credentials");
                break;
            case PAUSED:
                console(TAG, "state: paused");
        }
    }

    public void vpnError(@NonNull VpnException e)
    {
//    updateUI();
        handleError(e);
    }

    protected void isLoggedIn(Callback<Boolean> callback)
    {
        UnifiedSdk.getInstance().getBackend().isLoggedIn(callback);
    }

    protected void loginToVpn()
    {
        Log.e(TAG, "loginToVpn: 1111");
        AuthMethod authMethod = AuthMethod.anonymous();
        UnifiedSdk.getInstance().getBackend().login(authMethod, new Callback<User>()
        {
            @Override
            public void success(@NonNull User user)
            {
                console(TAG, "log in success");
                handler.postDelayed(()-> {connectToVpn();}, 5000);
            }

            @Override
            public void failure(@NonNull VpnException e)
            {
                console(TAG, "failed to login");
                handleError(e);
            }
        });
    }

    protected void isConnected(Callback<Boolean> callback)
    {
        UnifiedSdk.getVpnState(new Callback<VpnState>()
        {
            @Override
            public void success(@NonNull VpnState vpnState)
            {
                callback.success(vpnState == VpnState.CONNECTED);
            }

            @Override
            public void failure(@NonNull VpnException e)
            {
                callback.success(false);
            }
        });
    }

    protected void connectToVpn()
    {
        isLoggedIn(new Callback<Boolean>()
        {
            @Override
            public void success(@NonNull Boolean aBoolean)
            {
                if (aBoolean)
                {
                    List<String> fallbackOrder = new ArrayList<>();
//                    fallbackOrder.add(HydraTransport.TRANSPORT_ID);
                    fallbackOrder.add(TRANSPORT_ID_TCP);
                    fallbackOrder.add(TRANSPORT_ID_UDP);
//          showConnectProgress();
                    List<String> bypassDomains = new LinkedList<>();
                    bypassDomains.add("*facebook.com");
                    bypassDomains.add("*wtfismyip.com");

                    UnifiedSdk.getInstance().getVpn().start(new SessionConfig.Builder()
                            .withReason(TrackingConstants.GprReasons.M_UI)
                            .withTransportFallback(fallbackOrder)
                            .withVirtualLocation(selectedCountry)
                            .withTransport(HydraTransport.TRANSPORT_ID)
                            .addDnsRule(TrafficRule.Builder.bypass().fromDomains(bypassDomains))
                            .build(), new CompletableCallback()
                    {
                        @Override
                        public void complete()
                        {
                            console(TAG, "connection complete");
                        }

                        @Override
                        public void error(@NonNull VpnException e)
                        {
                            handleError(e);
                        }
                    });
                }
            }

            @Override
            public void failure(@NonNull VpnException e)
            {
            }
        });
    }

    protected void disconnectFromVnp()
    {
        UnifiedSdk.getInstance().getVpn().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback()
        {
            @Override
            public void complete()
            {

            }

            @Override
            public void error(@NonNull VpnException e)
            {
                handleError(e);
            }
        });
    }

    protected void getCurrentServer(final Callback<String> callback)
    {
        UnifiedSdk.getVpnState(new Callback<VpnState>()
        {
            @Override
            public void success(@NonNull VpnState state)
            {
                if (state == VpnState.CONNECTED)
                {
                    UnifiedSdk.getStatus(new Callback<SessionInfo>()
                    {
                        @Override
                        public void success(@NonNull SessionInfo sessionInfo)
                        {
                            ServerIPaddress = sessionInfo.getCredentials().getServers().get(0).getAddress();
//              ShowIPaddera(ServerIPaddress);
                            callback.success(sessionInfo.getCredentials().getServers().get(0).getCountry());
                        }

                        @Override
                        public void failure(@NonNull VpnException e)
                        {
                            callback.success(selectedCountry);
                        }
                    });
                } else
                {
                    callback.success(selectedCountry);
                }
            }

            @Override
            public void failure(@NonNull VpnException e)
            {
                callback.failure(e);
            }
        });
    }


    public void handleError(Throwable e)
    {
        if (e instanceof NetworkRelatedException)
        {
//      showMessage("Check internet connection");
        } else if (e instanceof VpnException)
        {
            if (e instanceof VpnPermissionRevokedException)
            {
//        showMessage("User revoked vpn permissions");
            } else if (e instanceof VpnPermissionDeniedException)
            {
//        showMessage("User canceled to grant vpn permissions");
            } else if (e instanceof VpnTransportException)
            {
                HydraVpnTransportException hydraVpnTransportException = (HydraVpnTransportException) e;
                if (hydraVpnTransportException.getCode() == HydraVpnTransportException.HYDRA_ERROR_BROKEN)
                {
//          showMessage("Connection with vpn server was lost");
                } else if (hydraVpnTransportException.getCode() == HydraVpnTransportException.HYDRA_DCN_BLOCKED_BW)
                {
//          showMessage("Client traffic exceeded");
                } else
                {
//          showMessage("Error in VPN transport");
                }
            } else
            {
                Log.e(TAG, "Error in VPN Service ");
            }
        } else if (e instanceof PartnerApiException)
        {
            switch (((PartnerApiException) e).getContent())
            {
                case PartnerApiException.CODE_NOT_AUTHORIZED:
//          showMessage("User unauthorized");
                    break;
                case PartnerApiException.CODE_TRAFFIC_EXCEED:
//          showMessage("Server unavailable");
                    break;
                default:
//          showMessage("Other error. Check PartnerApiException constants");
                    break;
            }
        }
    }
}
