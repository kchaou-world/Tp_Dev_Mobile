package com.example.kchaou_mohamed_tp5; // gardez VOTRE package

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etPrenom, etPoids, etTaille;
    private RadioButton rbHomme, rbFemme;
    private Button btnCalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }

    private void init() {
        etPrenom = findViewById(R.id.etPrenom);
        etPoids = findViewById(R.id.etPoids);
        etTaille = findViewById(R.id.etTaille);
        rbHomme = findViewById(R.id.rbHomme);
        rbFemme = findViewById(R.id.rbFemme);
        btnCalculer = findViewById(R.id.btnCalculer);

        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = etPrenom.getText().toString().trim();
                String sPoids = etPoids.getText().toString().trim().replace(',', '.');
                String sTaille = etTaille.getText().toString().trim().replace(',', '.');

                if (prenom.isEmpty() || sPoids.isEmpty() || sTaille.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                double poids, taille;
                try {
                    poids = Double.parseDouble(sPoids);
                    taille = Double.parseDouble(sTaille);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Valeurs invalides",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (poids <= 0 || taille <= 0) {
                    Toast.makeText(MainActivity.this, "Poids et taille doivent être positifs",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                double imc = poids / (taille * taille);
                String civilite = rbFemme.isChecked() ? "Madame" : "Monsieur";

                // Intent explicite vers la 2e activité
                Intent myintent = new Intent(MainActivity.this, ResultatActivity.class);
                myintent.putExtra("prenom", prenom);
                myintent.putExtra("civilite", civilite);
                myintent.putExtra("imc", imc);
                startActivity(myintent);
            }
        });
    }
}