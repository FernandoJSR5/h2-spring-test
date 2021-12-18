package com.example.model.services;

import com.example.model.dao.IPriceDao;
import com.example.model.entities.GenericResponse;
import com.example.model.entities.Price;
import com.example.model.entities.PriceDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
class PriceServiceTest {

    @Mock
    IPriceDao mockPrice;

    @Autowired
    IPriceDao priceDao;

    @InjectMocks
    PriceServiceImpl priceService;

    List<Price> prices = new ArrayList<>();
    GenericResponse genericResponse;

    @BeforeEach
    void setUp() {
        prices = priceDao.findPrices(LocalDateTime.of(2020, Month.JUNE,16,21,00,00),
                35455, 1);

        Price price = Collections.max(prices, Comparator.comparing(p -> p.getPriority()));

        PriceDetail mockPriceDetail = new PriceDetail(price.getProductId(), price.getBrandId(), price.getPriceList(),
                price.getStartDate(), price.getEndDate(), price.getPrice());

        genericResponse = new GenericResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), mockPriceDetail);

    }

    @Test
    @DisplayName("It should be return generic response")
    void serviceTest() {
        when(mockPrice.findPrices(LocalDateTime.of(2020, Month.JUNE,16,21,00,00),
                35455, 1)).thenReturn(prices);

        GenericResponse response = priceService.getPrices(LocalDateTime.of(2020, Month.JUNE,16, 21, 00, 00),
                35455, 1);

        assertThat(response).isEqualTo(genericResponse);

    }
}