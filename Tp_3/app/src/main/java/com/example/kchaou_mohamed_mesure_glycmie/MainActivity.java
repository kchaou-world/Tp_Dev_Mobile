package com.example.kchaou_mohamed_mesure_glycmie;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etValeur;
    private SeekBar sbAge;
    private TextView tvAge;
    private TextView tvResultat;
    private RadioButton rbtOui;
    private RadioButton rbtNon;
    private Button btnConsulter;

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
        etValeur = findViewById(R.id.etValeur);
        sbAge = findViewById(R.id.sbAge);
        tvAge = findViewById(R.id.tvAge);
        tvResultat = findViewById(R.id.tvResultat);
        rbtOui = findViewById(R.id.rbtOui);
        rbtNon = findViewById(R.id.rbtNon);
        btnConsulter = findViewById(R.id.btnConsulter);

        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress);
                tvAge.setText("Votre âge : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer(v);
            }
        });
    }

    public void calculer(View view) {
        String saisie = etValeur.getText().toString().trim();
        if (saisie.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir la valeur mesurée", Toast.LENGTH_SHORT).show();
            return;
        }

        double valeur;
        try {
            valeur = Double.parseDouble(saisie);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valeur invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = sbAge.getProgress();
        boolean jeune = rbtOui.isChecked();
        String message;

        if (jeune) {
            double min, max;
            if (age >= 13) {
                min = 5.0;
                max = 7.2;
            } else if (age >= 6) {
                min = 5.0;
                max = 10.0;
            } else {
                min = 5.5;
                max = 10.0;
            }

            if (valeur < min) {
                message = "Niveau de glycémie est trop bas";
            } else if (valeur > max) {
                message = "Niveau de glycémie est trop élevée";
            } else {
                message = "Niveau de glycémie est normale";
            }
        } else {
            if (valeur < 10.5) {
                message = "Niveau de glycémie est normale";
            } else {
                message = "Niveau de glycémie est trop élevée";
            }
        }

        tvResultat.setText(message);
    }
}