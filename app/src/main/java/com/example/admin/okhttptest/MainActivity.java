package com.example.admin.okhttptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnsend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnsend = findViewById(R.id.btn_send);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOkHttpRequest_GET();
                sendOkHttpRequest_POST();
            }
        });
    }

    private void sendOkHttpRequest_POST() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestbody = new FormBody.Builder()
                .add("username","admin")
                .add("password","123456")
                .build();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(requestbody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responsedata = response.body().string();
            Gson gson = new Gson();
//            App app = gson.fromJson(responsedata,App.class);
            List<App> apps = gson.fromJson(responsedata,new TypeToken<List<App>>(){}.getType());
            for (App app : apps){
                Log.e("MainActivity",app.getId() );
                Log.e("MainActivity", app.getName() );
                Log.e("MainActivity", app.getVersion() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendOkHttpRequest_GET() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responsedata = response .body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
