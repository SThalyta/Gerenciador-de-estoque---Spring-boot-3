package com.example.springboot.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_MOVE")
public class MoveModel {

    public enum TipoMovimentacao {
        ENTRADA, SAIDA;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMovimentation;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel productModel;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Column(nullable = false)
    private LocalDateTime dataMovimento;

    public MoveModel() {
        // Define dataMovimento como o momento atual ao criar uma nova instância
        this.dataMovimento = LocalDateTime.now();
    }

    // Getters e Setters
    public UUID getIdMovimentation() {
        return idMovimentation;
    }

    public void setIdMovimentation(UUID idMovimentation) {
        this.idMovimentation = idMovimentation;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public LocalDateTime getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }
}

