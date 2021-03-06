package com.example.model.services;

import com.example.model.entities.GenericResponse;
import com.example.model.entities.PriceDetail;
import com.example.model.dao.IPriceDao;
import com.example.model.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceServiceImpl {

    @Autowired
    private IPriceDao priceDao;

    /**
     * Method to get all prices filtered by date, productId and brandId
     *
     * @return
     */

    public GenericResponse getPrices(LocalDateTime date, long productId, int brandId) {
        GenericResponse genericResponse = new GenericResponse();

        try {
            List<Price> prices = priceDao.findPrices(date, productId, brandId);
            Price price = Collections.max(prices, Comparator.comparing(p -> p.getPriority()));
            PriceDetail priceDetail = new PriceDetail(price.getProductId(), price.getBrandId(), price.getPriceList(),
                    price.getStartDate(), price.getEndDate(), price.getPrice());
            genericResponse.setPrice(priceDetail);
            genericResponse.setStatus(HttpStatus.OK.value());
            genericResponse.setMessage(HttpStatus.OK.name());
        } catch (Exception e) {
            genericResponse.setPrice(null);
            genericResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            genericResponse.setMessage(e.getMessage());
        }

        return genericResponse;
    }
}
