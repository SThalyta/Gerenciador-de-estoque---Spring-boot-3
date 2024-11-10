package com.example.springboot.controllers;

import com.example.springboot.dtos.MoveRecordDto;
import com.example.springboot.models.MoveModel;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.MoveRepository;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/moves")
public class MoveController {

    @Autowired
    MoveRepository moveRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Object> createMove(@RequestBody @Valid MoveRecordDto moveRecordDto) {
        Optional<ProductModel> productOptional = productRepository.findById(moveRecordDto.productId());

        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        ProductModel product = productOptional.get();
        MoveModel.TipoMovimentacao tipo = moveRecordDto.tipoMovimentacao();

        if (tipo == MoveModel.TipoMovimentacao.SAIDA && product.getQuantity() < moveRecordDto.quantity()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock for this operation.");
        }

        if (tipo == MoveModel.TipoMovimentacao.ENTRADA) {
            product.setQuantity(product.getQuantity() + moveRecordDto.quantity());
        } else {
            product.setQuantity(product.getQuantity() - moveRecordDto.quantity());
        }

        productRepository.save(product);
        MoveModel move = new MoveModel();
        move.setProductModel(product);
        move.setQuantity(moveRecordDto.quantity());
        move.setTipoMovimentacao(tipo);
        moveRepository.save(move);

        return ResponseEntity.status(HttpStatus.CREATED).body(move);
    }

    @GetMapping
    public ResponseEntity<?> getAllMoves() {
        return ResponseEntity.status(HttpStatus.OK).body(moveRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoveModel> getMoveById(@PathVariable UUID id) {
        Optional<MoveModel> move = moveRepository.findById(id);
        return move.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/products/{idProduct}")
    public ResponseEntity<List<MoveModel>> getMovesByProductId(@PathVariable UUID idProduct){
        List<MoveModel> movimentacoes = moveRepository.findAllByProductModel_IdProduct(idProduct);
        if(movimentacoes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimentacoes);

    }

}

