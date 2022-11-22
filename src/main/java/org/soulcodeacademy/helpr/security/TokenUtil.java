package org.soulcodeacademy.helpr.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

//Objetivo desta classe é:
//Validar JWT, Gerar JWT e extrair dados do JWT

@Component //Instanciar automaticamente o TokenUtil
public class TokenUtil {
    @Value("${senhaJwt}") //injeta o valor da variável no campo abaixo
    private String senhaJwt;

    @Value("${validaddeJwt}")
    private long validadeJwt;

    public String gerarToken(String email, String perfil) {
        return JWT.create()
                .withSubject(email)
                .withClaim("perfil", perfil)
                .withExpiresAt(new Date(System.currentTimeMillis() + this.validadeJwt))
                .sign(Algorithm.HMAC512(this.senhaJwt));
    }

    public String extrairEmail(String token) {
        return JWT.require(Algorithm.HMAC512(this.senhaJwt))
                .build()
                .verify(token)
                .getSubject(); // dados do email

    }

    public boolean validarToken(String token) {
        //Caso ocorra erro na linha 42, o token passado é invalido:
        //Não foi gerado por nós ou expirou
        try {
            JWT.require(Algorithm.HMAC512(this.senhaJwt))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }

    }
}

//Teste de JWT

/*  public static void main(String[] args) {
        String senha = "senhatop";

        String token = JWT.create()
                .withSubject("jr@gmail.com")
                .withClaim("nome", "batata")
                .sign(Algorithm.HMAC512(senha));

        String email = JWT.require(Algorithm.HMAC512(senha)).build().verify(token).getSubject();
        String batata = JWT.require(Algorithm.HMAC512(senha)).build().verify(token).getClaim("nome").asString();
        System.out.println(email);
        System.out.println(batata);*/