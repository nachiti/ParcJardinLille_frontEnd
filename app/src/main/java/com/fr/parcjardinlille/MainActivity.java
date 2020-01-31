package com.fr.parcjardinlille;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.fr.parcjardinlille.Services.Service;
import com.fr.parcjardinlille.models.ParcJardin;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends FragmentActivity implements LocationListener {

    private static final String GOOGLE_API_KEY = "AIzaSyAl6VLd_QVux2AucgfXrmiJCDmnMJbpN4o";
    GoogleMap googleMap;
    EditText place_Text;
    double latit=0,longit=0;
    private int PROXIMITY_RADIUS = 5000;
    private SearchView searchView;
    private List<ParcJardin> parcJardinList = new ArrayList<>();

    public Service URLretrofit(){
        Service service = new RestAdapter.Builder()
                .setEndpoint(Service.API_URL)
                .build()
                .create(Service.class);

        return service;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //we need to check if the Google API is available before using it
        super.onCreate(savedInstanceState);
        if(checkPlayServices()!=true){
            finish();
        }
        setContentView(R.layout.activity_main);
        searchView =(SearchView) findViewById(R.id.searchv);// recupere le champ de recherche
        searchView.setIconifiedByDefault(false);//e champ de recherche soit toujours visible,

        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        googleMap = mapFragment.getMap();
        googleMap.setMyLocationEnabled(true); //Active ou désactive la couche my-location
        /**
         * Ces services permettent aux applications d'obtenir des mises à jour périodiques de l'emplacement géographique de l'apparei
         */
        LocationManager locationManager =(LocationManager) getSystemService(LOCATION_SERVICE);//pour contrôler les mises à jour d'emplacement

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            }
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
            }
            return;
        }

        /**Nom du fournisseur de localisation GPS
         */
        String nomFornisseurLocalisationGPS = locationManager.GPS_PROVIDER;
        /**the last known location for the provider, or null*/
        Location location =  locationManager.getLastKnownLocation(nomFornisseurLocalisationGPS);

        if(location != null){
            onLocationChanged(location);
        }

        /*Inscrivez-vous pour les mises à jour d'emplacement à l'aide du fournisseur nommé et d'une intention en attente*/
        locationManager.requestLocationUpdates(nomFornisseurLocalisationGPS,30,0,this);


        getAllParcJardinLille();

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(MainActivity.this, DetailParcJardin.class);
                intent.putExtra("nameJardinParcLille", marker.getTitle());
                startActivity(intent);
                return false;
            }
        });
    }

    /**
     *
     * @param parcJardins
     */
    public void ajoutPointAuMap(List<ParcJardin> parcJardins){
        GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
        Object[] toPass = new Object[2];
        toPass[0] = googleMap;
        toPass[1] = parcJardins;
        googlePlacesReadTask.execute(toPass);

    }

    /**
     * Recupere les tous parcjardin
    * */
    public void getAllParcJardinLille(){
        Service service = URLretrofit();
        service.getAllParc(new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {
                ajoutPointAuMap(parcJardins);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication()," getAllParcJardinLille : n'exist pas "+error, Toast.LENGTH_SHORT).show();

            }
        });
    }
    /**
     * Recupere les parJardin avec le service = servicename
     */
     public void getParcJardinByService(final String serviceName){

        Service service = URLretrofit();
        service.getParcJardinnService(serviceName, new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {
                ajoutPointAuMap(parcJardins);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication()," getParcJardinByService(nameservice): Parc Ou Jardin n'existe pas !! ",Toast.LENGTH_SHORT).show();
            }
        });

     }

    /**
     * vérifier que l'APK des services Google Play est disponible et à jour sur cet appareil.
     * @return
     */
    public boolean checkPlayServices(){
        int resultcode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(ConnectionResult.SUCCESS ==resultcode){
            return true;
        }else{
            GooglePlayServicesUtil.getErrorDialog(resultcode,this,0).show();
            return false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /**
     * filtre par service(
     *                      -Sport, -Jeux,  -Etude,  -Monument, -Cafeteria )
     * @param v
     */
    public void Sport(View v){
        Toast.makeText(getApplication(),"Sport",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Sport");
    }
    public void Etude(View v){
        Toast.makeText(getApplication(),"Etude",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Etude");
    }
    public void Monument(View v){
        Toast.makeText(getApplication(),"Monument",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Monument");
    }
    public void Cafeteria(View v){
        Toast.makeText(getApplication(),"Cafeteria",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Cafeteria");
    }
    public void Jeux(View v){
        Toast.makeText(getApplication(),"Jeux",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Jeux");
    }

}
