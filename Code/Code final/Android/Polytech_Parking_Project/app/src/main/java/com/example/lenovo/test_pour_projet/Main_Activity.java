package com.example.lenovo.test_pour_projet;
                        // MAIN ACTIVITY // VUE DE LA LISTE DES PARKINGS
/*
Dans cette activité l'utilisateur pourra visualiser la liste des parkings disponibles
Nous  lirons les données de la base de donnée via le fichier PHP pour recuperer le nombres de place
disponible sur chaque parking
 */

import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;


import android.net.Uri;

import android.os.Bundle;

import android.support.v7.app.AlertDialog;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


// Pour la connection avec la base de donnée via le fichier PHP
// de BDD-> PHP-> JSON-> ANDROID
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//


/**
 * Created by lenovo on 30/09/2016.
 */
public class Main_Activity extends Acceuil
{  // ACTIVITE VUE DES LISTE DES PARKINGS
    //------------------------DECLARACTION--------------------------

    // POUR LA RECYCLERVIEW
    private RecyclerView recyclerView;// recyclerView est une liste d'un même objet
    private List<MyParking> list_parking = new ArrayList<>(9);// on utilise une Liste de parking
    boolean acces = true;// boolean pour tester l'acces à la reservation
    // on la met à true. En effet quand on passe de l'activité confirmation reservation à vue parking ( main_activity)
    // cet acces doit avoir par defaut true pour eviter les bugs

    private int ParkingReserve = 0; // Contient le numero du parking reservé Donnée renvoyée au fichier PHP
    private int[] placeDisponible = new int[9]; // tableau pour mettre à jour le nombre
    // de place disponible dans la liste de parking;
    // à remplir par le fichier JSON




    //------- GESTION CONNECTION BDD-PHP-JSON-ANDROID
    RequestQueue requestQueue;// car on utilise volley

    // ADRESSE URL  du fichier PHP
    // ADRESSE IP ( VOIR IPCONFIG SUR VOTRE ORDINATEUR )  A CHANGER POUR VOS TESTS
    // Tous les appareils doivent être connectés sur la même adresse IP
    String showUrl = "http://192.168.43.179/ProjetParking/PHP/ProjetBDD/Affichage_BDD.php";


    String TAG = Main_Activity.class.getSimpleName();// contient les données





    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        acces = getIntent().getBooleanExtra("acces", true);// on recupere la valeur de l'acces obtenu via l'activité Acceuil qui teste si on est proche
        // de l'université ou pas
        Log.v("Accessibilité Reser ", " " + acces);// affichage LOG

        // AFFICHAGE DE LA VUE........................................................
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTitle("Liste des parking de l'université d'orléans");

        //------------//////////---CONNEXION BASE DE DONNEE---///////////////////////---------------

        // 1- GESTION CONNECTION BDD-PHP-JSON-ANDROID.............................................
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        gestionJson(); // Tableau contenant les données


