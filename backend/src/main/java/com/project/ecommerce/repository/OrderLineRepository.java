package com.project.ecommerce.repository;

import com.project.ecommerce.entity.Command;
import com.project.ecommerce.entity.OrderLine;
import com.project.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    public Optional<OrderLine> findByCommandIdAndProductId(Long commandId, Long productId);
}
