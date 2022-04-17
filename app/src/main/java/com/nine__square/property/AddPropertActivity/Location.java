package com.nine__square.property.AddPropertActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nine__square.property.Avtivities.PlacePicker;
import com.nine__square.property.Avtivities.SearchCity;
import com.nine__square.property.Avtivities.SearchLocality;
import com.nine__square.property.Avtivities.SearchState;
import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.VolleySingleton;

public class Location extends AppCompatActivity {

    EditText address, projectname,city, locality, state, otherlocality;
    Button btnnext, btnback, placepick;
    String cityG = "", localityG = "", addressG = "", projectnameG = "", stateG = "";



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" Location");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        locality = findViewById(R.id.locality);
        otherlocality = findViewById(R.id.otherlocality);
        address = findViewById(R.id.address);
        projectname = findViewById(R.id.projectname);
        btnnext = findViewById(R.id.btnnext);
        btnback = findViewById(R.id.btnback);
        placepick = findViewById(R.id.placepick);



        ListingData.getInstance(getApplicationContext()).usertype();
        ListingData.getInstance(getApplicationContext()).type();
        ListingData.getInstance(getApplicationContext()).category();
        ListingData.getInstance(getApplicationContext()).categorytype();




        address.setText("");
        VolleySingleton.getInstance(getApplicationContext()).setsearchaddress("");

        locality.setText("");
        VolleySingleton.getInstance(getApplicationContext()).setsearchlocality("");

        state.setText("Maharashtra");
        VolleySingleton.getInstance(getApplicationContext()).setsearchstate("Maharashtra");

        city.setText("Nagpur");
        VolleySingleton.getInstance(getApplicationContext()).setsearchcity("Nagpur");




//        privater.setChecked(true);
//        sharing = "Private";

        placepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otherlocalityG;
                otherlocalityG = otherlocality.getText().toString().trim();


                if(idvalid()){
                    if(VolleySingleton.getInstance(getApplicationContext()).getsearchlocality().equals("Add Location")) {
                        VolleySingleton.getInstance(getApplicationContext()).setsearchlocality(otherlocalityG);
                        startActivity(new Intent(Location.this, PlacePicker.class));
//                        startActivity(new Intent(Location.this, PlacePickerActivity.class));
                    }else{
                        startActivity(new Intent(Location.this, PlacePicker.class));
//                        startActivity(new Intent(Location.this, PlacePickerActivity.class));
                    }
                }

            }
        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locality.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchlocality("");
                startActivity(new Intent(Location.this, SearchCity.class));
            }
        });

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchcity("");
                locality.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchlocality("");
                startActivity(new Intent(Location.this, SearchState.class));
            }
        });

        locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Location.this, SearchLocality.class));
            }
        });





        city.setText(ListingData.getInstance(getApplicationContext()).city());
        state.setText(ListingData.getInstance(getApplicationContext()).state());
        locality.setText(ListingData.getInstance(getApplicationContext()).locality());
        address.setText(ListingData.getInstance(getApplicationContext()).address());
        projectname.setText(ListingData.getInstance(getApplicationContext()).projectname());

        if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Update")){
            address.setVisibility(View.VISIBLE);
            VolleySingleton.getInstance(getApplicationContext()).setsearchstate(ListingData.getInstance(getApplicationContext()).state());
            VolleySingleton.getInstance(getApplicationContext()).setsearchlocality(ListingData.getInstance(getApplicationContext()).city());
            VolleySingleton.getInstance(getApplicationContext()).setsearchlocality(ListingData.getInstance(getApplicationContext()).locality());
            VolleySingleton.getInstance(getApplicationContext()).setsearchaddress(ListingData.getInstance(getApplicationContext()).address());

        }


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateG = state.getText().toString().trim();
                cityG = city.getText().toString().trim();
                localityG = locality.getText().toString().trim();
                addressG = address.getText().toString().trim();
                projectnameG = projectname.getText().toString().trim();

                if(stateG.isEmpty()){
                    showmessage("Please Select State");
                }
                else if(cityG.isEmpty()){
                    showmessage("Please Select City");
                }
                else if(localityG.isEmpty()){
                    showmessage("Please Select Locality");
                }

                else if(addressG.isEmpty()){
                    showmessage("Please enter address");
                }


                /*else if(VolleySingleton.getInstance(getApplicationContext()).getsearchaddress().isEmpty()){
                    showmessage("Please Pick Address");
                }*/
                else if(localityG.equals("Add Location")){
                    String otherlocalityG;
                    otherlocalityG = otherlocality.getText().toString().trim();
                    if(otherlocalityG.isEmpty()){
                        showmessage("Please Enter Other Locality");
                    }else{
                        localityG= otherlocalityG;

                        ListingData.getInstance(getApplicationContext()).addlocation(stateG, cityG, addressG, localityG, projectnameG);
                        startActivity(new Intent(Location.this, PropertyDetails.class));

                    }

                }

                else{
                    ListingData.getInstance(getApplicationContext()).addlocation(stateG, cityG, addressG, localityG, projectnameG);
                    startActivity(new Intent(Location.this, PropertyDetails.class));
                }



            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


    }

    private boolean idvalid() {

        String otherlocalityG;
        otherlocalityG = otherlocality.getText().toString().trim();

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchcity().isEmpty()){
            showmessage("Please Select City");
            return false;
        }

        else if(VolleySingleton.getInstance(getApplicationContext()).getsearchlocality().isEmpty()){
            showmessage("Please Select Locality");
            return false;
        }
        else if(VolleySingleton.getInstance(getApplicationContext()).getsearchlocality().equals("Add Location") && otherlocalityG.isEmpty()){
            showmessage("Please Enter Other Locality");
            return false;
        }
//        Toast.makeText(Location.this, "Please Wait a Minute...", Toast.LENGTH_SHORT).show();

        return true;
    }

    private void showmessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onResume() {



        if(VolleySingleton.getInstance(getApplicationContext()).getsearchaddress() != null){
            if(!VolleySingleton.getInstance(getApplicationContext()).getsearchaddress().isEmpty()){
                address.setVisibility(View.VISIBLE);
            }
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchaddress();
            address.setText(citysearch);
        }

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchcity() != null && ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchcity();
            city.setText(citysearch);
        }

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchstate() != null && ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchstate();
            state.setText(citysearch);
        }

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchlocality() != null && ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchlocality();
            locality.setText(citysearch);
        }

        if(locality.getText().toString().trim().equals("Add Location")){
            otherlocality.setVisibility(View.VISIBLE);
        }else{
            otherlocality.setVisibility(View.GONE);
        }


        super.onResume();
    }


}
