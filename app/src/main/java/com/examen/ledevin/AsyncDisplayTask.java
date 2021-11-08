package com.examen.ledevin;

import android.content.Context;

import android.os.AsyncTask;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
//class asynctask qui s'occupe de la recuperation des données de la BDD et de les afficher sur le layout concerné
public class AsyncDisplayTask extends AsyncTask<Void,Stats,Void> {
    Context ctx;
    TextView tvscore_recent , tvmax_score,tvmin_score,tvnb_won,tvnb_lose,tvlast_date;

    //constructeur
    public AsyncDisplayTask(Context ctx, TextView tvscore_recent, TextView tvmax_score, TextView tvmin_score, TextView tvnb_won, TextView tvnb_lose,TextView tvlast_date) {
        this.ctx = ctx;
        this.tvscore_recent = tvscore_recent;
        this.tvmax_score = tvmax_score;
        this.tvmin_score = tvmin_score;
        this.tvnb_won = tvnb_won;
        this.tvnb_lose = tvnb_lose;
        this.tvlast_date = tvlast_date;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Stats... stats) {
        // stats : object Stats
//affichage des données récupérées sous forme d'objet Stats dans les TextViews concernnés

        tvscore_recent.setText(tvscore_recent.getText().toString()+": "+stats[0].getScore_recent());
        tvmax_score.setText(tvmax_score.getText().toString()+": "+stats[0].getScore_max());
        tvmin_score.setText( tvmin_score.getText().toString()+": "+stats[0].getScore_min());
        tvnb_lose.setText(tvnb_lose.getText().toString()+": "+stats[0].getGames_lost());
        tvnb_won.setText(tvnb_won.getText().toString()+": "+stats[0].getGames_won());

//conversion du type String en type Date
        SimpleDateFormat sdfShort=new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = null;
        try {
            if(stats[0].getDate_current()!=null){
                currentDate = sdfShort.parse(stats[0].getDate_current());

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


//conversion du type Date en type string (pour lui appliquer le format date complet )
       if(currentDate!=null) {
            String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
//affichage de la date sur le layout : la date sera affiché en fonction du language de l'appareil (une traduction automatique)
            tvlast_date.setText(tvlast_date.getText()+" : "+currentDateString);
        } else tvlast_date.setText(R.string.no_game_no_date);



    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    //recuperation des données sur la bdd en utilisant un workthread (un thread en background)
    protected Void doInBackground(Void... voids) {

        BdOperations bddevin = new BdOperations(ctx);
        Stats stats=  bddevin.getAllData();
        //publier le resultat sur le UIThread
        publishProgress(stats);


        return null;
    }


}
