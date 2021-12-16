package com.example.model.services;
import com.example.model.common.GenericResponse;
import java.time.LocalDateTime;

public interface IPriceService {
    public GenericResponse getPrices(LocalDateTime date, long productId, int brandId);
}
