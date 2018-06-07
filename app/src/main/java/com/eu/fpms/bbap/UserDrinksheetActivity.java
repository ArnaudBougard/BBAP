package com.eu.fpms.bbap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class UserDrinksheetActivity extends AppCompatActivity {

    ListView listView;
    String[] beername = {"orval","chimay bleue","rochefort 8"};
    Integer[] imageId = {R.drawable.Orval, R.drawable.chimay_bleue, R.drawable.rochefort};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_drinksheet);

        listView = (ListView) findViewById(R.id.dynamicListView);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

    }

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
