package com.nine__square.property.newMap;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.VolleySingleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlacePickerActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private ImageView markerImage;
    private ImageView mImgBack;
    private ImageView markerShadowImage;
    private CurrentPlaceSelectionBottomSheet bottomSheet;
    private FloatingActionButton fab;
    private double latitude;
    private double longitude;
    private boolean showLatLong = true;
    private float zoom = 18.0F;
    private boolean addressRequired = true;
    private String shortAddress = "";
    private String fullAddress = "";
    private boolean hideMarkerShadow;
    private int markerDrawableRes = -1;
    private int markerColorRes = -1;
    private int fabColorRes = -1;
    private int primaryTextColorRes = -1;
    private int secondaryTextColorRes = -1;
    private List<Address> addresses = new ArrayList<>();
    private ImageView mBtnSearchLocation;
    private CardView mBtnSelectAddress;
    private CardView mBtnSearchBar;
    private String mCountryCode = "";
    private FusedLocationProviderClient fusedLocationClient;
    private ImageView mBtnCurrentLocation;
    private boolean isSearchFromDropdown = false;
    private String googleMapApiKey;

    List<Address> addresslist1 = null;
    LatLng latLng2;
    String locality;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ak_activity_place_picker);
        String loca, city, state;


        loca = VolleySingleton.getInstance(getApplicationContext()).getsearchlocality();
        city = VolleySingleton.getInstance(getApplicationContext()).getsearchcity();
        state = VolleySingleton.getInstance(getApplicationContext()).getsearchstate();

        locality = loca + " " + city + " " + state;


        if (locality != null || locality.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addresslist1 = geocoder.getFromLocationName(locality, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addresslist1.get(0);
            latLng2 = new LatLng(address.getLatitude(), address.getLongitude());

            System.out.println("Locatooooooo :" + latLng2);

        }


//        turnGPSOn();
        getIntentData();
        initViews();
//        updateCurrentLocation();
    }

    private void updateCurrentLocation() {
        if (latitude == 0 && longitude == 0) {
            if (PermissionUtility.checkLocationFineAndCoarsePermission(this))
                startLocationUpdates();
        }
    }

    private void initViews() {

        initPlaces();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);

        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheet.showCoordinatesTextView(showLatLong);

        TextView mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText(R.string.search_address);
        markerImage = findViewById(R.id.marker_image_view);
        mImgBack = findViewById(R.id.img_back);
        markerShadowImage = findViewById(R.id.marker_shadow_image_view);
        fab = findViewById(R.id.place_chosen_button);
        mBtnSelectAddress = findViewById(R.id.btnSelectAddress);

        mBtnSearchBar = findViewById(R.id.cvSearchAddress);
        mBtnSearchLocation = findViewById(R.id.img_search);
        mBtnCurrentLocation = findViewById(R.id.ivCurrentLocation);

        mBtnSelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addresses != null) {
                    AddressData addressData = new AddressData(latitude, longitude, shortAddress, addresses);
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(Constants.ADDRESS_INTENT, addressData);
                    setResult(RESULT_OK, returnIntent);

                    String Lat2, Long2, LatLong;
                    Lat2 = String.valueOf(latitude);
                    Long2 = String.valueOf(longitude);
                    LatLong = Lat2+","+Long2;
                    fullAddress = addresses.get(0).getAddressLine(0);


                    VolleySingleton.getInstance(PlacePickerActivity.this).setsearchaddress(fullAddress.toString());
                    ListingData.getInstance(PlacePickerActivity.this).setLatLog(LatLong);
                    onBackPressed();

//                    Intent intent = new Intent(PlacePickerActivity.this, AddTaskActivity.class);
//                    intent.putExtra("getlatlng",latitude+","+longitude);
//                    intent.putExtra("getaddress", shortAddress);
//                    startActivity(intent);
//                    finish();
//                    finish();
                } else {
                    if (!addressRequired) {
                        AddressData addressData = new AddressData(latitude, longitude, shortAddress, null);
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(Constants.ADDRESS_INTENT, addressData);
                        setResult(RESULT_OK, returnIntent);
//                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.no_address), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addresses != null) {
                    AddressData addressData =new AddressData(latitude, longitude, addresses);
                    Intent returnIntent =new Intent();
                    returnIntent.putExtra(Constants.ADDRESS_INTENT, addressData);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else {
                    if (!addressRequired) {
                        AddressData addressData =new AddressData(latitude, longitude, null);
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(Constants.ADDRESS_INTENT, addressData);
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.no_address), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });*/


//        mBtnCurrentLocation.setVisibility(View.GONE);
        mBtnCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionUtility.checkLocationFineAndCoarsePermission(PlacePickerActivity.this))
                    turnGPSOn();
                    startLocationUpdates();

            }
        });
        setIntentCustomization();


    }

    private void initPlaces() {
        try {
            // Initialize Places.
//            Places.initialize(this, getResources().getString(R.string.google_maps_api_key));
            Places.initialize(this, googleMapApiKey);
            // Create a new Places client instance.
            PlacesClient placesClient = Places.createClient(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getIntentData() {
        if (getIntent() != null) {

            this.googleMapApiKey = this.getIntent().getStringExtra(Constants.GOOGLE_MAP_API_KEY);
            this.latitude = this.getIntent().getDoubleExtra(Constants.INITIAL_LATITUDE_INTENT, 0.0D);
            this.longitude = this.getIntent().getDoubleExtra(Constants.INITIAL_LONGITUDE_INTENT, 0.0D);
            this.showLatLong = this.getIntent().getBooleanExtra(Constants.SHOW_LAT_LONG_INTENT, false);
            this.addressRequired = this.getIntent().getBooleanExtra(Constants.ADDRESS_REQUIRED_INTENT, true);
            this.hideMarkerShadow = this.getIntent().getBooleanExtra(Constants.HIDE_MARKER_SHADOW_INTENT, false);
            this.zoom = this.getIntent().getFloatExtra(Constants.INITIAL_ZOOM_INTENT, 18.0F);
            this.markerDrawableRes = this.getIntent().getIntExtra(Constants.MARKER_DRAWABLE_RES_INTENT, -1);
            this.markerColorRes = this.getIntent().getIntExtra(Constants.MARKER_COLOR_RES_INTENT, -1);
            this.fabColorRes = this.getIntent().getIntExtra(Constants.FAB_COLOR_RES_INTENT, -1);
            this.primaryTextColorRes = this.getIntent().getIntExtra(Constants.PRIMARY_TEXT_COLOR_RES_INTENT, -1);
            this.secondaryTextColorRes = this.getIntent().getIntExtra(Constants.SECONDARY_TEXT_COLOR_RES_INTENT, -1);
        }
    }

    private void setIntentCustomization() {
        if (hideMarkerShadow)
            markerShadowImage.setVisibility(View.GONE);
        else
            markerShadowImage.setVisibility(View.VISIBLE);

        if (markerColorRes != -1) {
            markerImage.setColorFilter(ContextCompat.getColor(this, markerColorRes));
        }
        if (markerDrawableRes != -1) {
            markerImage.setImageDrawable(ContextCompat.getDrawable(this, markerDrawableRes));
        }
        if (fabColorRes != -1) {
            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, fabColorRes)));
        }
        if (primaryTextColorRes != -1) {
            bottomSheet.setPrimaryTextColor(ContextCompat.getColor(this, primaryTextColorRes));
        }
        if (secondaryTextColorRes != -1) {
            bottomSheet.setSecondaryTextColor(ContextCompat.getColor(this, secondaryTextColorRes));
        }

    }

    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng2);
        map.clear();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 17.0f));


        /*if (PermissionUtility.checkLocationFineAndCoarsePermission(this)) {
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
        }*/

        map.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                if (markerImage.getTranslationY() == 0f) {
                    markerImage.animate()
                            .translationY(-75f)
                            .setInterpolator(new OvershootInterpolator())
                            .setDuration(250)
                            .start();
                    if (bottomSheet.isShowing()) {
                        bottomSheet.dismissPlaceDetails();
                    }
                }
            }
        });

        map.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                markerImage.animate()
                        .translationY(0f)
                        .setInterpolator(new OvershootInterpolator())
                        .setDuration(250)
                        .start();

                if (isSearchFromDropdown) {
                    if (bottomSheet != null)
                        bottomSheet.setPlaceDetails(latitude, longitude, shortAddress, fullAddress);
                    isSearchFromDropdown = false;
                } else {
                    bottomSheet.showLoadingBottomDetails();
                    LatLng latLng = map.getCameraPosition().target;
                    latitude = latLng.latitude;
                    longitude = latLng.longitude;
                    AsyncTask.execute(new Runnable() {
                        public final void run() {
                            getAddressForLocation();
                            runOnUiThread(new Runnable() {
                                public final void run() {
                                    bottomSheet.setPlaceDetails(latitude, longitude, shortAddress, fullAddress);
                                }
                            });
                        }
                    });
                }

            }
        });

