

package com.example.lenovo.test_pour_projet;
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 28/09/2016.
 * Document ressource :
 * https://github.com/codepath/android_guides/wiki/Using-the-RecyclerView
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<MyParking> list_Parking ;
    private View.OnClickListener clickListener; // gestion boutton
    private RecyclerView.OnItemTouchListener onItemTouchListener;
    private OnItemClickListener onItemClickListener;
    // MyViewHolder.OnButtonItemClickListener ReserveButtonListener;
    // POUR LES HOLDER http://stackoverflow.com/questions/16948937/skipped-60-frames-the-application-may-be-doing-too-much-work-on-its-main-thread
    private View mSelectedView;
    private int mSelectedPosition;
    private RecyclerView mRecyclerView;


    int nbParking;
    Main_Activity mainActivity;
    boolean acces= true;



    //private CustomItemClickListener listener;
    Context mContext; //

    private LayoutInflater mInflater;
    //LES CONSTRUCTEURS
// on ajoute un constructeur prenant en entrée une liste

    public MyAdapter(List<MyParking>list){
        this.list_Parking=list;
    }

    // Autre contructeur a parametre
    public MyAdapter(Context mContext,ArrayList<MyParking>data,CustomItemClickListener listen){
        this.list_Parking=data;
        this.mContext=mContext;

    }

    // CONSTRUCTEUR context et activité
    public MyAdapter(Context Context,Main_Activity mainActivity){
        this.list_Parking= new ArrayList<MyParking>();
        this.mContext=Context;
        LayoutInflater li = (LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        this.mainActivity=mainActivity;
    }


    // Fonction permettant de créer les viewHolder
//et par la meme indiquer la vue à Inflater ( a partir des layout.xml
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup,int itemType){
        // View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cellule_parking,viewGroup,false);
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cellule_parking, viewGroup, false);
        // LIEN  A VOIR ; http://pastebin.com/t7Mudy8H

        return new MyViewHolder(view,this);
    }

    public int getCount(){
        return list_Parking.size();
    }

    public Object getItem(int position){
        return list_Parking.get(position);
    }

    public long getItemId(int position){
        return position;
    }
    @Override
// c'est ici que nous allons remplr notre cellule avec le texte image de chaque MyParking
    public void onBindViewHolder(final MyViewHolder myViewHolder,final int position) {
        final MyParking myParking = list_Parking.get(position);
        myViewHolder.bind(myParking);
        //http://stackoverflow.com/questions/28379302/add-clicklistner-for-button-inside-a-cardview-populated-using-a-recyclerview
        // https://www.bignerdranch.com/blog/recyclerview-part-1-fundamentals-for-listview-experts/
        myViewHolder.parentView.setSelected(list_Parking.contains(position));


    }

    // selecting items


    @Override
    public int getItemCount(){
        return list_Parking.size();
    } // recupere la taille de list_parking


    public int getNbParking(){
        return nbParking;
    } // recupere le numero du parking
    public void setNbParking(int nb){
        nbParking=nb;
    }





    public interface OnItemClickListener{
        public void onItemClick(MyViewHolder item, int position);
    }

    public OnItemClickListener getOnItemClickListener(){
        return onItemClickListener;
    }

    public  int Selection(int position, MyParking parking){ // GESTION DES ACCES
        list_Parking.add(position,parking);

        if (acces==true){
            acces=false;
            return position; // pour la communiquer à la base de donnée

        }
        else{

            return 5555; // erreur reservation
        }



    }
    /**
     * Created by lenovo on 28/09/2016.
     * Il faut créer un Adapter qui doit étendre RecyclerView.Adapter. cet adapter est typé pour fon
     * ctionner avec une certaine classe de ViwHolder ( object qui va garder les référence vers les vues de
     * de chaque cellule.
     */

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View parentView;// The view which holds all the other views
        private TextView m_textViewParkingName;
        private TextView m_textViewDescription;
        private ImageView m_ImageParking;
        private TextView m_textViewNbPlaceDisponible;
        int Nb_ParkingPosition;


        private MyAdapter adapteur;
        // itemView est la vue correspondant à 1 cellule
        public MyViewHolder(View itemView, MyAdapter parent) {

            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            // on effectue les findView pour récupérer les identifiants
            m_textViewParkingName = (TextView) itemView.findViewById(R.id.nom_parking);// identifiant pour le nom du Parking
            m_textViewDescription = (TextView) itemView.findViewById(R.id.description_parking);// identifiant pour la description
            m_ImageParking = (ImageView) itemView.findViewById(R.id.imageParking);// id pour l'image du parking
            m_textViewNbPlaceDisponible=(TextView)itemView.findViewById(R.id.View_Nb_places); // recuperation de l'identifiant contenant le nombre de place disponibles

            this.parentView=itemView;
            this.adapteur=parent;
        }




        @Override // AVEC LE THIS ET L'IMPLEMENTATION
        public  void onClick(final View v) {


            v.setSelected(false);
            v.setSelected(true);
            v.setSelected(false);


            // IL FAUT TROUVER UN MOYEN DE RECUPERER LA VALEUR DE LA POSITION
            Log.v(" CLIQ CLIQ", "" + getAdapterPosition());
            Nb_ParkingPosition = getAdapterPosition();
            adapteur.setNbParking(Nb_ParkingPosition);// recupération de la position sélectionnée


        }




        // on ajoute une fonction pour remplir la cellule en fonction d'un MyParking
        public void bind(MyParking myObject) {
            // recuperation des données d'un object myObject de type MyParking
            m_textViewParkingName.setText(myObject.getM_nomParking());
            m_textViewDescription.setText(myObject.getM_DescriptionParking());
            m_ImageParking.setImageResource(myObject.getM_imageUrl());
            m_textViewNbPlaceDisponible.setText(String.valueOf(myObject.getM_Nb_placedisponible()));

        }


        // retour la position du parking selectionné
        public int GetPositionParking(){return Nb_ParkingPosition;}


    }

}
