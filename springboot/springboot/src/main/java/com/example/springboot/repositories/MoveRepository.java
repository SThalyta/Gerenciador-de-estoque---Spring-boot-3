package com.example.springboot.repositories;

import com.example.springboot.models.MoveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MoveRepository extends JpaRepository<MoveModel, UUID> {
    List<MoveModel> findAllByProductModel_IdProduct(UUID idProduct);
}

