package ru.itlab.others;

import com.github.scribejava.apis.HHApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.stereotype.Component;
import ru.itlab.models.User;
import ru.itlab.models.forms.HhForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@Component
public class Hh {
    private static final String NETWORK_NAME = "hh.ru";
    private static final String PROTECTED_RESOURCE_URL = "https://api.hh.ru/me";
    private static final String myPage = "http://localhost:8080/oauth";
    public static Hh hh;

    static {
        try {
            hh = new Hh();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
    }

    final String clientId = "UNK1I7U5K9O3BNEHP58AC3BLRHKASFH9I7PANMBVV00A74CUPSN4GMSA81VQ5VKC";
    final String clientSecret = "K76SPC90LD0U1F0M5O2O62GOVP3MLVNCA7U3BBV5C5435OFAEA4F0P9TIBMUF07B";
    final OAuth20Service service;


    private Hh() throws OAuthSystemException {
        service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .callback("http://localhost:8080/oauth")
                .build(HHApi.instance());
    }


    public HhForm getOauthUser(String code) throws IOException, ExecutionException, InterruptedException, OAuthSystemException {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        StringBuilder url = new StringBuilder();
        url.append("https://hh.ru/oauth/token?grant_type=authorization_code&client_id=").append(clientId).append("&client_secret=").append(clientSecret).append("&redirect_uri=").append(myPage).append("&code=").append(code);
                String accessTokenJson = getData(url.toString());
        AccessToken accessToken = gson.fromJson(accessTokenJson, AccessToken.class);
        //System.out.println("access_token : " + accessToken.access_token);

        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken.access_token, request);

        String JSON = service.execute(request).getBody();
       // System.out.println("JSON : " + JSON);
        HhForm user = gson.fromJson(JSON, HhForm.class);
        return user;

    }

    private String getData(String url) throws IOException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        InputStreamReader inReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader bf = new BufferedReader(inReader);

        StringBuilder sb = new StringBuilder();
        int charByte;
        while ((charByte = bf.read()) != -1) {
            sb.append((char) charByte);
        }

        return sb.toString();


    }


}
