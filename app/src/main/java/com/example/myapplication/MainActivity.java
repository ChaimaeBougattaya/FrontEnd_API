package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Student> Students ;
    TextView txt;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        JSONTask json = new JSONTask();
        String link = "http://192.168.10.1:81/controller";
        json.execute(link);
    }
    public class JSONTask extends AsyncTask<String, Void, ArrayList<Student>> {
        URL url;
        @Override
        protected ArrayList<Student> doInBackground(String... strings) {
            Students = new ArrayList<>();
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuffer buffer;
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }
                JSONArray arr =new JSONArray(buffer.toString());

                for (int i = 0; i < arr.length(); i++) {

                    int id = Integer.parseInt(arr.getJSONObject(i).getString("id"));
                    String nom = arr.getJSONObject(i).getString("nom");
                    String prenom = arr.getJSONObject(i).getString("prenom");
                    String username = arr.getJSONObject(i).getString("username");
                    Students.add(new Student(id, nom, prenom, username));
                }
            } catch (MalformedURLException e) { e.printStackTrace();}
            catch (IOException e) { e.printStackTrace();
            } catch (JSONException e) { e.printStackTrace(); }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) { reader.close(); }
                } catch (IOException e) { e.printStackTrace(); }
            }
            return Students;
        }

        @Override
        protected void onPostExecute(ArrayList<Student> unused) {
            super.onPostExecute(unused);

            CustomListAdapter listAdapter = new CustomListAdapter(getApplicationContext(),R.layout.custom_list_layout,Students);
            list.setAdapter(listAdapter);
        }
    }



}