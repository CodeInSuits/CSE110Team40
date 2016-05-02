package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Matt on 5/2/16.
 */
public class LocationAPIHelper {

    public static GoogleApiClient getApiClient(Context context,
                                        GoogleApiClient.ConnectionCallbacks callbacks,
                                        GoogleApiClient.OnConnectionFailedListener failedListener) {
        return new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(callbacks)
                .addOnConnectionFailedListener(failedListener)
                .addApi(LocationServices.API)
                .build();
    }

}
