package com.example.lenovo.test_pour_projet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * LAUNCH ACTIVITY : LANCEMENBNT DE L'APPLICATION
 * VISUALISATION DU LOGO POLYTECH
 * Created by lenovo on 20/10/2016.
 */
public class Launch_activity extends Acceuil{



    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
       finish();
        System.exit(0);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_polytech);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Launch_activity.this, Acceuil.class);
                Launch_activity.this.startActivity(mainIntent);
                Launch_activity.this.finish();
            }
        }, 3000);
    }


}
