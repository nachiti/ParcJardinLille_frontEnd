package com.fr.parcjardinlille;

import android.os.AsyncTask;
import android.util.Log;


import com.fr.parcjardinlille.models.ParcJardin;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class GooglePlacesReadTask extends AsyncTask<Object, Integer, GoogleMap> {

    String googlePlacesData = null;
    GoogleMap googleMap;
    List<ParcJardin> ListParcJardins;

    @Override
    protected GoogleMap doInBackground(Object... inputObj) {
        try {
            googleMap = (GoogleMap) inputObj[0];
            ListParcJardins = (List<ParcJardin>) inputObj[1];

        } catch (Exception e) {
            Log.d("Google Place Read Task", e.toString());
        }
        return googleMap;
    }

    @Override
    protected void onPostExecute(GoogleMap googleMap) {
        googleMap.clear();
        if(ListParcJardins != null)

            for (int i = 0; i < ListParcJardins.size(); i++) {

                MarkerOptions markerOptions = new MarkerOptions();
                double lat = ListParcJardins.get(i).getLatitude();
                double lng = ListParcJardins.get(i).getLongitude();

                LatLng mLatLng = new LatLng(lat,lng);

                markerOptions.position(mLatLng);

                markerOptions.title(ListParcJardins.get(i).getNom());

                googleMap.addMarker(markerOptions);
            }

    }
}
