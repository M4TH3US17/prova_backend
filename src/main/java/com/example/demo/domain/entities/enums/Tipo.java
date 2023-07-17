package com.example.demo.domain.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Tipo {

    CONVERTIBLE("Convertible"), COUPE("Coupe"),
    HATCHBACK("Hatchback"),     PICKUP("Pickup"),
    SEDAN("Sedan"),             SUV("Suv"),
    VAN("Van");

    private String tipo;

    Tipo(String tipo) { this.tipo = tipo; }

    @JsonValue
    public String getTipo() {return tipo;}
}
