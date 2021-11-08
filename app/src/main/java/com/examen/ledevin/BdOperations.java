package com.examen.ledevin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.Calendar;


public class BdOperations extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "devin.db";
    public static final String TABLE_NAME = "stats_table";
    public static final String id_player = "id_player";
    public static final String score_recent = "score_recent";
    public static final String score_min = "score_min";
    public static final String score_max = "score_max";
    public static final String games_won = "games_won";
    public static final String games_lost = "games_lost";
    public static final String date_last_game = "date_last_game";


    public BdOperations(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creation de la bdd
        db.execSQL(" CREATE TABLE IF NOT EXISTS " +TABLE_NAME+" ( "+score_recent+" INTEGER , "+score_min+"  INTEGER , "+score_max+"  INTEGER , "+games_won+" INTEGER , "+games_lost+" INTEGER , "+date_last_game+" DATE ) ");


      Initialize_db(db);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //MAJ de la bdd
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
//initialise la bdd : creation d'une ligne
    public void Initialize_db(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put(score_recent, 0);
        values.put(score_min, 0);
        values.put(score_max, 0);
        values.put(games_won, 0);
        values.put(games_lost, 0);


        db.insert(TABLE_NAME, null, values);


    }
    //recuperation des données
    public Stats getAllData() {
        //recuperation d'une db Readable
        SQLiteDatabase db = this.getReadableDatabase();
        //creation d'un cursor qui permet d'avancer vers les lignes
        Cursor cursor = db.rawQuery(" select * from "+TABLE_NAME,null);
//si il existe une ligne
        if (cursor.moveToFirst()) {


            do {
   //recuperation des données
                int RecentScore =cursor.getInt(cursor.getColumnIndex(score_recent));
                int MinScore=cursor.getInt(cursor.getColumnIndex(score_min));
                int MaxScore=cursor.getInt(cursor.getColumnIndex(score_max));
                int GamesWon=cursor.getInt(cursor.getColumnIndex(games_won));
                int GamesLost=cursor.getInt(cursor.getColumnIndex(games_lost));
                String CurrentDate=cursor.getString(cursor.getColumnIndex(date_last_game));
//creation d'un objet type Stats en lui attribuant les données recupérés ci dessus
                Stats stat = new Stats(RecentScore,MinScore,MaxScore,GamesWon,GamesLost,CurrentDate);
//retourner stat
                return stat;
 //avancer vers la ligne suivante
            } while (cursor.moveToNext());

        }
        //fermeture de la bdd
        db.close();

        return null;  }
        //MAJ d'une ligne du tableau (ici on à qu'une seule ligne )
    public boolean updateData(boolean iswon,Stats stat){

        int score ;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
   //recuperation de la date actuelle
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

        contentValues.put(date_last_game, currentDate);
        //si iswon=true alors +3 sinon -1
        if(iswon){ score= stat.getScore_recent()+3;
            contentValues.put(games_won,stat.getGames_won()+1);
        }

        else{ score= stat.getScore_recent()-1;
            contentValues.put(games_lost,stat.getGames_lost()+1);
        }

        contentValues.put(score_recent,score);
        //MAJ du score min et max , si score<=min alors min =score , si score>= max alors max=score
        if(score<=stat.getScore_min())
            contentValues.put(score_min,score);
        else if (score>=stat.getScore_max())
            contentValues.put(score_max,score);




//MAJ : ici whereclause = null donc la MAJ se fera sur toutes les lignes du tableau (normalement ) , vu qu'on à une seule ligne donc la condition whereclause n'est pas necessaire
        db.update(TABLE_NAME, contentValues,null,null);
        //fermeture de la bdd
       db.close();
        return true;

    }

}
