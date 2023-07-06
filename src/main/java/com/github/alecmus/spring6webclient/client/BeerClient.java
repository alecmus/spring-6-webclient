package com.github.alecmus.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.alecmus.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.Flow;

public interface BeerClient {

    Flux<String> listBeer();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDTO> listBeerDtos();
}
