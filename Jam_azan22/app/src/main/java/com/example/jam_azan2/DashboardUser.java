package com.example.jam_azan2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DashboardUser extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button to_settings1;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);

        String languageToLoad  = "ms"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_dashboard_user);

        Calendar calendar= Calendar.getInstance();
        String currentDate= android.text.format.DateFormat.format("d MMMM yyyy",calendar).toString();
        TextView textViewDate = findViewById(R.id.normal_date);
        textViewDate.setText(currentDate);

        Calendar date = Calendar.getInstance();
        String dayToday = android.text.format.DateFormat.format("EEEE", date).toString();
        TextView myTextView = findViewById(R.id.day);
        myTextView.setText(dayToday);


        int dayOfMonth = 5;
        int monthOfYear = 10;
        int year = 2021;
        LocalDate dt = LocalDate.of(year, monthOfYear, dayOfMonth);
        HijrahDate hijrahDate = HijrahDate.from(dt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        String islamicDate = formatter.format(hijrahDate); // 07/03/1439
        TextView AZANView = findViewById(R.id.islamic_date);
        AZANView.setText(islamicDate);

//        Calendar islamic = Calendar.getInstance();
//        String islamicDate = android.text.format.DateFormat.format("dd MMMM yyyy", islamic).toString();
//        TextView AZANView = findViewById(R.id.islamic_date);
//        AZANView.setText(islamicDate);

        firebaseAuth = FirebaseAuth.getInstance();

        to_settings1 = (Button) findViewById(R.id.to_settings1);
        to_settings1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
    }


    public void openSettings() {
        Intent intent = new Intent(this, UserSettings_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}