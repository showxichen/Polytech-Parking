package com.example.testtuto;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;//plus rapide et performant
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ville;
    Button insert, show;
    TextView result;

    RequestQueue requestQueue;// car on utilise volley
    String showUrl = "http://192.168.0.26/ProjetParking/PHP/Tuto2/TESTxi.php";
  // String showUrl = "http://192.168.43.179/ProjetParking/PHP/Tuto2/TESTxi.php";
    // String showUrl="http://api.androidhive.info/volley/person_array.json";
    String insertUrl = "http://192.168.0.26/ProjetParking/PHP/Tuto2/TESTxi2.php";
    //String insertUrl = "http://192.168.43.179/ProjetParking/PHP/Tuto2/TESTxi2.php";
    String jsonresponse;
    String TAG = MainActivity.class.getSimpleName();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;




    //Envoie des donnees json via Map
    Map<String, String> map=new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert = (Button) findViewById(R.id.insert);
        show = (Button) findViewById(R.id.show);
        result = (TextView) findViewById(R.id.result);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


       //Map peut contenir plusieur donnees
        map.put("param","1");
        map.put("param","1");
        //params=ID_Parking dans le fichier reel, il faudra faire une condition du genre if




        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("JSONJSONJSON1111111", "OOOOOUUUUUUIIIII");
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        showUrl,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray server_response) {
                                Log.d(TAG, server_response.toString());

                                try {

                                    //JSONArray server_response = response.getJSONArray("server_response");
                                    //On cree des objets individuels pour notre
                                    jsonresponse = "  ";
                                    for (int i = 0; i < server_response.length(); i++) {
                                        JSONObject Ville = server_response.getJSONObject(i);
                                        //ici c'est en fonction des donnees json du fichier php
                                        String idville = Ville.getString("ID_ville");
                                       // String nomville = Ville.getString("Nom_ville");

                                        // affichage des resultats
                                        //result.append(idville+" "+nomville + "\n");
                                        jsonresponse += idville+ "\n\n";
                                    }
                                    //result.append("===\n");
                                    result.setText(jsonresponse);
                                } catch (JSONException e) {
                                    Log.i("CATCH", "NOOOOOOOOOOON");
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("ErrorListener", "NOOOOOOOOOOON");
                                /*VolleyLog.d(TAG, "Error: " + error.getMessage());
                                Toast.makeText(getApplicationContext(),
                                        error.getMessage(), Toast.LENGTH_SHORT).show();*/
                            }
                        });

                requestQueue.add(jsonArrayRequest);

            }
        });



/*
//BOUTON AJOUT DANS LA BASE SANS ENVOYER DE PARAMETRE
        insert.setOnClickListener(new View.OnClickListener() {

                @Override
                 public void onClick(View view) {
                    StringRequest request = new StringRequest(Request.Method.POST,
                            insertUrl,
                            new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        }
                    },
                            new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(request);
                }
            });*/












        //BOUTON AJOUT DANS LA BASE EN ENVOYANT DES PARAMETRES
        insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        insertUrl,
                        //Creation de l'objet JSON a envoyer au fichier php  contenue dans notre Map a
                        new JSONObject(map),
                        //utiliser si on veut egalement changer des valeurs dans le fichier android studio via la reception de donnee php
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
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
        });

        }
    }
