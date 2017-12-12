package com.example.lenovo.test_pour_projet;
                  // CLASSE RESERVER : UTILISER POUR CONFIRMER LA SELECTIONNE
// L'UTILISATEUR PEUT SOIT CONFIRMER LA RESERVATION DU PARKING SOIT ANNULER ET REVENIR A LACTIVITE
//PRECEDENTE/

// AIDE INTERNET
// https://github.com/miskoajkula/MySQL-PHP-JSON/blob/master/app/src/main/java/practise/mysql_php_json/MainActivity.java


// BIBLIOTHEQUE UTILISEE
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Pour le stockage des données

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


//envoie données au fichier php

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONObject;


/**
 * Created by lenovo on 29/09/2016.
 */
public class Reserver extends Main_Activity {
    private String PARKING ="parking.txt"; // nom du fichier d'enregistrement
    private FileOutputStream mFile=null; // on initialise le fichier a zero



    //private boolean reservation;

    // Pour envoyer les données du parking réservé à l'activité Reservation
    private String Nom_parking_reserve; //Parking renvoyé si réservation
    private int Numero_BDD ; // Nombre envoyé à la base de données
    // cet int correspond à la position du parking qui correspondra dans la base de donnée
    // envoyant la position , nous allons décompter -1 dans le parking correspondant à Numero_BDD
    String insertUrl = "http://192.168.43.179/ProjetParking/PHP/ProjetBDD/Modification_BDD.php";// fichier php qui va traiter
    // la demander de reservtion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        setTitle("Réservation");

        // Recupération des données
        TextView View_Nom_Parking=(TextView)findViewById(R.id.Name_parking_reserve); // on alloue
        // a un textView l'identifiant de la textView dans laquelle on affichera le nom du parking selectionné
        Nom_parking_reserve=getIntent().getStringExtra("Nom_parking_reserve");
        View_Nom_Parking.setText(Nom_parking_reserve);
        // On récupère le numero du Parking selectionné
        // cette donnée sera récupéré et alloué dans la base de donnée
        // pour déconter le parking selectionné
        Numero_BDD=getIntent().getIntExtra("Numero_parking_reserve",0);

        Button button_Confirmer= (Button) findViewById(R.id.button_confirmer);
        button_Confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Quand on clique sur confirmer on affiche une boite de dialogue
                // confirmant la confirmation
               Log.v("Reservation Parking"+Numero_BDD,"CONFIRMEE"); // BoiteDialogueReservation(); // n'est plus gerer par cette activité


                Log.v("E","ENregi");

                Enregistrement();
                // recuperation du numero de parking;
                // stockage du numero parking
                // on quitte l'application

                Numero_BDD=Numero_BDD*10+1;

                sentData(Numero_BDD); // on envoie la donnée au fichier php


                // premier chiffre numero parking
                // deuxieme chiffre   1: reservation
                // recuperation du numero de parking;
                // stockage du numero parking
                // on quitte l'application
                Intent RetourIntent= new Intent(Reserver.this,Acceuil.class);
                startActivity(RetourIntent); // on retourne a l'acceuil
            }
        });



    }






    // retour le numero du Parking enregistré
    int GetNumeroBDD()
    {
        return Numero_BDD;
    }


    //....................METHODE D'ENGRISTREMENT  INTERNE ET BASE DE DONNEEES................

    public void Enregistrement() {
       String Enregistrement_parking=String.valueOf(GetNumeroBDD());// on recupere
        // le String du numero envoyer à la base de donnée
        // Methode pour l'enregistrement
        // Enregistrement de la donnée du parking pour
        // permettra à l'utilisateur de retrouvé le parking
        // Selectionné , après fermerture et réouverture de l'applcation
        Log.v("Enregistrement",Enregistrement_parking);
        try {
            mFile = openFileOutput(PARKING, Context.MODE_PRIVATE);
            // Mode_private: le fichier est accessible uniquement par l'application
            }
        catch(FileNotFoundException e) { // Si il n'arrive pas a creer le fichier
        e.printStackTrace();
        }
        try {
            mFile.write(Enregistrement_parking.getBytes());
            Log.v(" document",Enregistrement_parking);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }




    //---------------------GESTION DU BOUTONS RETOUR DU TELEPHONE------------
    // Il faut reconfigurer l'action activé avec l'appuie du boutton retour
    // cela permet d'éviter les erreurs entre les activités avec les boutton physique du
    // téléphone
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent intent= new Intent(Reserver.this,Main_Activity.class);
        startActivity(intent);
    }


    //---------------------ENVOIE DONNEE VERS LA BASE DE DONNEE
    /* Quand l'utilisateur reserver la données passe par le fichier php qui va modifier
    * la base de donnée. Nous envoie donc le code P*10+S P: numero parking  S: 0 SORTIE 1 RESERVER*/

    public void sentData(final int Donnee) {

        Map<String, String> map=new HashMap<String, String>();
        map.put("ID_Parking",String.valueOf(Donnee));
        Log.i("JSON", "ENREGISTREMENT");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                insertUrl,
                //Creation de l'objet JSON a envoyer au fichier php  contenue dans notre Map a
                new JSONObject(map),

                //utiliser si on veut egalement changer des valeurs dans le fichier android studio via la reception de donnee php
                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {
                        Log.i("JSON", "REQUETE");
                    }
                },
                //Uniquement en cas d'erreur, seul les boites de dialogues d'erreur sont accepter (exemple toast) sinon exit
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(request);
    }
}

