package com.github.alecmus.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.alecmus.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BeerClient {

    Flux<String> listBeer();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDTO> listBeerDtos();

    Mono<BeerDTO> getBeerById(String id);

    Flux<BeerDTO> getBeerByBeerStyle(String beerStyle);

    Mono<BeerDTO> createBeer(BeerDTO beerDTO);

    Mono<BeerDTO> updateBeer(BeerDTO beerDTO);
}
