package com.affaince.subscription.subscriptionableitem.registration;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by rbsavaliya on 21-07-2015.
 */

public class CreateItemTest {
    @Test
    public void create_item () {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:8080/subscriptionableitem");
        httpPost.setHeader("Content-type", "application/json");
        JSONObject jsonObject = new JSONObject();
        try {
            HttpEntity httpEntity = new StringEntity(jsonObject.toString());
            httpPost.setEntity(httpEntity);
            httpClient.execute (httpPost);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("content type not supported");
        } catch (ClientProtocolException e) {
            throw new AssertionError("client protocol is not supported");
        } catch (IOException e) {
            throw new AssertionError("content type not supported");
        }
    }
}
