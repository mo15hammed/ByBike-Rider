package com.muhammadelsayed.bybike_rider.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Maps {

    public static final String TAG = Maps.class.getSimpleName();

    public static String getAddressFromCoordinates(Context context, double lat, double lon) throws IOException {
        Log.wtf(TAG, "getAddressFromCoordinates() has been instantiated");

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        addresses = geocoder.getFromLocation(lat, lon, 5); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        String address = null;
        int i = 0;
        while (i < 10) {
            address = addresses.get(i).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            if (address != null)
                break;
            i++;
        }



        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

        return Utils.splitAddress(address);
    }
}
