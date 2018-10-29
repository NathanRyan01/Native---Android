package com.example.virg.thesis;

import android.content.Context;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLWrestlerParser {
    private Wrestler[] data = null;
    Context context = null;
    InputStream is;

    public XMLWrestlerParser(Context c, int value) {
        this.context = c;

        // grab the data from xml

        // get the doc parser ready

        if (value == 100){
            is = this.context.getResources().openRawResource(R.raw.test100);
        }
        else if (value == 1000){
            is = this.context.getResources().openRawResource(R.raw.test1000);
        }
        else{
            is = this.context.getResources().openRawResource(R.raw.test10000);
        }

        DocumentBuilder builder = null;
        Document doc = null;

        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(is);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // chop all the nodes
        assert doc != null;
        NodeList nameList = doc.getElementsByTagName("NAME");
        // traverse NodeLists to make the data array
        data = new Wrestler[nameList.getLength()];

        for (int i = 0; i < nameList.getLength(); i++) {
            String name = nameList.item(i).getFirstChild().getNodeValue();
            data[i] = new Wrestler(name);
        }
    }

    public Wrestler[] getData() {
        return data;
    }

    public Wrestler getData(int i) {
        return data[i];
    }

    public String[] getNames() {
        String[] names = new String[data.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = getData(i).getName();
        }
        return names;
    }

}