       // Recherche mise à jour chaque seconde
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               gestionJson(); // Tableau contenant les données
            }
        }, 0, 1000);









        /////////////// GESTION RECYCLER VIEW..........................................................

        recyclerView = (RecyclerView) findViewById(R.id.recyclerList_parking);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView

        ajouterParkingTEST();// on rempli la liste de Parking
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // disposition lineaire verticale
        // de la recyclerview
        final MyAdapter adapter = new MyAdapter(list_parking); // pour manipuler les click
        // un MyAdapter, pour fournir notre liste de parking.
        //cet adapter servira à remplir notre recyclerview




        recyclerView.setAdapter(adapter); // on effectue l'affichage de la liste de parking










        /// GESTION DES BOUTTON..........................................................
        //1- BOUTON RESERVATION..................

        Button myButton = (Button) findViewById(R.id.button_reservation); // on recupere l'identifiant
        // du boutton "reservé"
        myButton.setOnClickListener(new View.OnClickListener() {// listener qui réagit aux click
            // un object listener and un object qui contient une methode qui sera call lors de l'évenement
            public void onClick(View v) {

                // recupère l'etat du fichier de reservation
                int lecture=getIntent().getIntExtra("lecture",0);
                if ((lecture!=55) && (lecture!=5555)) // Si le fichier parking.txt contient une réservation de parking
                {
                    Log.v("Err","Rervation en cours");
                    Erreur_Reservation_En_Cours();
                }
                else {
                    ParkingReserve = adapter.getNbParking(); // on récupere le numero du parking selectionné
                    Log.i("myTest", "on choisi le parking");// pour verifier si ça marcher !D VOIR LOGCATE
                    // pour activiter l'activité suivante on crée un intente;

                    Log.i("Demander Reservation", "Nb" + ParkingReserve);
                    if( list_parking.get(ParkingReserve).getM_Nb_placedisponible()!=0) {
                        Reservation(ParkingReserve, list_parking.get(ParkingReserve), v);// fonction en charge
                        // de la reservation du parking
                        // avec les tests sur la variable acces
                        // et demande de confirmation
                    }
                    else{
                        Log.i("Erreur","Parking"+ParkingReserve+"Complet");
                        ParkingComplet(); // Signale à l'utilisateur que le parking
                        // est complet
                    }
                }

            }
        });

        // 2- BOUTON ANNULER
        // si on veut   // annuler et revenir à la page d'acceuil
        Button myButtonCancel = (Button) findViewById(R.id.button_annulerVueListeParking);

        myButtonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Activity.this, Acceuil.class);
                // On passe de l'activité liste parking à l'acceuil
                startActivity(intent);
            }
        });



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    // FONCTION POUR AJOUTER LES PARKING A LA MATRICE MYPARKING
    // Fonction ajouterParking : contient le données contenu dans la list_parking utilisé
    // PROPOSITION D'AMELIORATION : IMPORTER LES IMAGES DE GOOGLE STREET VIEW !

    private void ajouterParkingTEST() { // Instancie les differents parking et rempli la liste
        // de parking
      // on recupere les données du parking pour le nombre de place disponible

        list_parking.add(new MyParking("Parking Site Vinci", "Situé aux abords de Polytech Orleans site Vinci", R.drawable.parkingvinci, placeDisponible[0]));
        list_parking.add(new MyParking("Parking Résidence des Charmes", "Parking Gratuit devant la Résidences des charmes", R.drawable.parking_charmes, placeDisponible[1]));
        list_parking.add(new MyParking("Parking Résidence Aristote", "Parking situé à 200 mètres de la faculté de lettre", R.drawable.parking_rue_de_tours, placeDisponible[2]));
        list_parking.add(new MyParking("Parking rue de Chartres", "Situé devant le parc floral et la faculté de Sciences", R.drawable.parking_rue_chatres, placeDisponible[3]));
        list_parking.add(new MyParking("Parking Université Chateau", "A 100 mètres du Terrain omnisports", R.drawable.parking_chateau, placeDisponible[4]));
        list_parking.add(new MyParking("Parking Polytech Galilée PUBLIC", "", R.drawable.parkingiut_galilee, placeDisponible[5]));
        list_parking.add(new MyParking("Parking Polytech Galilée (2)", "Accés réservé aux personnels de Polytech Orléans", R.drawable.parking_galilee1, placeDisponible[6]));
        list_parking.add(new MyParking("Parking IUT", "Situé rue chateaux roux", R.drawable.parking_iut_rue_chateaux_roux, placeDisponible[7]));

    }


    //............................... FONCTION RESERVATION.......................................................
    /* L'application va analyser la position GPS de l'utilisateur. Si cette position est éloignée de la
     * localisation GPS de l'université le programme lancera une bulle d'erreur
      * Dans le cas contraire, l'application regardera ensuite le nombre de place disponible dans le parking voulu
      * Si le parking est complet un message d'erreur sera envoyé
      * Si un autre parking a été selectionné un message d'erreur sera envoyé
      * */


    public int Reservation(final int PositionParking, MyParking parking, View v) {

        if (acces == true) { // l'utilisateur peut réserver
            // L'application lance l'activité suivante

            SelectionParking(v,PositionParking); // on enregistre le numero du parking selectionnée

            acces = true;
            return PositionParking; // retour du numero du parking selectionné

        } else {// si l'utilisateur ne peut pas réserver
            // on affiche une boite de dialogue d'erreur
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Erreur réservation");
            builder.setMessage("Vous ne vous trouvez pas à proximité de l'université");
            AlertDialog dialog = builder.create();
            dialog.show();
            return 5555;
        }
    }


    // Retourne la liste de parking de type MyParking
    public List<MyParking> getList_parking() {
        return list_parking;
    }



    ////------------------------BOITES DE DIALOGUE  CONFIRMATION -------------------------------------

    public void SelectionParking(final View itemView, final int PositionParking){
        //http://forums.xamarin.com/discussion/comment/58142#Comment_58142
        //si ça ne marche pas
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(itemView.getContext());
                dialog.setContentView(R.layout.boite_dialogue_confirmation);
                dialog.setTitle("Reservation");

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_no);
                // SI L'UTILISATEUR CLIQUE SUR "ANNULER"
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dialog.dismiss(); // disparition de la boite et retour dans la liste
                    }
                });

                Button dialogButtonOUI = (Button) dialog.findViewById(R.id.btn_yes);

                // SI L'UTILISATEUR CLIQUE SUR "CONFIRMER"
                dialogButtonOUI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent=new Intent(Main_Activity.this,Reserver.class);
                        myIntent.putExtra("Nom_parking_reserve", list_parking.get(PositionParking).getM_nomParking());// on récupere le parking
                        // selectionné
                        myIntent.putExtra("Numero_parking_reserve", PositionParking); // onrécupère la position du parking pour la base de donnée
                        myIntent.putExtra("Image_parking_reserve", list_parking.get(PositionParking).getM_imageUrl()); // on récupere l'image du parking
                        startActivity(myIntent);
                        dialog.cancel(); // on ferme la boite de dialogue
                        dialog.getContext().startActivity(myIntent); // On commence l'activité reserver

                    }
                });

           dialog.show();
            }
        });


    }

