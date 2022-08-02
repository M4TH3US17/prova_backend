package com.example.demo.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Cor {

    AMARELO("Amarelo"),   AZUL("Azul"),
    BEGE("Bege"),         BRANCO("Branco"),
    CINZA("Cinza"),       DOURADO("Dourado"),
    LARANJA("Laranja"),   MARROM("Marrom"),
    PRATA("Prata"),       PRETO("Preto"),
    ROXO("Roxo"),         VERDE("Verde"),
    VERMELHO("Vermelho"), OUTROS("Outros");

    private String cor;

    Cor() {}

    @JsonValue
    public String getCor() { return cor; }

    private Cor(String cor) { this.cor = cor; }

    @Override
    public String toString() { return this.cor; }
}
