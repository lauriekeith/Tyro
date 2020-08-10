package com.example.projecttyro;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkInfo extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... strings) {

        try {
        URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=SM53AY&destinations=" + strings[0] + "&key=AIzaSyA5tBJ-PDV4dzFOsSvSHaqECs8ehpJeAqA");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);
        Object content = connection.getContent();
        System.out.println(content);
        InputStream in = new BufferedInputStream(connection.getInputStream());
        return getDistance(in);

//          result = new BufferedReader(new InputStreamReader(in))
//                  .lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

    }

    public void setDistance(EditText editText) throws IOException {
        //


    }

    public int getDistance(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readApiResponse(reader);
        } finally{
            reader.close();
        }
    }

    private int readApiResponse(JsonReader reader) throws IOException {
        int distance = 0;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("rows")) {
                distance = readRows(reader);
            } else {
                reader.skipValue();
            }
        }
        return distance;
    }

    private int readRows(JsonReader reader) throws IOException {
        reader.beginArray();
        reader.beginObject();
        reader.nextName();
        reader.beginArray();
        reader.beginObject();
        reader.nextName();
        reader.beginObject();
        reader.nextName();
        reader.skipValue();
        reader.nextName();
        return reader.nextInt();
    }


}
