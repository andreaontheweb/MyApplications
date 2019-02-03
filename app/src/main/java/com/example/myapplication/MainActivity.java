package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, loc, desig;
    Button saveBtn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Get the Intent that started this activity and extract the string
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Intent intent2 = getIntent();
        String messagename = intent2.getStringExtra(DetailsActivity.EXTRA_NAME);
        String messagelocation = intent2.getStringExtra(DetailsActivity.EXTRA_LOCATION);
        String messagedesignation = intent2.getStringExtra(DetailsActivity.EXTRA_DESIGNATION);
        Toast.makeText(getApplicationContext(), messagename,Toast.LENGTH_SHORT).show();

        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.txtName);
        name.setText(messagename);
        loc = (EditText)findViewById(R.id.txtLocation);
        loc.setText(messagelocation);
        desig = (EditText)findViewById(R.id.txtDesignation);
        desig.setText(messagedesignation);
        saveBtn = (Button)findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strname =name.getText().toString();
                if (TextUtils.isEmpty(strname)){
                    name.setError("non puo essere vuoto");
                    return;
                }
                String username = name.getText().toString()+"\n";
                String location = loc.getText().toString();
                String designation = desig.getText().toString();
                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(username,location,designation);
                intent = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}