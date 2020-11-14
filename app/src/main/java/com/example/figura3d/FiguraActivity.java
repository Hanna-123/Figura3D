package com.example.figura3d;

import android.app.Activity;
import android.os.Bundle;
public class FiguraActivity extends Activity {
    Figura f;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        Figura f;
        Bundle bn = this.getIntent().getExtras();
        f = new Figura(this, bn.getString("FIGURA"), bn.getInt("LADOS"));
        setContentView(f);
    }
}
