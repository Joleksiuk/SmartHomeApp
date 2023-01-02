package pl.smarthome.Controllers.shelly;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.*;
import org.apache.commons.collections.MapUtils;
import pl.smarthome.Controllers.tuya.TuyaFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ShellyService {

    private static final int readTimeout = 30;
    private static final int writeTimeout = 30;
    private static final int connTimeout = 30;
    private static final int maxRetry = 3;

    private static final MediaType CONTENT_TYPE = MediaType.parse("application/json");

    static class Constant {

        public static final Map<String, String> CONTAINER = new ConcurrentHashMap<String, String>();

        public static final String AUTH_KEY = "authKey";
        public static final String SERVER = "server";
    }

    private static String authKey = "ZWQxZTN1aWQ8C2F82EE9C857CE6FCAE87A3D4103699E42021BC811BA77FDC325A1F52AA8E4EA828527A61F8E75E";
    private static String server = "https://shelly-38-eu.shelly.cloud";


    static {
        ShellyService.Constant.CONTAINER.put(ShellyService.Constant.AUTH_KEY, authKey);
        ShellyService.Constant.CONTAINER.put(ShellyService.Constant.SERVER, server);
    }


    private static OkHttpClient getHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(connTimeout, TimeUnit.SECONDS);
        client.setReadTimeout(readTimeout, TimeUnit.SECONDS);
        client.setWriteTimeout(writeTimeout, TimeUnit.SECONDS);

        return client;
    }

    public static Response doRequest(Request request) {
        Response response;
        try {
            response = getHttpClient().newCall(request).execute();
        } catch (IOException e) {
            throw new ShellyCloudSDKException(e.getMessage());
        }
        return response;
    }
    public static Request.Builder getRequest(String url) {
        Request.Builder request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .get();
        } catch (IllegalArgumentException e) {
            throw new ShellyCloudSDKException(e.getMessage());
        }
        return request;
    }

    public static Request.Builder postRequest(String url, String body) {
        Request.Builder request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(CONTENT_TYPE, body));
        } catch (IllegalArgumentException e) {
            throw new ShellyCloudSDKException(e.getMessage());
        }

        return request;
    }

    public static Request.Builder putRequest(String url, String body) {
        Request.Builder request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .put(RequestBody.create(CONTENT_TYPE, body));
        } catch (IllegalArgumentException e) {
            throw new ShellyCloudSDKException(e.getMessage());
        }
        return request;
    }

    public static Request.Builder deleteRequest(String url, String body) {
        Request.Builder request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .delete(RequestBody.create(CONTENT_TYPE, body));
        } catch (IllegalArgumentException e) {
            throw new ShellyCloudSDKException(e.getMessage());
        }
        return request;
    }
    public static JsonObject primaryBody(){
        JsonObject json = new JsonObject();
        json.addProperty("auth_key", authKey);
        return json;
    }

    public static Object execute(String path, String method, String body, Map<String, String> customHeaders) {
        try {

            if (MapUtils.isEmpty(ShellyService.Constant.CONTAINER)) {
                throw new ShellyCloudSDKException("Developer information is not initialized!");
            }

            String url = Constant.SERVER + path;

            Request.Builder request;
            if ("GET".equals(method)) {
                request = getRequest(url);
            } else if ("POST".equals(method)) {
                request = postRequest(url, body);
            } else if ("PUT".equals(method)) {
                request = putRequest(url, body);
            } else if ("DELETE".equals(method)) {
                request = deleteRequest(url, body);
            } else {
                throw new ShellyCloudSDKException("Method only support GET, POST, PUT, DELETE");
            }
            if (customHeaders.isEmpty()) {
                customHeaders = new HashMap<>();
            }
            request.url(Constant.SERVER);
            Response response = doRequest(request.build());
            Gson gson=new Gson();
            return gson.fromJson(response.body().string(), Object.class);
        } catch (Exception e) {
            throw new ShellyCloudSDKException(e.getMessage());
        }
    }

    static class ShellyCloudSDKException extends RuntimeException {

        private Integer code;

        public ShellyCloudSDKException(String message) {
            super(message);
        }

        public ShellyCloudSDKException(Integer code, String message) {
            super(message);
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        @Override
        public String toString() {
            if (code != null) {
                return "ShellyCloudSDKException: " +
                        "[" + code + "] " + getMessage();
            }

            return "ShellyCloudSDKException: " + getMessage();
        }
    }
}
