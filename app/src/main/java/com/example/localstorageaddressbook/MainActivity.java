package com.example.localstorageaddressbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycleView;
    FloatingActionButton add_button;
    ArrayList<String> id,name,address, email,contact;
    CustomArrayAdapter customArrayAdapter;
    Context context;
    FileHelper myFile;
    TextView defaultText;
    SwipeRefreshLayout swipe_refresh;
    public static final int OPEN_NEW_ACTIVITY = 12345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        recycleView = findViewById(R.id.recycle_view);
        add_button = findViewById(R.id.add_button);
        defaultText = findViewById(R.id.default_text);
        swipe_refresh = findViewById(R.id.swipe_refresh);
        try {
            myFile = new FileHelper(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        id = new ArrayList<>();
        name = new ArrayList<>();
        email = new ArrayList<>();
        address = new ArrayList<>();
        contact = new ArrayList<>();
        try {
            addDataToAdapter();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        customArrayAdapter = new CustomArrayAdapter(MainActivity.this, id, name,address,email,contact);
        recycleView.setAdapter(customArrayAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, AddAddressActivity.class);
                startActivityForResult(intent,OPEN_NEW_ACTIVITY);
            }
        });
        swipe_refresh.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                swipe_refresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_NEW_ACTIVITY) {
            // Execute your code on back here
            // ....
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    public  void addDataToAdapter() throws IOException, JSONException {
        String data = myFile.readAll();
        if(!(data.length() < 5)){
            defaultText.setVisibility(View.GONE);
            JSONArray jArray = new JSONArray(data);
            for (int i=0 ; i<jArray.length();i++){
                JSONObject add = jArray.getJSONObject(i);
                id.add(add.getString("id"));
                name.add(add.getString("name"));
                address.add(add.getString("address"));
                email.add(add.getString("contact"));
                contact.add(add.getString("email"));
            }
        }

    }
}