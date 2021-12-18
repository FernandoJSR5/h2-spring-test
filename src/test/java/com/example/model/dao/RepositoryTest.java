package com.example.model.dao;

import com.example.model.entities.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@DataJpaTest
class RepositoryTest {

    @Autowired
    private IPriceDao priceDao;

    Price price;

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
    }

    @Test
    @DisplayName("It should return a specific price")
    void repositoryTest() {
       List<Price> prices = priceDao.findPrices(LocalDateTime.of(2020, Month.JUNE, 14, 10, 00, 00),
                35455, 1);
        assertThat(prices.get(0)).isEqualTo(price);
    }
}