package com.github.alecmus.spring6webclient.client;

import com.github.alecmus.spring6webclient.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient client;

    @Test
    void testUpdate() {

        final String NAME = "New Name";

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos()
                .next()
                .doOnNext(beerDTO -> beerDTO.setBeerName(NAME))
                .flatMap(dto -> client.updateBeer(dto))
                .subscribe(byIdDto -> {
                    System.out.println(byIdDto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testCreateBeer() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        BeerDTO newDto = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .beerStyle("IPA")
                .quantityOnHand(500)
                .upc("123245")
                .build();

        client.createBeer(newDto)
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByBeerStyle() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.getBeerByBeerStyle("Pale Ale")
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerById() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos()
                .flatMap(dto -> client.getBeerById(dto.getId()))
                .subscribe(byIdDto -> {
                    System.out.println(byIdDto.getBeerName());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerDto() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos().subscribe(dto -> {
            System.out.println(dto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerJson() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeersJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetMap() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeer() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeer().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}