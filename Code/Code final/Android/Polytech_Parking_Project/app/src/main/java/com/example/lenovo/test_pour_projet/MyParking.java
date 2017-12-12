package com.example.lenovo.test_pour_projet;

import java.util.List;

import static android.R.id.list;

/**
 * Created by lenovo on 28/09/2016.
 * Classe object parking pour remplir les cellule du recyclerview
 */
public class MyParking {

    private String m_nomParking; // Nom du Parking
    private String m_DescriptionParking;// description du Parkin
    private int m_Nb_placedisponible; // nombre de place disponible sur le parking

    private int m_imageid; // identifiant de l'image correspondante au parking


    public MyParking(String nomParking, String Description, int imageurl, int place_disponible){
        this.m_DescriptionParking=Description;
        this.m_nomParking=nomParking;
        this.m_imageid=imageurl;
        this.m_Nb_placedisponible=place_disponible;

    }


    // Recuperation des données d'un objet MyParking
    public String getM_nomParking(){
        return m_nomParking;
    }

    public String getM_DescriptionParking()
    {
        return m_DescriptionParking;
    }

    public int getM_imageUrl(){
        return m_imageid;
    }

    public int getM_Nb_placedisponible(){return m_Nb_placedisponible;}

    // set
    public  void setM_Nb_placedisponible(int ent){
        m_Nb_placedisponible=ent;
    }
    public void setM_nomParking( String nomParking) {m_nomParking=nomParking;}
    public void setM_DescriptionParking ( String description ) {m_DescriptionParking = description;}
    public void setM_imageid (int id_image) { m_imageid=id_image;}





}
