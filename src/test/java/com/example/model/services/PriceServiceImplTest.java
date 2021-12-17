package com.example.model.services;

import com.example.model.dao.IPriceDao;
import com.example.model.entities.GenericResponse;
import com.example.model.entities.Price;
import com.example.model.entities.PriceDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
class PriceServiceImplTest {

    @Mock
    IPriceDao priceDao;

    @InjectMocks
    PriceServiceImpl priceService;

    Price price;
    List<Price> prices = new ArrayList<>();
    GenericResponse genericResponse;

    @BeforeEach
    void setUp() {
        price = new Price();
        price.setId(1);
        price.setBrandId(1);
        price.setStartDate(LocalDateTime.of(2020, Month.JUNE, 14, 00, 00, 00));
        price.setEndDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59));
        price.setPrice(35.50);
        price.setPriority(0);
        price.setPriceList(1);
        price.setProductId(35455);
        price.setCurr("EUR");
        prices.add(price);

        Price price = Collections.max(prices, Comparator.comparing(p -> p.getPriority()));

        PriceDetail mockPriceDetail = new PriceDetail(price.getProductId(), price.getBrandId(), price.getPriceList(),
                price.getStartDate(), price.getEndDate(), price.getPrice());

        genericResponse = new GenericResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), mockPriceDetail);

    }

    @Test
    void it_should_return_generic_response() {
        when(priceDao.findPrices(LocalDateTime.of(2020, Month.JUNE, 14, 10, 00, 00),
                35455, 1)).thenReturn(prices);

        GenericResponse response = priceService.getPrices(LocalDateTime.of(2020, Month.JUNE, 14, 10, 00, 00),
                35455, 1);
        assertThat(response).isEqualTo(genericResponse);


    }
}