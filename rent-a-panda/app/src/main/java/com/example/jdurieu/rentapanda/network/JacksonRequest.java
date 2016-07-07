package com.example.jdurieu.rentapanda.network;

/**
 * Created by JonathanDurieu on 10/06/16.
 */

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;



/**
 * Volley adapter for JSON requests that will be parsed into Java objects by Jackson.
 * You can set a cache expiration policy when creating request. If not specified a softTTL of 3 minutes and a TTL of 5 minutes is used.
 */
public class JacksonRequest<T> extends Request<T> {

    private static final String DEVICE_HEADER = "deviceId";
    private static final String TOKEN_HEADER = "token";
    private static final String ACCEPTED_LANGUAGE_HEADER = "Accept-Language";

    private final ObjectMapper mapper = new ObjectMapper();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Listener<T> listener;
    private final int softTTL;
    private final int ttl;
    private final String mRequestBody;

    /**
     * Charset for request.
     */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);


    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }


    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     * @param listener the listener object that will be called back after request successful completion
     * @param errorListener the listener object that will be called back on request error
     * @param softTTL the soft time to Live in minutes, cache will be hit, but also refreshed on background
     * @param ttl the time to live in minutes, this cache entry expires completely
     */
    public JacksonRequest(String url, Class<T> clazz, Map<String, String> headers,
                          Listener<T> listener, ErrorListener errorListener, int softTTL, int ttl) {
        this(Method.GET, url, null, clazz, headers, listener, errorListener, softTTL, ttl);
    }

    /**
     * Makes a GET request. SoftTTl defaults to 1 mn and ttl defaults to 5 mn.
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     * @param listener the listener object that will be called back after request successful completion
     * @param errorListener the listener object that will be called back on request error
     */
    public JacksonRequest(String url, Class<T> clazz, Map<String, String> headers,
                          Listener<T> listener, ErrorListener errorListener) {
        this(url, clazz, headers, listener, errorListener, 1, 5);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    /**
     * Send a request with the method as passed in parameters.
     *
     * @param method the HTTP method to issue
     * @param requestBody the HTTP body of the request. If null, no body is sent.
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     * @param listener the listener object that will be called back after request successful completion
     * @param errorListener the listener object that will be called back on request error
     * @param softTTL the soft time to Live in minutes, cache will be hit, but also refreshed on background
     * @param ttl the time to live in minutes, this cache entry expires completely
     */
    public JacksonRequest(int method, String url, Object requestBody, Class<T> clazz, Map<String, String> headers,
                          Listener<T> listener, ErrorListener errorListener, int softTTL, int ttl) {


        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.softTTL = softTTL;
        this.ttl = ttl;
        final DateFormat df = new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss.SSS'Z'");
        mapper.setDateFormat(df);

        mapper.getDeserializationConfig().with(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String b = "";
        if (requestBody != null){
            try {
                b = mapper.writeValueAsString(requestBody);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        mRequestBody = b;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {

            //HttpHeaderParser.parseCharset(response.headers)); returns ISO-8859-1
            String json = new String(
                    response.data,"utf-8"); //HttpHeaderParser.parseCharset(response.headers));
            T parsed = mapper.readValue(json, clazz);

            return Response.success(
                    parsed, CachePolicy.parseIgnoreCacheHeaders(response, softTTL, ttl));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonMappingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonParseException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {

            try {
                String jsonTest  = new String(response.data,"utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            return Response.error(new ParseError(e));
        }
    }
}
