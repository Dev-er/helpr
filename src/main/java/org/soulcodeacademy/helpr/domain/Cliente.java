package org.soulcodeacademy.helpr.domain;

import org.soulcodeacademy.helpr.domain.enums.Perfil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;


@Entity
public class Cliente extends Usuario {

    @NotBlank(message = "Esta campo é obrigatório")
    private String telefone;

    private String cnpj;

    public Cliente() { }

    public Cliente(Integer id, String nome, String email, String cpf, String senha, String telefone, String cnpj) {
        super(id, nome, email, cpf, senha, Perfil.CLIENTE);
        this.telefone = telefone;
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
