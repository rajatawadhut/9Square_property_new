package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.nine__square.property.R;
import com.nine__square.property.Utils.ConfirmAddress;
import com.nine__square.property.Utils.VolleySingleton;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PlacePicker extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Address> addresslist1 = null;
    LatLng latLng2;


    String locality;
    Button btn;
    private final static int PLACE_PICKER_REQUEST = 999;
    private final static int LOCATION_REQUEST_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);

        Toast.makeText(PlacePicker.this, "Please Wait a Minute...", Toast.LENGTH_LONG).show();


        String loca, city, state;

        loca = VolleySingleton.getInstance(getApplicationContext()).getsearchlocality();
        city = VolleySingleton.getInstance(getApplicationContext()).getsearchcity();
        state = VolleySingleton.getInstance(getApplicationContext()).getsearchstate();

        locality = loca + " "+ city + " " + state;


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    LOCATION_REQUEST_CODE );


            if(locality != null || locality.equals("")){
                Geocoder geocoder = new Geocoder(this);
                try {
                    addresslist1 = geocoder.getFromLocationName(locality,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address = addresslist1.get(0);
                latLng2 = new LatLng(address.getLatitude(), address.getLongitude());

                System.out.println("Locatooooooo :"+ latLng2);

            }



        mapFragment.getMapAsync(this);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


//                    mMap.setMyLocationEnabled(true);
//                    mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//                        @Override
//                        public void onMyLocationChange(Location location) {
//
//                            System.out.println("Location :"+ location);
//                            LatLng ltlng=new LatLng(location.getLatitude(),location.getLongitude());
//                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
//                                    ltlng, 16f);
//                            mMap.animateCamera(cameraUpdate);
//                        }
//                    });
//                    Location location = mMap.getMyLocation();

                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng);

                            markerOptions.title(getAddress(latLng));
                            mMap.clear();
                            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                                    latLng, 17.0f);
                            mMap.animateCamera(location);
                            mMap.addMarker(markerOptions);
                        }
                    });



                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng2);
        mMap.clear();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2,17.0f));
        mMap.addMarker(markerOptions);

    }


    private String getAddress(LatLng latLng){

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {

                ft.remove(prev);
            }
            ft.addToBackStack(null);
            DialogFragment dialogFragment = new ConfirmAddress(PlacePicker.this);

            Bundle args = new Bundle();
            args.putDouble("lat", latLng.latitude);
            args.putDouble("long", latLng.longitude);
            args.putString("address", address);
            dialogFragment.setArguments(args);
            dialogFragment.show(ft, "dialog");
            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return "No Address Found";

        }


    }


}