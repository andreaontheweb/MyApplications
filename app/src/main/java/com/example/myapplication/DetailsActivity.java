package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tutlane on 05-01-2018.
 */
/*

 */
public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_LOCATION = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_NAME="com.example.myfirstapp.MESSAGENAME";
    public static final String EXTRA_DESIGNATION="com.example.myfirstapp.MESSAGENAME";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.user_list);
        final ListAdapter adapter = new SimpleAdapter(DetailsActivity.this, userList, R.layout.list_row,new String[]{"name","designation","location"}, new int[]{R.id.name, R.id.designation, R.id.location});
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                intent = new Intent(DetailsActivity.this,MainActivity.class);


                HashMap<String,String> map =(HashMap<String,String>)adapter.getItem(position);
                String name = (String)map.get("name");
                String designation  = (String)map.get("designation");
                String location  = (String)map.get("location");
                intent.putExtra(EXTRA_NAME, name);
                intent.putExtra(EXTRA_LOCATION, location);
                intent.putExtra(EXTRA_DESIGNATION, designation);

                Toast.makeText(DetailsActivity.this, "click " + name,
                        Toast.LENGTH_LONG).show();
                Log.d("List", "Ho cliccato sull'elemento " + position+adapter.getItem(position));

                startActivity(intent);
            }
        });


        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetailsActivity.this,MainActivity.class);


                startActivity(intent);
            }
        });

    }
}