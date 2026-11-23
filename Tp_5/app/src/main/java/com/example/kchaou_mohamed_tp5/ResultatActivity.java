package com.example.kchaou_mohamed_tp5; // gardez VOTRE package

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class ResultatActivity extends AppCompatActivity {

    private TextView textIMC, textETAT;
    private Button btnBack, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textIMC = findViewById(R.id.textIMC);
        textETAT = findViewById(R.id.textETAT);
        btnBack = findViewById(R.id.btnBack);
        btnCall = findViewById(R.id.btnCall);

        // Récupération des extras
        String prenom = getIntent().getExtras().getString("prenom");
        String civilite = getIntent().getExtras().getString("civilite");
        double imc = getIntent().getExtras().getDouble("imc");

        textIMC.setText(String.format(Locale.US, "%s %s votre IMC est : %.2f", civilite, prenom, imc));

        // Interprétation selon l'OMS
        String etat;
        int couleur;
        if (imc < 18.5) {
            etat = "maigreur";
            couleur = R.color.colorOrange;
        } else if (imc < 25) {
            etat = "normal";
            couleur = R.color.colorGreen;
        } else if (imc < 30) {
            etat = "surpoids";
            couleur = R.color.colorOrange;
        } else {
            etat = "obésité";
            couleur = R.color.colorRed;
        }
        textETAT.setText(etat);
        textETAT.setTextColor(getResources().getColor(couleur));

        // Bouton RETOUR : ferme l'activité, retour à MainActivity
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Bouton Appeler 123 : intent implicite
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123"));
                startActivity(call);
            }
        });
    }
}