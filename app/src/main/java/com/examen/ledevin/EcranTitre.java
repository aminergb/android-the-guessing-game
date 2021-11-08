package com.examen.ledevin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Activite Titre : représente le menu de l'application
public class EcranTitre extends AppCompatActivity {
    private Button btnjouer,btnstats;
    private Intent ToActJeu , ToActStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //attribution du layout activity_ecran_titre à l'activite EcranTitre
        setContentView(R.layout.activity_ecran_titre);
        //recuperation des bouttons du layout

        btnjouer = (Button) findViewById(R.id.jouerBtn);

        btnstats = (Button) findViewById(R.id.StatsBtn);
        //creation des intents pour le demarrage des activites
        ToActJeu = new Intent(this,EcranJeu.class);
        ToActStats = new Intent(this,EcranStats.class);
        //set clicklinsteners
        btnjouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(ToActJeu);
            }
        });
        btnstats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(ToActStats);
            }
        });

    }


}
