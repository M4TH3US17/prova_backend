package com.example.demo.entities;

import com.example.demo.entities.enums.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "carros")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Carro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long    id;
    private String  nome;
    private Double  preco;
    private Double  km;
    private Boolean reservado;
    private String  urlImagem;
    private Integer ano;
    @Enumerated(EnumType.STRING)
    private Cor cor;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @ManyToOne @JoinColumn(name = "marca")
    private Marca   marca;

    public Carro(String nome, Double preco, Double km, Boolean reservado, String urlImagem, Integer ano, Cor cor, Tipo tipo, Marca marca)
    {
        this.nome      = nome;
        this.preco     = preco;
        this.km        = km;
        this.reservado = reservado;
        this.urlImagem = urlImagem;
        this.ano       = ano;
        this.cor       = cor;
        this.tipo      = tipo;
        this.marca     = marca;
    }
}
