package com.dh.grupo01.clinicaodontologica.security;


import com.dh.grupo01.clinicaodontologica.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${clinica.odontologica.jwt.expiration}")
    private String expiracao;

    @Value("${clinica.odontologica.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication){
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Date dataHoje = new Date();
        Date dataExpiracao = new Date(dataHoje.getTime() + Long.parseLong(this.expiracao));
        String token = Jwts.builder()
                .setIssuer("API DH Clinica Odontologica")
                .setSubject(usuarioLogado.getUsername())
                .setIssuedAt(dataHoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
        return token;
    }

    public boolean verificaToken(String token){
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getUsernameUsuario(String token){
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            return username;
    }

}
