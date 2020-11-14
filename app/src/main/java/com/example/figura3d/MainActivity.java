package com.example.figura3d;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.*;

import android.os.Bundle;

public class MainActivity extends Activity{
    Button jbnCubo, jbnCono, jbnCilindro, jbnEsfera;
    EditText jet;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbnCubo = (Button) findViewById(R.id.xbtCubo);
        jbnCono = (Button) findViewById(R.id.xbtCono);
        jbnCilindro = (Button) findViewById(R.id.xbtCilindro);
        jbnEsfera = (Button) findViewById(R.id.xbtEsfera);
        jet = (EditText) findViewById(R.id.extNLados);
        jbnCubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bu = new Bundle();
                bu.putInt("LADOS", 4);
                bu.putString("FIGURA", "Cubo");
                in.putExtras(bu);
                startActivity(in);
            }
        });
        jbnCono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bu = new Bundle();
                bu.putInt("LADOS", Integer.parseInt(jet.getText().toString()));
                bu.putString("FIGURA", "Cono");
                in.putExtras(bu);
                startActivity(in);
            }
        });
        jbnCilindro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bu = new Bundle();
                bu.putInt("LADOS", Integer.parseInt(jet.getText().toString()));
                bu.putString("FIGURA", "Cilindro");
                in.putExtras(bu);
                startActivity(in);
            }
        });
        jbnEsfera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bu = new Bundle();
                bu.putInt("LADOS", Integer.parseInt(jet.getText().toString()));
                bu.putString("FIGURA", "Esfera");
                in.putExtras(bu);
                startActivity(in);
            }
        });

    }
}