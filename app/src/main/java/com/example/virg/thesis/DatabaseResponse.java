package com.example.virg.thesis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class DatabaseResponse extends AsyncTask <String, Integer, String> {
    private String action;

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... strings) {
        action = (String) strings[0];
        switch (action) {
            case "select":
                try {
                    String id = (String) strings[1];
                    String link = "http://ab04e781.ngrok.io/selectOneAndroid.php?id=" + id;
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI(link));
                    HttpResponse response = client.execute(request);
                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(response.getEntity().getContent()));
                    StringBuilder sb = new StringBuilder("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                } catch (Exception e) {
                    return "Exception: " + e.getMessage();
                }
            case "insert":
                try {
                    String value = (String) strings[1];
                    String link = "http://ab04e781.ngrok.io/insertAndroid.php?value=" + value;
                    String data = URLEncoder.encode("value", "UTF-8") + "=" +
                            URLEncoder.encode(value, "UTF-8");
                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return "Exception: " + e.getMessage();
                }
            case "update":
                try {
                    String id = (String) strings[1];
                    String value = (String) strings[2];
                    String link = "http://ab04e781.ngrok.io/updateAndroid.php?id=" + id + "&value=" + value;
                    String data = URLEncoder.encode("id", "UTF-8") + "=" +
                            URLEncoder.encode(id, "UTF-8");
                    data += "&" + URLEncoder.encode("value", "UTF-8") + "=" +
                            URLEncoder.encode(value, "UTF-8");
                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return "Exception: " + e.getMessage();
                }
        }
        return null;
    }

    protected void onPostExecute(String result){

    }
}
