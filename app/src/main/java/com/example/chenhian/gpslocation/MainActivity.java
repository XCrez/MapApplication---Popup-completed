package com.example.chenhian.gpslocation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends ActionBarActivity {
    Button btnShowLocation;
    GPSTracker gps;
    double latitude;
    double longitude;
    SharedPreferences prefs;

    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv =(TextView) findViewById(R.id.locationText);
        btnShowLocation = (Button) findViewById(R.id.show_Location);

        prefs = getSharedPreferences("PrecisionSelection",0 );
        Toast.makeText(getApplicationContext(),"For debugging : Current selection" + prefs.getInt("spinnerSelection", -1) , Toast.LENGTH_LONG).show();


        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(MainActivity.this);
                //Opens popup with current location and button to open google maps
                if (gps.canGetLocation()) {

                    if (prefs.getInt("spinnerSelection", -1) == 2) {

                        LayoutInflater layoutInflater
                                = (LayoutInflater) getBaseContext()
                                .getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = layoutInflater.inflate(R.layout.popup, null);
                        TextView tv = (TextView) popupView.findViewById(R.id.locationText);
                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                        tv.setText("For debugging purposes, Message to be removed : Latitude = " + latitude + " Longitude = " + longitude  + "Placeholder : User is at Fort Road");
                        final PopupWindow popupWindow = new PopupWindow(
                                popupView,
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                        Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                        btnDismiss.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                popupWindow.dismiss();
                            }
                        });
                        Button launchmapbtn = (Button) popupView.findViewById(R.id.launchmap); // Button to launch google maps
                        launchmapbtn.setOnClickListener(new Button.OnClickListener() {
                            public void onClick(View v) {
                                try {
                                    Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + latitude + " " + longitude)); // Prepare intent
                                    startActivity(geoIntent); // Initiate lookup
                                } catch (Exception e) {
                                }
                            }
                        });

                        popupWindow.showAsDropDown(btnShowLocation, -220, -1200);


                    }

                    if (prefs.getInt("spinnerSelection", -1) == 1) {
                        LayoutInflater layoutInflater
                                = (LayoutInflater) getBaseContext()
                                .getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = layoutInflater.inflate(R.layout.popup, null);
                        TextView tv = (TextView) popupView.findViewById(R.id.locationText);
                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                        tv.setText("For debugging purposes, Message to be removed in final version : Latitude = " + latitude + " Longitude = " + longitude + "    Placeholder : User is in Mountbatten Area");
                        final PopupWindow popupWindow = new PopupWindow(
                                popupView,
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                        Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                        btnDismiss.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                popupWindow.dismiss();
                            }
                        });
                        Button launchmapbtn = (Button) popupView.findViewById(R.id.launchmap); // Button to launch google maps
                        launchmapbtn.setOnClickListener(new Button.OnClickListener() {
                            public void onClick(View v) {
                                try {
                                    Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + "MountBatten MRT")); // Prepare intent
                                    startActivity(geoIntent); // Initiate lookup
                                } catch (Exception e) {
                                }
                            }
                        });

                        popupWindow.showAsDropDown(btnShowLocation, -220, -1200);


                    }

                    if (prefs.getInt("spinnerSelection", -1) == 0) {
                        Toast.makeText(getApplicationContext(), "Placeholder : User is in the East Side of Singapore", Toast.LENGTH_LONG).show();

                    }
                }
                //Toast message telling user to on gps if off.
                else {
                    Toast.makeText(getApplicationContext(), "User GPS is currently disabled, please enable GPS and try again.", Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.about:{
                aboutMenuItem();
                break;
            }
            case R.id.action_settings:{
                settingsMenuItem();
                break;
            }
            case R.id.Precision_settings:{
                precisionSettingsMenuItem();
                break;
            }

        }


        return super.onOptionsItemSelected(item);
    }

    private void precisionSettingsMenuItem() {
        Intent PrecisionIntent = new Intent(this, PrecisionSettingsActivity.class);
        startActivity(PrecisionIntent);

    }

    private void settingsMenuItem() {

    }

    private void aboutMenuItem() {
        new AlertDialog.Builder(this).setTitle("About").setMessage("MapApplications v1.4").setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO Auto generated method stub
            }
        }).show();}

}



