package org.soulcodeacademy.helpr.domain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//DTO - Objeto de Tranferência de Dados
// É útil para validarmos as informações tranferidas pelo Cliente
public class CargoDTO {
    @NotBlank (message = "Campo nome é de preenchimento obrigatório") // impede que o campo nome seja enviado em branco
    private String nome;

    @NotBlank (message = "Campo descrição é de preenchimento obrigatório")
    private String descricao;
    @NotNull (message = "Campo salário é de preenchimento obrigatório")
    @Min(value= 500, message = "Campo Salário Inválido")
    @Max(value = 100000, message = "Campo salário inválido")
    private Double salario;

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
