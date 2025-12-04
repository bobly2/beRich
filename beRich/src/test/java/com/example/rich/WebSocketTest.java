package com.example.rich;


import com.binance.connector.client.common.configuration.ClientConfiguration;
import com.binance.connector.client.common.configuration.SignatureConfiguration;
import com.binance.connector.client.common.websocket.configuration.WebSocketClientConfiguration;
import com.binance.connector.client.spot.rest.SpotRestApiUtil;

import com.binance.connector.client.spot.rest.api.SpotRestApi;
import com.binance.connector.client.spot.websocket.api.SpotWebSocketApiUtil;
import com.binance.connector.client.spot.websocket.api.api.SpotWebSocketApi;
import com.binance.connector.client.spot.websocket.api.model.Interval;
import com.binance.connector.client.spot.websocket.api.model.KlinesRequest;
import com.binance.connector.client.spot.websocket.api.model.KlinesResponse;
import com.binance.connector.client.spot.websocket.stream.SpotWebSocketStreamsUtil;
import com.binance.connector.client.spot.websocket.stream.api.SpotWebSocketStreams;


import okhttp3.*;
import org.eclipse.jetty.client.Authentication;

import org.eclipse.jetty.client.BasicAuthentication;
import org.eclipse.jetty.client.HttpProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Proxy;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class WebSocketTest {


    @Test
    public void webSocketApi() throws ParseException {



    }



    private SpotWebSocketApi api;
    public SpotWebSocketApi getApi() {
        if (api == null) {
            WebSocketClientConfiguration clientConfiguration =
                    SpotWebSocketApiUtil.getClientConfiguration();
            // if you want the connection to be auto logged on:
            // https://developers.binance.com/docs/binance-spot-api-docs/websocket-api/authentication-requests
            clientConfiguration.setAutoLogon(true);
            SignatureConfiguration signatureConfiguration = new SignatureConfiguration();
            signatureConfiguration.setApiKey("apiKey");
            signatureConfiguration.setPrivateKey("/path/to/private.key");
            clientConfiguration.setSignatureConfiguration(signatureConfiguration);
            api = new SpotWebSocketApi(clientConfiguration);
        }
        return api;
    }
    public void websocketAPI1() throws ParseException, InterruptedException {

        KlinesRequest klinesRequest = new KlinesRequest();
        klinesRequest.symbol("BNBUSDT");
        klinesRequest.interval(Interval.INTERVAL_1s);
        CompletableFuture<KlinesResponse> future = getApi().klines(klinesRequest);
        future.handle(
                (response, error) -> {
                    if (error != null) {
                        System.err.println(error);
                    }
                    System.out.println(response);
                    return response;
                });
    }
    public void restAPI() throws ParseException, InterruptedException {

    }


}
