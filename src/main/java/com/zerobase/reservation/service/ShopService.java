package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.model.dto.ShopDto;
import com.zerobase.reservation.model.entity.Shop;
import com.zerobase.reservation.model.entity.constraint.SortType;
import com.zerobase.reservation.repository.ShopRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;

  public List<ShopDto.Simple> getAllShop() {
    return shopRepository.findAll().stream().map(ShopDto::toSimple).collect(Collectors.toList());
  }

  public ShopDto.Detail getDetail(Long shop_id) {
    Shop s = shopRepository.findById(shop_id)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOP));
    return ShopDto.toDetail(s);
  }

  public List<ShopDto.Simple> getSortedShop(String sortType) {
    var sort = SortType.valueOfText(sortType);
    List<Shop> r;
    switch (sort) {
      case NAME:
        r = shopRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        break;
      case RATE:
        r = shopRepository.findAll(Sort.by(Sort.Direction.DESC, "rate"));
        break;
      default:
        return getAllShop();
    }

    return r.stream().map(ShopDto::toSimple).collect(Collectors.toList());
  }


}
