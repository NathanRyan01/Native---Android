package com.example.virg.thesis;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class FirstFragment extends Fragment{
    GetWiki wiki = new GetWiki();
    rawWiki raw  = new rawWiki();
    WebView webView;
    Document doc;
    Elements element;
    View myView;
    private static final String TAG = "debugging";
    long startTime1;
    long endTime1;
    long MethodDuration1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);
        webView = myView.findViewById(R.id.WebView1);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.setWebViewClient(new WebViewClient());
        this.raw.execute();
        this.wiki.execute();
        return myView;
    }


    private class rawWiki extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String link = ("https://en.wikipedia.org/w/api.php?format=json&action=parse&page=Dwayne_Johnson");
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                endTime1 = System.currentTimeMillis();
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
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
        }
        protected void onPostExecute(String data) {
            MethodDuration1 = (endTime1 - startTime1);
            String total = String.valueOf(MethodDuration1);
            Toast.makeText(getActivity(),
                    total + " milliseconds to retrieve data", Toast.LENGTH_LONG).show();
        }
    }



    private class GetWiki extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "https://en.wikipedia.org/wiki/Dwayne_Johnson";
            try {
                // Get the webpage
                doc = Jsoup.connect(url).get();
            } catch (IOException element) {
                element.printStackTrace();
            }
            return url;
        }

        protected void onPostExecute(String name) {
            if (doc != null) {
                element = doc.select("body");
                String html = element.toString();
                String mime = "text/html";
                String enc = "utf-8";
                webView.loadDataWithBaseURL(name, html, mime, enc, null);
            }
        }
    }

}
