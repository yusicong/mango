package com.ysc.device.service.utils;

import com.ysc.device.service.domain.dto.RegisterDTO;
import com.ysc.device.service.domain.request.SmsCodeValidateRequest;
import com.ysc.device.service.domain.response.SMSResponse;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class SmsUtils {

    public static void main(String[] args) throws Exception {

        SMSResponse result = requestData("appkey=298d3a24b1fbd&amp;phone=15150650175&amp;zone=86&amp;&amp;code=1111");
        System.out.println(result);
    }

    private static final String address = "https://webapi.sms.mob.com/sms/verify";
    private static final String appkey = "298d3a24b1fbd";
    /**
     * 发起https 请求
     * @param request
     * @param
     * @return
     */
    public  static SMSResponse smsCodeValidated(SmsCodeValidateRequest request){
        String params = "appkey="+appkey+"&amp;phone="+ request.getMobile()+"&amp;zone="+ request.getZone()+"&amp;&amp;code="+ request.getCode();
        return requestData(params);
    }
    /**
     * 发起https 请求
     * @param
     * @param
     * @return
     */
    public static SMSResponse requestData(String params){

        HttpURLConnection conn = null;
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
                public X509Certificate[] getAcceptedIssuers(){return null;}
                public void checkClientTrusted(X509Certificate[] certs, String authType){}
                public void checkServerTrusted(X509Certificate[] certs, String authType){}
            }};

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());

            //ip host verify
            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String urlHostName, SSLSession session) {
                    return urlHostName.equals(session.getPeerHost());
                }
            };

            //set ip host verify
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");// POST
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            // set params ;post params
            if (params!=null) {
                conn.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.write(params.getBytes(Charset.forName("UTF-8")));
                out.flush();
                out.close();
            }
            conn.connect();
            //get result
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String result = parsRtn(conn.getInputStream());
                SMSResponse smsResponse = JsonUtils.toObject(result, SMSResponse.class);
                return smsResponse;
            } else {
                System.out.println(111);
                System.out.println(conn.getResponseCode() + " "+ conn.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return null;
    }

    /**
     * 获取返回数据
     * @param is
     * @return
     * @throws IOException
     */
    private  static String parsRtn(InputStream is) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = null;
        boolean first = true;
        while ((line = reader.readLine()) != null) {
            if(first){
                first = false;
            }else{
                buffer.append("\n");
            }
            buffer.append(line);
        }
        return buffer.toString();
    }
}
