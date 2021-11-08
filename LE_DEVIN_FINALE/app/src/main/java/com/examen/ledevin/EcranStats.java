package com.examen.ledevin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//Activite  EcranStats : affichage des stats
public class EcranStats extends AppCompatActivity {
    TextView tvscore_recent , tvmax_score,tvmin_score,tvnb_won,tvnb_lose,tvdate_last_game;
    Button btnRetour;
    Intent ToActTitre;
    AsyncDisplayTask showtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creation d'intent
        ToActTitre = new Intent(this,EcranTitre.class);
        BdOperations dbdevin = new BdOperations(this);
        super.onCreate(savedInstanceState);
        //attribution du layout activity_ecran_stats à l'activite EcranStats
        setContentView(R.layout.activity_ecran_stats);
//recuperation des elements du layout
        tvscore_recent = findViewById(R.id.tvscore_recent);
        tvmax_score =findViewById(R.id.tvscoremax);
        tvmin_score =findViewById(R.id.tvscoremin);
        tvnb_lose =findViewById(R.id.tvnblose);
        tvnb_won =findViewById(R.id.tvnbwon);
        tvdate_last_game =findViewById(R.id.tvlastgameplayed);
        btnRetour = findViewById(R.id.btnretour);
//set clicklistener
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                startActivity(ToActTitre);

            }
        });
        // creation et lancement de du thread Asyntask
        showtask = new AsyncDisplayTask(this,tvscore_recent,tvmax_score,tvmin_score,tvnb_won,tvnb_lose,tvdate_last_game);
        showtask.execute();




    }
}
