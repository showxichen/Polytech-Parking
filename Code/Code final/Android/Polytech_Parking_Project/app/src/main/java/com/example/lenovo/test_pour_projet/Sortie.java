package com.example.lenovo.test_pour_projet;
            // CLASSE SORTIE
// UTILISE QUAND L'UTILISATEUR VEUT SIGNALER SA SORTIE DU PARKING QU'IL AVAIT PRECEDEMMENT
// RESERVER
// UTILISATION DU FICHIER DE STOCKAGE CREER A LA RESERVATION

// NOTE developper : voir stackoverflow probleme with calendar getcalendar

                 /// BIBLIOTHEQUE UTILISEE ////
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 02/10/2016.
 */
public class Sortie extends Main_Activity {
    List<MyParking> list_parking=new ArrayList<>();
    private int[] placeDisponible=new int[9];
    private String PARKING ="parking.txt"; // nom du fichier d'enregistrement
    private FileOutputStream mFile=null; // on initialise le fichier a zero

    // DONNEE POUR LA GESTION ENVOIE DE DONNEE AU PHP
    // ANDROID-> PHP-> BASE DE DONNE
    int NumeroReservation;// A RECUPERE  DE L'APPLICATION QUI A STOCKER LA DONNEE
    String insertUrl = "http://192.168.43.179/ProjetParking/PHP/ProjetBDD/Modification_BDD.php";// fichier php qui va traiter
    // la demander de reservtion




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sortie); // utilisation du layout sortie.xml
        setTitle("Polytech Parking");



        ajouterParking();// on instancie le parking

        final TextView nomParking = (TextView) findViewById(R.id.nom_parking_reserve);

        final ImageView imageParking= (ImageView)findViewById(R.id.imageReservation);
        Button myButtonSortie = (Button) findViewById(R.id.button_sortieVoiture); // on recupere l;e boutton "Signaler ma sortie

        // on recupere l'identifiant du parking, stocké dans le fichier de reservation
        NumeroReservation=getIntent().getIntExtra("lecture",0); // on récupere la donnée de lecture
        nomParking.setText(list_parking.get(NumeroReservation).getM_nomParking()); // on récupère le nom du parking
        imageParking.setImageResource(list_parking.get(NumeroReservation).getM_imageUrl());// recuperation de l'image


        // SI utilisateur veut confirmer sa sortie du parking

       myButtonSortie.setOnClickListener(new View.OnClickListener(){// quand on clique sur le boutton " signaler ma sortie


            public void onClick(View v){
                Log.i("JSONJSONJSON55", "OOOOOUUUUUUIIIII");

                int DonneeBDD=NumeroReservation*10;// NumeroReservation0
                Log.v("Sortie Parking"+NumeroReservation,"CONFIRMEE");
                Log.i("JSONJSONJSON", "OOOOOUUUUUUIIIII");
               sentData(DonneeBDD); // envoie de la demande au fichier PHP
                //Premier chiffre = numero Parking
                // Deuxieme chiffre = état si 0 désistement  si 1 reservation


                DeleteStockage();// L'application vide le fichier
                Intent intent= new Intent(Sortie.this,Acceuil.class);
                startActivity(intent);
                //System.exit(0); // L'APPLICATION SE FERME
           }
        });
    }




    // Pour initialiser le tableau !
    // AMELIORATION CHERCHER A METTRE EN PLACE UNE CLASSE AVEC UNE LISTE DE PAKKING DEFINIE
    private void ajouterParking() {



            RecuperationDonneeParking();
            list_parking.add(new MyParking("Parking Site Vinci", "Situé aux abords de Polytech Orleans site Vinci", R.drawable.parkingvinci,placeDisponible[0]));
            list_parking.add(new MyParking("Parking Résidence des Charmes", "Parking Gratuit devant la Résidences des charmes", R.drawable.parking_charmes,placeDisponible[1]));
            list_parking.add(new MyParking("Parking Résidence Aristote", "Parking situé à 200 mètres de la faculté de lettre", R.drawable.parking_rue_de_tours,placeDisponible[2]));
            list_parking.add(new MyParking("Parking rue de Chartres", "Situé devant le parc floral et la faculté de Sciences", R.drawable.parking_rue_chatres,placeDisponible[3]));
            list_parking.add(new MyParking("Parking Université Chateau", "A 100 mètres du Terrain omnisports", R.drawable.parking_chateau,placeDisponible[4]));
            list_parking.add(new MyParking("Parking Polytech Galilée PUBLIC", "", R.drawable.parkingiut_galilee,placeDisponible[5]));
            list_parking.add(new MyParking("Parking Polytech Galilée (2)", "Accés réservé aux personnels de Polytech Orléans", R.drawable.parking_galilee1,placeDisponible[6]));
            list_parking.add(new MyParking("Parking IUT", "Situé rue chateaux roux", R.drawable.parking_iut_rue_chateaux_roux,placeDisponible[7]));

    }



    // Recuperation des données via le fichier PHP via fichier JSON
    void RecuperationDonneeParking(){
        // Fonction en attendant php
        for (int i=0 ;i<list_parking.size();i++){
            placeDisponible[i]=30;
        }
    }


    public void DeleteStockage(){  // effacement du fichier à la fin
      String FILENAME ="parking.txt";

        EnregistrementSortie();

    }


    public void EnregistrementSortie() {
        String Enregistrement_parking="555";// on recupere
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
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            mFile.write(Enregistrement_parking.getBytes());

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }



    //.................. GESTION DU BOUTON RETOUR......................
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent intent= new Intent(Sortie.this,Acceuil.class);
        startActivity(intent);
    }



    //---------------------ENVOIE DONNEE VERS LA BASE DE DONNEE
    /* Quand l'utilisateur reserver la données passe par le fichier php qui va modifier
    * la base de donnée. Nous envoie donc le code P*10+S P: numero parking  S: 0 SORTIE 1 RESERVER*/

   public void sentData(final int Donnee) {


        Map<String, String> map=new HashMap<String, String>();
       Log.i("JSONJSONJSON55", "Ouiiiiiii");

       map.put("ID_Parking",String.valueOf(Donnee)); // on récupe la valeur Donnee pour ID_Parking présent dans le fichier pHP
       Log.i("JSONJSONJSON55", "RECUPERATION DONNEE");

       JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                insertUrl,
                //Creation de l'objet JSON a envoyer au fichier php  contenue dans notre Map a
                new JSONObject(map),
                //utiliser si on veut egalement changer des valeurs dans le fichier android studio via la reception de donnee php
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("JSON", "BIENVENUE");
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


