package com.example.kchaou_mohamed_tp4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class Welcome extends AppCompatActivity {
    TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_welcome);
        textWelcome = findViewById(R.id.textWelcome);
        String nom = getIntent().getStringExtra("name");
        String prenom = getIntent().getStringExtra("lastname");
        String mail = getIntent().getStringExtra("mymail");
        String message = "Bienvenue " + nom + " " + prenom + " " + "\n" + "c'est la deuxieme activité.\n" + "vous serez bientôt contacté sur : "+ mail + " ";
        textWelcome.setText(message);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}