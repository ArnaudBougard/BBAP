package com.eu.fpms.bbap;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDrinksheetActivity extends AppCompatActivity {
    

    //DECLARATION
    ListView listView;
    ImageButton imageButton;
    String[] beerName = {"orval","chimay bleue","rochefort 8"};
    int[] imageId = {R.drawable.orval, R.drawable.chimay_bleue, R.drawable.rochefort};


    ArrayList<String> drinkArrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_drinksheet);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        imageButton = (ImageButton)findViewById(R.id.btn_alert_dialog);
        listView = (ListView) findViewById(R.id.dynamicListView);

        drinkArrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(UserDrinksheetActivity.this, android.R.layout.simple_list_item_1, drinkArrayList);
        listView.setAdapter(adapter);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BUILD ALERTDIALOG
                AlertDialog.Builder theBuilder = new AlertDialog.Builder(UserDrinksheetActivity.this);

                theBuilder.setTitle("Fils,une bière se déguste avec Sagesse!");
                theBuilder.setItems(beerName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),beerName[which]+" ajoutée à votre drinksheet", Toast.LENGTH_SHORT).show();

                       drinkArrayList.add(beerName[which]);
                       adapter.notifyDataSetChanged();
                    }
                });

                //ad.show();
                //open_dialog(v,ad);
                theBuilder.setNegativeButton("Stop! je suis déjà charette!",null);
                AlertDialog alertDialog = theBuilder.create();
                alertDialog.show();
            }


        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //DATABASE
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

    }




   /* public void open_dialog(View view, AlertDialog dialog){

        AlertDialog.Builder theBuilder = new AlertDialog.Builder(UserDrinksheetActivity.this);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_layout,null);
        ListView listView = (ListView)findViewById(R.id.listview_alertDialog);

        listView.setAdapter(new CustomAdapter(this));

        theBuilder.setView(row);

        dialog = theBuilder.create();
        dialog.show();

    }*/



    public double algorithmeAlcoolemie(double volume, double taux, int poids, int taille, int age, String sexe){

        double taux_alco = 0.0;
        double masse_ethanol = 0.0;
        double K = 0.0;
        double C = 0.0;
        double IMC;
        double IMG;


        masse_ethanol = (volume * taux);

        IMC = poids / (taille*taille);

        if(sexe == "Homme") {

            IMG = (1.2 * IMC) + (0.23 * age) - 16.2;
            K = 0.7;

            if(IMG < 15.0){

                C = 0.9;

            }
            else if (IMG >= 15.0 && IMG <= 20 ){

                C = 1.0;

            }
            else if (IMG > 20){

                C = 1.1;

            }

        }
        else if(sexe == "Femme"){

            IMG = (1.2 * IMC) + (0.23 * age) - 5.4;
            K = 0.6;

            if(IMG < 25.0){

                C = 0.9;

            }
            else if (IMG >= 25.0 && IMG <= 30 ){

                C = 1.0;

            }
            else if (IMG > 30){

                C = 1.1;

            }

        }
        else{

            System.out.println("ERREUR SEXE INDÉFINI OU ENDOMMAGÉ !");

        }

        taux_alco = masse_ethanol / (poids * K * C);

        return taux_alco;

    }

    public double algorithmeCalories(int poids, int taille, int age, String sexe, double kcal){

        double kcal_quot = 0.0;
        double kcal_pourcent = 0.0;

        if(sexe == "Homme") {

            kcal_quot = ( 9.99*poids + 6.25*taille - 5*age + 5 ) * 1.3;

        }
        else if(sexe == "Femme") {

            kcal_quot = ( 9.99*poids + 6.25*taille - 5*age - 161 ) * 1.3;

        }

        kcal_pourcent = kcal / kcal_quot;

        return kcal_pourcent;

    }

    public double algorithmeTemps(String sexe, double taux_alco){

        double temps = 0.0;

        if(sexe == "Homme"){

            if(taux_alco > 0.5){

                temps = ( taux_alco - 0.5 ) / 0.1;

            }
            else{

                temps = 0.0;

            }

        }
        else if(sexe == "Femme"){

            if(taux_alco > 0.5){

                temps = ( taux_alco - 0.5 ) / 0.085;

            }
            else{

                temps = 0.0;

            }

        }

        return temps;
        // Le temps est en heures !
    }


}
