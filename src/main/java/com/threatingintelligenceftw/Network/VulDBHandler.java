package com.threatingintelligenceftw.Network;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VulDBHandler {

    private static final String REQUEST_URL = "https://vuldb.com/?api";
    private String response;

    public VulDBHandler(String key, String cve) {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(REQUEST_URL);
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("apikey", key));
        parameters.add(new BasicNameValuePair("search", cve));
        parameters.add(new BasicNameValuePair("details", "1"));
        response = getResponse(client, post, parameters);
    }

    private String getResponse(HttpClient client, HttpPost post, List<NameValuePair> parameters) {
        try {
            post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream in = entity.getContent();
                Scanner s = new Scanner(in).useDelimiter("\\A");
                return s.hasNext() ? s.next() : "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getResponse() {
        return response;
    }
}
