package com.eu.fpms.bbap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDrinksheetActivity extends AppCompatActivity {


    //DECLARATION

    //int[] imageId = {R.drawable.orval, R.drawable.chimay_bleue, R.drawable.rochefort};

    ImageButton imageButton;
    ArrayList<String> drinkArrayList = new ArrayList<String>();;
    AlertDialog alert;

    ArrayList<String> drinkList;

    ArrayAdapter<String> adapter;
    ListView listView;

    MyListAdapter adapter2;

    Button calculAlcool;
    Button calculCaL;
    Button calculTemps;







    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_drinksheet);

        //INITIALIZATION
        imageButton = (ImageButton)findViewById(R.id.btn_alert_dialog);
        listView = (ListView) findViewById(R.id.dynamicListView);
        drinkList = new ArrayList<String>();

        //adapter = new ArrayAdapter<String>(UserDrinksheetActivity.this, android.R.layout.simple_list_item_1, drinkList);
        //listView.setAdapter(adapter);

        adapter2 = new MyListAdapter(this, R.layout.row_item ,drinkList);
        listView.setAdapter(adapter2);

        //DATABASE
        final DatabaseHelper myDb = new DatabaseHelper(this);

        //ON CLICK FOR ALERT DIALOG BUTTON
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drinkArrayList.clear();

                //RETRIEVE DATA FROM DB TO PUT IN ALERTDIALOG
                Cursor cursor = myDb.fetchDrinkInfo();
                while (cursor.moveToNext()){

                    String drinkName = cursor.getString(1);
                    drinkArrayList.add(drinkName);
                }


                //METHOD: CREATE + SET UP ALERTDIALOG IN THE LAYOUT
                showDialog();

            }


        });


        //ON CLICK FOR ALCOHOL CALCUL BUTTON
        calculAlcool = (Button)findViewById(R.id.button_calcul_alcool);

        calculAlcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"EN COURS DE CONSTRUCTION",Toast.LENGTH_SHORT).show();
                //focntionCalculAlcool(myDb);

                //double resultat = algorithmeAlcoolemie(150,5,69,181,22,"Homme");
                //Toast.makeText(getApplicationContext(),"TAUX CALCULE = " + resultat,Toast.LENGTH_LONG).show();
            }
        });


        //ON CLICK FOR CALORIE BUTTON
        calculCaL = (Button)findViewById(R.id.button_calcul_calorie);

        calculCaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"EN COURS DE CONSTRUCTION",Toast.LENGTH_SHORT).show();
            }
        });

        //ON CLICK FOR CALCUL TIME BUTTON
        calculTemps = (Button)findViewById(R.id.button_calcul_temps);

        calculTemps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"EN COURS DE CONSTRUCTION",Toast.LENGTH_SHORT).show();
            }
        });




    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void showDialog(){

        AlertDialog.Builder theBuilder = new AlertDialog.Builder(UserDrinksheetActivity.this);

        //add data
        int numberOfDrinks = drinkArrayList.size();
        final String[] names = new String[numberOfDrinks];

        for(int i=0; i<numberOfDrinks; i++){

            names[i] = drinkArrayList.get(i);
        }


        theBuilder.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),names[which] + " ajoutée à votre drinksheet", Toast.LENGTH_SHORT).show();

                drinkList.add(names[which]);

                //adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();


            }
        });

        theBuilder.setNegativeButton("Stop! je suis déjà charette!",null);
        alert = theBuilder.create();
        alert.setTitle("Fils,une bière se déguste avec Sagesse!");
        alert.show();

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class MyListAdapter extends ArrayAdapter<String>{

        private int layout;

        public MyListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            ViewHolder mainViewHolder = null;




            if(convertView == null){

                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout,parent,false);

                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.tvBeername);
                viewHolder.quantity = (EditText) convertView.findViewById(R.id.etQuantity);
                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tvTime);

                //setTag allow to store any objet
                convertView.setTag(viewHolder);
            }

            else {

                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.title.setText(getItem(position));
                mainViewHolder.tv_time.setText(getCurrentTime());
            }

            return convertView;
        }

    }


    public class ViewHolder{

        TextView title;
        EditText quantity;
        TextView tv_time;

    }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getCurrentTime(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(calendar.getTime());

        return time;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //CETTE FONCTION EST EN COURS DE CONSTRUCTION

    public void focntionCalculAlcool(DatabaseHelper db){

        int sizeDrinkList = drinkList.size();
        Drink boisson;
        String userName = "ju";
        User nomUtilisateur;
        double volume = 5;
        double tauxTotalTemp = 0;


        nomUtilisateur = db.fetchUserInfo(userName);

        for(int i=0;i<sizeDrinkList;i++) {


            String beerName = drinkList.get(i);
            boisson = db.getAllDrinkInfo(beerName);

            //tauxTotalTemp = tauxTotalTemp + algorithmeAlcoolemie(volume,boisson.getVol(), nomUtilisateur.getWeight(),nomUtilisateur.getHeight(),nomUtilisateur.getAge(),nomUtilisateur.getSex());
        }

         Toast.makeText(getApplicationContext(),"Taux Calculé = " + tauxTotalTemp,Toast.LENGTH_LONG).show();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
