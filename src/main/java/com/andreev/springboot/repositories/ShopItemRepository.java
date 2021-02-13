package com.andreev.springboot.repositories;

import com.andreev.springboot.entities.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ShopItemRepository extends JpaRepository <ShopItem, Long> {
    List<ShopItem> findAllByAmountGreaterThanOrderByPriceDesc(int amount);

    ShopItem findByIdAndAmountGreaterThan(Long id, int amount);
}
