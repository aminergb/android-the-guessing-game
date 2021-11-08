package com.examen.ledevin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//activite FinPartie  : lance un workthread (un thread en arriere plan ) qui permet de mettre à jour la bdd
// permet de recuperer le nombre selectionne ainsi que le nombre à deviner pour savoir si le joueur a gagné la partie ou non if(nbselectionné==nbdevin)
public class FinPartie extends AppCompatActivity {
   private Button btnrejouer, btnmenu;
   private Intent ToActMenu , ToActJeu;
   private TextView guesstxt , selecttxt;
   private ImageView imgconsequence ;
   private int numberclick , numberguess;
   private BdOperations dbdevin;
   private boolean iswon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creation des intents
        ToActMenu = new Intent(this,EcranTitre.class);
        ToActJeu = new Intent(this,EcranJeu.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_partie);
        //creation de la bdd
        dbdevin = new BdOperations(this);
        //recuperation des bundles envoyés depuis l'activie EcranJeu
        //recuperation du nombre selectioné
        numberguess= getIntent().getIntExtra("guess",0);
        //recuperation du nombre à deviner
        numberclick=getIntent().getIntExtra("click",0);
        //set clicklisteners
        btnrejouer = findViewById(R.id.btnRejouer);
        btnmenu = findViewById(R.id.btnmenu);
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                startActivity(ToActMenu);
            }
        });
        btnrejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                startActivity(ToActJeu);
            }
        });
        imgconsequence =(ImageView) findViewById(R.id.imgconsequence);
        guesstxt =(TextView) findViewById(R.id.tvdeviner);
        selecttxt = (TextView) findViewById(R.id.tvselectioné);

        String guess= guesstxt.getText().toString();
        String select= selecttxt.getText().toString();
        guesstxt.setText(guess+" : "+numberguess);
        selecttxt.setText(select+" : "+numberclick);
//attribuer iswon=true  si le joueur à deviné le numero , sinon iswon=false
        //si iswon=true alors l'image "gagne sera attribué"
        if(numberguess==numberclick){
            iswon=true;

            imgconsequence.setImageResource(R.drawable.gagne);
        //si iswon=false alors l'image "perdu" sera attribué
        }else {
            iswon=false;
            imgconsequence.setImageResource(R.drawable.perdu);
        }
 //creation du workthread : permet de MAJ la bdd en lui passant comme argument un object stat de type Stat et un boolean (gagné ou perdu)
       Thread get_update = new Thread(new Runnable() {
            @Override
            public void run() {
                Stats stat ;
             stat = dbdevin.getAllData();
             dbdevin.updateData(iswon,stat);

            }
        });
        //lancement du thread en arriere plan
        get_update.start();




    }


}
