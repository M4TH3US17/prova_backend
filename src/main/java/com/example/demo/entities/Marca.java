package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "marcas") @Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long   id;
    private String marca;
}