/////----------------------FONCTION AUTOGENEREE PAR ANDROID STUDIO--------------------------

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main_ Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lenovo.test_pour_projet/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main_ Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lenovo.test_pour_projet/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    //---------------------GESTION DU BOUTONS RETOUR DU TELEPHONE------------
    // Il faut reconfigurer l'action activé avec l'appuie du boutton retour
    // cela permet d'éviter les erreurs entre les activités avec les boutton physique du
    // téléphone
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent intent= new Intent(Main_Activity.this,Acceuil.class);
        startActivity(intent);
    }



    ///-----------------------JSON---------------------------------------------------------------------
    public void gestionJson(){ // renvoie le tableau comprenant le nombre de place disponible
        // dans chaque parking
        // json
        // Nous preferons passer par ce tableau et pas par placeParking pour eviter les erreurs

        Log.i("JSONJSONJSON1111111", "OOOOOUUUUUUIIIII"); // phrase teste

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(showUrl,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray server_response) {
                        Log.d(TAG, server_response.toString());

                        try { // Tentative de connetion au fichier php
                            Log.i("JSON", " YES  CONNEXION EFFECTUEE");


                            for (int i = 0; i < server_response.length(); i++) {
                                JSONObject Place = server_response.getJSONObject(i);
                                //ici c'est en fonction des donnees json du fichier php
                                int disponibilite = Place.getInt("Disponibilite");

                                Log.i("JSON", "RECUPERATION DES DONNEES");

                                list_parking.get(i).setM_Nb_placedisponible(disponibilite);
                               // on recuperere pour chaque parking le nombre de place disponible
                                // referencié dans le fichier JSON
                            }

                        } catch (JSONException e) {
                            // si dans la lecture l'application rencontre un problem
                            Log.i("CATCH", "PROBLEME DE LECTURE");
                            for (int i=0 ; i<list_parking.size() ;i++) {

                                list_parking.get(i).setM_Nb_placedisponible(30);
                            }
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() { // s'il n'arrive pas à se connecter à la base
                    // de données
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ERREUR", "CONNEXION IMPOSSIBLE");
                        ErrorConnexion();
                    }

                });

        requestQueue.add(jsonArrayRequest);

        Log.i("RequestQueue", "Operation effectuee");

    }



/// BOITE DE DIALOGUE
    // Si l'utilisateur n'est pas connecté au réseaux

    public void ErrorConnexion(){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("PROBLEME CONNEXION");
            builder.setMessage("Vérifier votre connexion internet");// affichage du nom du parking selectionné
            builder.setCancelable(false)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // Perform Your Task Here--When No is pressed
                            dialog.cancel();
                            Intent myIntent = new Intent(Main_Activity.this,Acceuil.class);
                            startActivity(myIntent);
                        }
                    }).show();
         // on montre la boite de dialogue
        }


    public void ParkingComplet(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Parking Complet");
        builder.setMessage("Vous ne pouvez plus reserver ce parking");// affichage du nom du parking selectionné
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


}

