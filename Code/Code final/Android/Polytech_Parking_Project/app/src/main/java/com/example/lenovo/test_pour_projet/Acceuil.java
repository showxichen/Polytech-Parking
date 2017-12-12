package com.example.lenovo.test_pour_projet;
                // ACCEUIL DE L'APPLICATION

// DANS CETTE ACTIVITE L'UTILISATEUR DISPOSE DE DEUX CHOIX
// Soit il peut visualiser la liste des parkings disponible
// Soit il veut signaler sa sortie du parking précedemment reserve
// L'utilisateur n'a le droit de reserver qu'un seul parking

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import android.util.Log;
import android.view.View;
import android.widget.Button;

// Pour la lecture du fichier de stockage
// du parking précédemment réservé

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Acceuil extends Activity {
    private boolean acces=true;// variable boolean pour la condition de réservation  -
    private static int i=1;
    /* ATTENTION: This was auto-generated to implement the App Indexing API.
    * See https://g.co/AppIndexing/AndroidStudio for more information.
            */
    private GoogleApiClient client;
    private LocationManager locationManager;
    private LocationListener locationlistener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_polytech);


        setContentView(R.layout.activity_acceuil);
        setTitle("Polytech Parking"); // TITRE de l'activité



        // RECUPERATION DE LA LOCALISATION DU TELEPHONE
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationlistener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) { // methode qui contient l'action à réaliser en cas de
                // changement des coordonnées du gps
                //textView.append("\n"+location.getLatitude()+""+location.getLongitude());
                Log.v("localisation", location.getLatitude() + "    " + location.getLongitude());
                acces=VerificationGPS(location);// fonction vérification par rapport au donnée gps du centre de l'université d'Orleans

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si l'application n'a pas la permission d'utiliser le gps
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{ // L'application demande à l'utilisateur la permission d'utiliser le gps
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET // permission pour l'internet
                }, 10);
                return;
            }

        } else { // si l'application a la permission d'utiliser le gps
            // SI LES PERMISSIONS GPS sont obtenues
            // 1: L'application va récupérer les données gps du téléphone et appelera la fonction onLocationChange
            // du télephone . Cette methode va instancier un LocationListener qui va appeler la fonctin onLocationChange. Cette derniere appelle
            // la methode Verification GPS qui va verifier la localisation GPS par rapport à l'université

           locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationlistener);

            configureButton(); // configure Button gère les actions réalisées après avoir cliquer sur le boutton Reservé
            // et la valeur boolean acces qui a été modifier en fonction de la présence(true)
            // ou non ( false) de l'utilisateur à proximité de l'université.

            Log.v("AccessibilitéAA",""+acces); // affichage de le logcat pour verification programmeur

        }


        // FIN LOCALISATION

        // ------------SI L'UTILISATEUR DEMANDE LA VISUALISATION DU PARKING RESERVE--------------------
        Button buttonVueParkingReserve=(Button) findViewById(R.id.button_vue_reservations);
        buttonVueParkingReserve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

               int lecture=LectureStockage();// on recupere la position du parking
                // que l'on avait récupéré
                    if ((lecture==55) || (lecture==5555))// pas 5555 car je ne prendre que 1 bytes
                    {
                        Erreur_Non_Reservation();
                    }
                    else {
                    Log.v("PARKING RESERVE", " " + lecture);
                    Intent myItent = new Intent(Acceuil.this, Sortie.class);// intent de l'activité acceuil à l'activité sortie
                    myItent.putExtra("lecture", lecture);// putExtra pour envoyer la lecture dans l'activité sortie
                    startActivity(myItent);
                }
            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


