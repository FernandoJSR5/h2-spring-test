package com.example.rest;

import com.example.model.common.GenericResponse;
import com.example.model.common.PriceDetail;
import com.example.model.dao.IPriceDao;
import com.example.model.entities.Price;
import com.example.model.services.IPriceService;
import com.example.model.services.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    IPriceService priceServiceMock = Mockito.mock(IPriceService.class);

    @Autowired
    IPriceDao priceDaoMock = Mockito.mock(IPriceDao.class);

    @Autowired
    PriceController priceControllerMock = Mockito.mock(PriceController.class);

    @Test
    public void shouldReturnGenericResponse() throws Exception {
        GenericResponse genericResponse = new GenericResponse();
        Price mockPrice = new Price();
        List<Price> prices = new ArrayList<>();
        mockPrice.setId(1);
        mockPrice.setProductId(35455);
        mockPrice.setPriceList(1);
        mockPrice.setStartDate(LocalDateTime.of(22020, Month.JUNE, 14, 00, 00, 00));
        mockPrice.setEndDate(LocalDateTime.of(22020, Month.DECEMBER, 31, 23, 59, 59));
        mockPrice.setPrice(36);
        mockPrice.setBrandId(1);
        mockPrice.setPriority(0);
        mockPrice.setCurr("EUR");

        prices.add(mockPrice);

        when(priceDaoMock.findPrices(LocalDateTime.of(22020, Month.JUNE, 14, 10, 00, 00),
                35455, 1)).thenReturn(prices);

        PriceDetail mockPriceDetail = new PriceDetail(35455, 1, 1, LocalDateTime.of(22020, Month.JUNE, 14, 00, 00, 00),
                LocalDateTime.of(22020, Month.DECEMBER, 31, 23, 59, 59), 35.50);

        genericResponse.setPrice(mockPriceDetail);
        genericResponse.setMessage(HttpStatus.OK.name());
        genericResponse.setStatus(HttpStatus.OK.value());

        when(priceServiceMock.getPrices(LocalDateTime.of(22020, Month.JUNE, 14, 10, 00, 00),
                35455, 1)).thenReturn(genericResponse);

        when(priceControllerMock.getPriceDetail(LocalDateTime.of(22020, Month.JUNE, 14, 10, 00, 00),
                35455, 1)).thenReturn(ResponseEntity.ok().body(genericResponse));
    }

    @Test
    void getPriceDetail() throws Exception {

        //this.mockMvc.perform(get("/price?date=2020-06-14 10:00:00&product_id=35455&brand_id=1")).andExpect(MockMvcResultMatchers.status().isOk());
        //mockMvc.perform(get("/price?date=2020-06-14 10:00:00&product_id=35455&brand_id=1")).andExpect(status().isOk());
    }

}