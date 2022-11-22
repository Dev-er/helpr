package org.soulcodeacademy.helpr.security;

//Esta classe serve de ponte para a comunicação entre
// a persistência e o framework Spring Security

import org.soulcodeacademy.helpr.domain.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UsuarioSecurity implements UserDetails {
    //Dados de autenticação
    private String email;
    private String senha;
    
    //Dados de autorização
    private Perfil perfil;

    //Armazena as permissões / autoridades do usuário
    private ArrayList<SimpleGrantedAuthority>authorities = new ArrayList<>();

    public UsuarioSecurity(String email, String senha, Perfil perfil) {
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        //Adiciona a descrição do perfil com uma role do usuário
        this.authorities.add(new SimpleGrantedAuthority(perfil.getDescricao()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Indica para o Security as permissões/papeis di meu usuário
        return this.authorities;
    }

    @Override
    public String getPassword() {
        //Indica para o Security que o usuário possui a senha abaixo
        return this.senha;
    }

    @Override
    public String getUsername() {
        //Indica para o Security que o nosso usuário possui o email abaixo
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        //Flag que indica se a conta está válida
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //Flag indica se a conta está desbloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //Flag que indica se as credenciais estão válidas
        return true;
    }

    @Override
    public boolean isEnabled() {
        //Flag que indica se o usuário está habilitado
        return true;
    }
    //Indicamos por meio de getters o estado de autenticação / autorização
    //dos nossos usuários
}
