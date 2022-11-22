package org.soulcodeacademy.helpr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TokenFilter irá verificar o token do cliente, uma vez por requisição

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UsuarioSecurityService service;

    //Este método é chamado para toda requisição feita pelo cliente
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!validarCabecalho(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = this.extrairToken(request);

        if(!this.tokenUtil.validarToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //Passo 4
        String email = this.tokenUtil.extrairEmail(token);
        UserDetails usuario = this.service.loadUserByUsername(email);
        //Configura o usuário encontrado como autenticado na aplicação
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities()));
        filterChain.doFilter(request, response);
        //Resumo: Extrai do cabeçalho as informações do token,
        //para a segurança da aplicação bysca dados do usuário e então indica
        //para a segurança da aplicação que o usuário válido
        //Os próximos filtros já irão conhecer o usuário e permitir o acesso
    }

    // Passo 3
    private String extrairToken(HttpServletRequest request) {
        // "Bearer <JWT>"
        String cabecalho = request.getHeader("Authorization");

        return  cabecalho.substring(7); // Retorna apenas o código do JWT

    }

    // Passo 1 e 2
    private boolean validarCabecalho(HttpServletRequest request) {
        //extrair do cliente o cabeçalho com o possível token
        String cabecalho = request.getHeader("Authorization");
        //O cabeçalho enviado pelo clienteé válido se
        // Possuir o Authorization e o valor começar com "Bearer eysdadadad"
        return cabecalho != null && cabecalho.startsWith("Bearer");
    }
}
