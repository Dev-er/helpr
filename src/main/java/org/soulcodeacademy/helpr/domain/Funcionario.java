package org.soulcodeacademy.helpr.domain;

import org.soulcodeacademy.helpr.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario extends Usuario{
    // Gera uma coluna dtype para indicar qual a subclasse

    //Coluna foto não é obrigatória
    private String foto;

    @ManyToOne //Muitos Funcionários para um cargo
    @JoinColumn(name = "id_cargo") // Cria uma coluna nova que é a chave estrangeira de Cargo
    private Cargo cargo; // Se eu quiser mudar o cargo do funcionario, basta mudar o objeto deste campo

    public Funcionario() {}

    public Funcionario(String foto, Cargo cargo) {
        this.foto = foto;
        this.cargo = cargo;
    }

    public Funcionario(Integer id, String nome, String email, String cpf, String senha, String foto, Cargo cargo) {
        super(id, nome, email, cpf, senha, Perfil.FUNCIONARIO); //Chamada do construtor do Usuário
        this.foto = foto;
        this.cargo = cargo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}

// Anotações de Relacionamento
//@OneToOne = 1:1
//@OneToMany/@ManyToOne = 1:N
//@ManyToMany = N:N

//Relacionamento Unidirecional = apenas uma das entidades "conhece" a outra
//Relacionamento Bidirecional = as duas entidades se "conhecem", uma delas tem que mandar

//===BIDIRECIONAL=======================
//
//CLASSE EMPREGADO
//List<Dependente> dependentes;
//
//
//CLASSE DEPENDENTE
//private Empregado empregado;
//
//===UNIDIRECIONAL=======================
//
//CLASSE CARGO
//<nenhum>
//
//CLASSE FUNCIONARIO
//private Cargo cargo;