package com.github.alecmus.spring6webclient.client;

import reactor.core.publisher.Flux;

import java.util.Map;

public interface BeerClient {

    Flux<String> listBeer();

    Flux<Map> listBeerMap();
}
