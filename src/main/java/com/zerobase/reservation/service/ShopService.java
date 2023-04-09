package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.model.dto.ShopDto;
import com.zerobase.reservation.model.entity.Shop;
import com.zerobase.reservation.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {
    private final ShopRepository shopRepository;

    public List<ShopDto.Simple> getAllShop(){
        List<Shop> shopList = shopRepository.findAll();
        List<ShopDto.Simple> result= new ArrayList<>();
        for (Shop s:shopList){
            log.info("shop",s);
            result.add(ShopDto.toSimple(s));
        }
        return result;
    }
    public ShopDto.Detail getDetail(Long shop_id){
        Shop s=shopRepository.findById(shop_id).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_SHOP));
        return ShopDto.toDetail(s);
    }

}