// -----------------FONCTION Lecture Stockage-----------------------------

    /* L'application possède un fichier interne qui ne peut être ouvert que par l'application.
     * Ainsi à l'ouverture de l'application, nous regardons si ce fichier existe. Dans ce cas il
      * contient le numero du parking reserve
      * S'il ne contient rien ou qu'il n'existe pas il enverra une erreur 55
      * qui permettra de signaler à l'utilisateur qui peut ou pas reserver/sortir du parking */

    public int LectureStockage() {
        int lecture = 0;
        String PARKING = "parking.txt"; // nom du fichier de stockage
       // String string = "";// variable pour récupere les chaines de caractere contenues dans parking.txt
        StringBuffer fileContent = new StringBuffer(""); // initialise le buffer qui va lire le fichier
        FileInputStream myFile = null;
        try {
            myFile = openFileInput(PARKING);// tentative d'ouverture du fichier parking.txt
        } catch (FileNotFoundException e) {
            // si l'application ne trouve pas le fichier

            lecture = 5555; // si il y a une erreur l'application enverra 5555
            Log.e("login activity", "File not found: " + e.toString());
           // e.printStackTrace();
        }
        try {
            // si on a pu ouvrir le fichier
            // on commence a lire les caracteres présent dans ce dernier
            byte[] buffer = new byte[1]; //buffer pour recuperer les donnes présent dans parking.txt

            int length = 0; // initialisation de la taille a zero
            Log.v("Action", "On lit");
            if (myFile != null) {// si le fichier n'est pas nul

                while ((length = myFile.read(buffer)) != -1) {
                    // tant que la taille du contenu du fichier n'est pas atteinte

                    fileContent.append(new String(buffer));// on récupere les donnees
                    System.out.println("buffer appended" + i + "time");
                    i++;


                    myFile.read(buffer);
                }
            }
        } catch (IOException e) {
            lecture = 5555; // si il y a une erreur l'application enverra 5555
            e.printStackTrace();
        }
        // dans sortie et.setText(fileContent.toString());
        System.out.println(fileContent.toString());
        // SI le fichier contient donc des caracteres
        // 1/ On fait un test -> Nous avons remarqué que pour certains portable
            /* En effet certain android essayait de lire "" ou " " et voulait donc
            * convertir ce caractere nul en entier. Ainsi l'application crachait
            * Sur certains smartphone nous n'avons pas remarqué cet erreur
            * Nous avons donc fait le test pour eviter l'arret de l'application */
        if ( fileContent.toString()==" " || fileContent.toString()=="" )
        {
            lecture=5555;  //Erreur dans la lecture du fichier // L'utilisateur n'a pas encore
            // reservé
        }
        else{
            // Si l'application trouve un caractere dans le fichier
            // il va faire la conversion en entier
            Log.v("Un parking ","OO a été reserve");
            lecture = Integer.parseInt(fileContent.toString());// conversion string en int

        }
        Log.v("On recupere", "Nb" + lecture);


        return lecture;
    }


    // -------------BOITES DE DIALOGUE----------------------------------------------------//
    // METHODE si l'utilisateur n'a pas réservé de parking mes essaie de visualiser un parking réservé
    public void Erreur_Non_Reservation(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Aucune Réservation");
        builder.setMessage("Vous n'avez réservé aucun parking");// affichage du nom du parking selectionné
        builder.setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Perform Your Task Here--When No is pressed
                        dialog.cancel();
                    }
                }).show();
       // on montre la boite de dialogue
    }

    // Si l'utilisateur a déjà réservé un parking , il ne pourra pas acceder à la liste

    public void Erreur_Reservation_En_Cours(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Erreur réservation");
        builder.setMessage("Vous avez déjà une réservation en cours");// affichage du nom du parking selectionné
        builder.setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Perform Your Task Here--When No is pressed
                        dialog.cancel();
                    }
                }).show();// on montre la boite de dialogue
    }



    @Override
    public void onRequestPermissionsResult ( int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                   // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationlistener);
                    Log.v("PERMISSION","");
                configureButton();

                return;
        }
    }


    /////----------------CONFIGURATION BUTTON---- POUR VISUALISATION DES PARKINGS-------

    private void configureButton() {  // NOUS ALlONS LANCER L'ACTIVITE SUIVANTE : VUE DU PARKING
        Button myButton = (Button) findViewById(R.id.button_OK); // on recupere l'identifiant du boutton "reservé"
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              int lecture=LectureStockage();// on regarde si le fichier de stockage de réservation
                // est vide ou pas


                    Log.i("myTest", "Vue suivante on choisi le parking");// pour verifier si ça marcher !D VOIR LOGCATE
                    Intent myIntent= new Intent(Acceuil.this,Main_Activity.class);

                    myIntent.putExtra("acces",acces); // pour renvoyer la valeur d'acces
                    myIntent.putExtra("lecture",lecture); // pour renvoyer lecture
                    // si lecture =55 ou 5555 l'utilisateur pour consulter la liste
                    // mais ne pourra pas réserver de parking
                    // car ce dernier a déjà selectionner un parking
                    // on a pu reserver
                    startActivity(myIntent); // acces a la liste de parking



          }
        });

    }


    /// VERIFICATION LOCALISATION DE L'UTILISATEUR PAR RAPPORT A LA LOCALISATION DE L'UNIVERSITE
    public boolean VerificationGPS(Location location){


        if( (( location.getLatitude()>47.83)&& ( location.getLatitude()<47.849) )&&( ( location.getLongitude()<1.945) && (location.getLongitude()>1.92)) )
        {
            Log.v("Visiteur"," proche de l'université");
            return true;
        }
        else {
            Log.v("Visiteur STOP","Loin de l'université");
            return false;
        }
    }


 // CONFIGURATION BOUTTON RETOUR--------------------------------
 @Override
 public void onBackPressed() {
     // Do Here what ever you want do on back press;
     // finish();
    System.exit(0);
 }
}
