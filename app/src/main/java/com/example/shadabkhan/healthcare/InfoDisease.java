package com.example.shadabkhan.healthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoDisease extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_disease);
        Info obj =(Info) getIntent().getSerializableExtra("object");
        setTitle(obj.name);
        TextView t1=(TextView) findViewById(R.id.textView3);
        TextView t2=(TextView) findViewById(R.id.textView5);

        t1.setText(obj.sym);
        t2.setText(obj.pre);
    }
}
