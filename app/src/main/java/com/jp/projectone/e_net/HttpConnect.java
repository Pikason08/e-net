package com.jp.projectone.e_net;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by keitaro on 2017/07/02.
 */

public class HttpConnect extends AsyncTask<URL, Void, String> {


    private AsyncTaskCallbacks callback = null;


    public HttpConnect(AsyncTaskCallbacks callback) {
        this.callback = callback;
    }


    @Override
    protected String doInBackground(URL... url) {

        String result = "";
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url[0].openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int resp = conn.getResponseCode();
            Log.d("resp:", String.valueOf(resp));
            result = readIt(conn.getInputStream());
            Log.d("通信のresult",result);

        } catch(IOException e) {
            e.printStackTrace();
            Log.d("エラー","通信エラー");
        } finally {
            if(conn != null) {
                conn.disconnect();
                Log.d("通信","成功");
            }
        }
        return result;
    }

    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        try {
            stream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String response) {
        Log.d("受信メッセージ",response);
        Log.d("responseの型", String.valueOf(response.getClass()));

        callback.postExecute(response);

    }
    public interface AsyncTaskCallbacks {
        void preExecute();
        void postExecute(String result);
        void progressUpdate(int progress);
        void cancel();
    }


}
