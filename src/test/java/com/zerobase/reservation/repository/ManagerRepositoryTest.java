package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Manager;
import com.zerobase.reservation.model.entity.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;


@SpringBootTest
class ManagerRepositoryTest {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ShopRepository shopRepository;
    private EntityManager em;
    @BeforeEach
    void init() {
        Manager m = Manager.builder()
                .email("test@gmail.com")
                .phone("phone1")
                .name("name")
                .partner(true)
                .password("test")
                .shop(new ArrayList<>() {
                })
                .build();
        Shop s1 = Shop.builder()
                .phone("shop_phone1")
                .name("shop_name1")
                .location("shop_location1")
                .build();
        Shop s2 = Shop.builder()
                .phone("shop_phone2")
                .name("shop_name2")
                .location("shop_location2")
                .build();

        s1.setManager(m);
        s2.setManager(m);


        managerRepository.save(m);
        shopRepository.save(s1);
        shopRepository.save(s2);

    }

    @Test
    void findAllById() {
        Manager m = managerRepository.findAllById(1L).get();
        for(Shop shop:m.getShop()){
            System.out.println(shop.toString());
        }
    }
}