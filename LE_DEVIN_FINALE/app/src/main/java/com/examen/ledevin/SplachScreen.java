package com.examen.ledevin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Locale;
//activite qui servira de launcher pour l'appli
//animation qui durera 2 sec , quand elle fini , on passe vers l'activité EcranTitre (le menu )
public class SplachScreen extends AppCompatActivity {
   private ImageView logo;
   private Intent ToActTitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
//recuperation du logo
        logo=(ImageView) findViewById(R.id.imgviewlogo);
        //creation d'un intent qui permet de lancer l'activite EcranTitre
       ToActTitre=  new Intent(this, EcranTitre.class);
       //gestion de l'animation (transition )
        Animation logotransit = AnimationUtils.loadAnimation(this,R.anim.logotransit);
        logotransit.setAnimationListener( new Animation.AnimationListener() {
            public void onAnimationStart(Animation anim)
            {
            }
            public void onAnimationRepeat(Animation anim)
            {
            }
            //quand l'animation se termine (apres 2 secondes)
            public void onAnimationEnd(Animation anim)
            {

                 //lancer l'activite EcranTitre via l'intent ToActTitre
                startActivity(ToActTitre);
                finish();
            }
        });

        //demarrer l'animation
        logo.startAnimation(logotransit);




    }
}
