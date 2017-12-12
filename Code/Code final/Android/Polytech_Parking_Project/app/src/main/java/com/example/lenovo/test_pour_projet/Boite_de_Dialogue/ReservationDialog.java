/*
package com.example.lenovo.test_pour_projet.Boite_de_Dialogue;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.lenovo.test_pour_projet.Main_Activity;
import com.example.lenovo.test_pour_projet.R;
import com.example.lenovo.test_pour_projet.Reserver;

*/
/**
 * Created by lenovo on 23/10/2016.
 *//*



public class ReservationDialog extends Dialog
{
    LayoutInflater inflater;
    View v;

    public ReservationDialog(Context context) {
        super(context);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater=getActivity().getLayoutInflater();
        v=inflater.inflate(R.layout.boite_dialogue_confirmation,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
       return builder.create();
    }
}




*/
/*




public class ReservationDialog extends Dialog implements android.view.View.OnClickListener  {

        public Activity c;// activité en cours
        public Dialog d;// biote de dialogue
        public Button confirmer, annuler;

        public ReservationDialog(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.boite_dialogue_confirmation);
            confirmer = (Button) findViewById(R.id.btn_yes);
            annuler = (Button) findViewById(R.id.btn_no);
            confirmer.setOnClickListener(this);
            annuler.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_yes:
                    Intent intent;
                    intent = new Intent(this.c, Reserver.class);
                    c.startActivity(intent);
                    break;
                case R.id.btn_no:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }
    }
*//*


*/
