package com.example.chenhian.gpslocation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class PrecisionSettingsActivity extends Activity implements AdapterView.OnItemSelectedListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precision_settings);
        SharedPreferences prefs = getSharedPreferences("PrecisionSelection",0 );
      //  SharedPreferences prefs = getSharedPreferences(0);


        Spinner precisionSettings = (Spinner) findViewById(R.id.spinner);
        precisionSettings.setSelection(prefs.getInt("spinnerSelection", 0));

    }

    protected void onPause() {
        super.onPause();
        Spinner precisionSettings = (Spinner) findViewById(R.id.spinner);
        SharedPreferences.Editor editor = getSharedPreferences("PrecisionSelection",0).edit();
        int selectedPosition = precisionSettings.getSelectedItemPosition();
        editor.putInt("spinnerSelection", selectedPosition);
        editor.commit();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}


