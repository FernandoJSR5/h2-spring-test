package com.example.controller;

import com.example.model.entities.GenericResponse;
import com.example.model.services.PriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api")

public class PriceController {

    private PriceServiceImpl priceService;

   public PriceController(PriceServiceImpl priceService) {
       this.priceService = priceService;
   }

    @GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> getPriceDetail(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                              @RequestParam(value = "date") LocalDateTime date,
                                                 @RequestParam(value = "product_id") long productId,
                                                 @RequestParam(value = "brand_id") int brandId) {
        try {
            GenericResponse response = priceService.getPrices(date, productId, brandId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
