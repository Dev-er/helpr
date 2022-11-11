package org.soulcodeacademy.helpr.domain.dto;

import javax.validation.constraints.NotBlank;

public class ClienteDTO extends UsuarioDTO {

    @NotBlank(message = "Este campo é obrigatório")
    private String telefone;

    private String cnpj;

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




