package com.example.controller;

import com.example.model.entities.GenericResponse;
import com.example.model.entities.PriceDetail;
import com.example.model.services.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceServiceImpl priceService;

    GenericResponse genericResponse;

    @BeforeEach
    void setUp() {
        genericResponse = new GenericResponse();
        PriceDetail mockPriceDetail = new PriceDetail(35455, 1, 1,
                LocalDateTime.of(2020, Month.JUNE, 20, 00, 00, 00),
                LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59), 35.50);
        genericResponse.setPrice(mockPriceDetail);
        genericResponse.setStatus(HttpStatus.OK.value());
        genericResponse.setMessage(HttpStatus.OK.name());
    }

    @Test
    void getPriceDetail() throws  Exception {

        when(priceService.getPrices(LocalDateTime.of(2020, Month.JUNE, 14, 10, 00, 00),
                35455, 1)).thenReturn(genericResponse);

        mockMvc.perform(get("/api/price").param("date", "2020-06-14 10:00:00")
                .param("product_id", "35455").param("brand_id","1")
                .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.price.productId").value("35455"));

        verify(priceService).getPrices(LocalDateTime.of(2020, Month.JUNE, 14, 10, 00, 00),
                35455, 1);
    }
}