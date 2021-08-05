package ru.itlab.others;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;

public class Vk_auth {
    private static int APP_ID = 7919535;
    private static String CLIENT_SECRET = "2b44e9212b44e9212b44e921972b3c3e8e22b442b44e9214a4f47912b25a3cd5203b29e";
    private static String REDIRECT_URI = "https://brain-miners.herokuapp.com/";
    private static String code;
    public Vk_auth(String code) throws ClientException, ApiException {
        this.code = code;
    }


    public String getToken() throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserAuthResponse authResponse = vk.oAuth()
                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                .execute();

        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        return actor.getAccessToken();
    }

}
