package com.examen.ledevin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;
//Activite Jeu : permet de recuperer le nombre à deviner ainsi que le nombre à selectionné afin de les envoyés sous forme de bundles vers activite (EcranFin) via l'intent ToActFin
//recalcule le nb aléatoire à deviner à chaque oncreate ou onresume
public class EcranJeu extends AppCompatActivity {
   private int nb_devin ;
    private Button btn1 ,btn2,btn3,btn4;
    private Random rand;
    private Intent ToActFin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //attribution du layout activity_ecran_jeu à l'activite EcranJeu
        setContentView(R.layout.activity_ecran_jeu);

      //creation d'un nombre aleatoire
        rand = new Random();
        nb_devin = rand.nextInt(4)+1;
        //Creation de l'intent ToActFin qui permet de demarrer l'activite FinPartie

         ToActFin = new Intent(this,FinPartie.class);
         //mettre le nombre aléatoire dans l'intent sous forme de bundle
         ToActFin.putExtra("guess",nb_devin);
 //recuperation des bouttons du layout
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
//set clicklinsteners
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //mettre le nombre selectione dans l'intent sous forme de bundle
                ToActFin.putExtra("click",1);
                startActivity(ToActFin);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //mettre le nombre selectione dans l'intent sous forme de bundle
                ToActFin.putExtra("click",2);
                startActivity(ToActFin);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //mettre le nombre selectione dans l'intent sous forme de bundle
                ToActFin.putExtra("click",3);
                startActivity(ToActFin);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //mettre le nombre selectione dans l'intent sous forme de bundle
                ToActFin.putExtra("click",4);
                startActivity(ToActFin);
            }
        });




    }

    @Override
    protected void onStart() {

        super.onResume();
        super.onStart();
    }

    @Override
    protected void onStop() {

        super.onResume();
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onResume();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        //creation d'un nouveau nombre aleatoire a chaque retour vers l'activite
        rand = new Random();

        nb_devin = rand.nextInt(4)+1;
        ToActFin.putExtra("guess",nb_devin);
        super.onResume();
    }
}