//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));

    }

    private void getAddressForLocation() {
        setAddress(latitude, longitude);
    }

    private void setAddress(double latitude, double longitude) {

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(latitude, longitude, 1);
            this.addresses = addresses;
            if (addresses != null && addresses.size() != 0) {
                fullAddress = addresses.get(0).getAddressLine(0);
                shortAddress = generateFinalAddress(fullAddress).trim();
                mCountryCode = addresses.get(0).getCountryCode();
            } else {
                shortAddress = "";
                fullAddress = "";
            }
        } catch (Exception e) {
            shortAddress = "";
            fullAddress = "";
            addresses = null;
            e.printStackTrace();
        }
    }

    private String generateFinalAddress(String address) {
        String strRtr;
        String[] s = address.split(",");
        if (s.length >= 3)
            strRtr = s[1] + "," + s[2];
        else if (s.length == 2)
            strRtr = s[1];
        else
            strRtr = s[0];
        return strRtr;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.AUTOCOMPLETE_PLACE_PICKER_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        if (data != null) {
                            Place place = Autocomplete.getPlaceFromIntent(data);
                            if (place.getLatLng() != null) {
                                //   bottomSheet.showLoadingBottomDetails();
                                isSearchFromDropdown = true;
                                latitude = place.getLatLng().latitude;
                                longitude = place.getLatLng().longitude;
                                shortAddress = place.getName();
                                fullAddress = place.getAddress();

                                Geocoder gcd = new Geocoder(this, Locale.getDefault());
                                List<Address> addresses = gcd.getFromLocation(latitude, longitude, 1);
                                if (addresses != null && addresses.size() != 0) {
                                    fullAddress = addresses.get(0).getAddressLine(0);
                                    shortAddress = getPlaceName(place.getName(), generateFinalAddress(fullAddress).trim());
//                                    shortAddress += " " +generateFinalAddress(fullAddress).trim();
                                    mCountryCode = addresses.get(0).getCountryCode();
                                }

                                if (map != null)
                                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }


    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        /*locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(1000)*/
        ;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
   /*     fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback(),
                Looper.getMainLooper());*/

        fusedLocationClient.requestLocationUpdates(locationRequest,
                new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        for (Location location : locationResult.getLocations()) {
                            Log.i("MainActivity ", "" + location.getLongitude());
                            //not getting current location updates every 2 minutes
                        }
                    };

                },Looper.getMainLooper());

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            if (map != null)
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));
                        }
                    }
                });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case Constants.MY_PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();

                } else {
//                    CustomDialogUtil.showAlert(mContext, getString(R.string.permission_popup_title), getString(R.string.location_permission_manually));
                }
                break;
        }
    }

    private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){ //if gps is disabled
            Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent1);
        }else{
            updateCurrentLocation();
        }
    }

    private String getPlaceName(String shortName, String fullName){

        String returnName = "";

        if (TextUtils.isEmpty(shortName)){
            if (!TextUtils.isEmpty(fullName))
                returnName = fullName;
        }else {
            if (TextUtils.isEmpty(fullName))
                returnName = shortName;
            else
                returnName = shortName + ", " + fullName;

        }
        return returnName;

    }
}
