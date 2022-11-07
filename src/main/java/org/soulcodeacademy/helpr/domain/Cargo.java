package org.soulcodeacademy.helpr.domain;

import javax.persistence.*;


// VAi transformar nossa classe em uma entidade na tabela para usar nossa classe
@Entity
public class Cargo { // nome da tabela = cargo
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Integer IdCargo; //Será a chave primária da tabela

    @Column(nullable = false, length = 50) // NOT NULL, e máximo de 50 caracteres
    private String nome;
    @Column(nullable = false, length = 120)
    private String descricao;
    @Column(nullable = false) //@Column serve para customizar a coluna
    private Double salario;

    // a ORM usará este construtor conjuntamente com Getters e Setters
    public Cargo() {}

    public Cargo(Integer idCargo, String nome, String descricao, Double salario) {
        IdCargo = idCargo;
        this.nome = nome;
        this.descricao = descricao;
        this.salario = salario;
    }

    public Integer getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(Integer idCargo) {
        IdCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
