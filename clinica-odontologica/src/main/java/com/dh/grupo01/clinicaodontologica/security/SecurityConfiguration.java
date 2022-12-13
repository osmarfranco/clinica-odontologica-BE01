package com.dh.grupo01.clinicaodontologica.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    AutenticacaoViaTokenFilter autenticacaoViaTokenFilter;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //Permitindo autenticação para todos
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/v3/api-docs/**","/swagger-ui/**").permitAll()
                //Permissões de acesso para paciente
                .antMatchers(HttpMethod.GET, "/paciente").hasAnyAuthority("ADMIN", "ATENDENTE", "DENTISTA")
                .antMatchers(HttpMethod.GET, "/paciente/buscarCpf/{cpf}").hasAnyAuthority("ADMIN", "ATENDENTE") // se der erro, tirar o /paciente da frente
                .antMatchers(HttpMethod.POST, "/paciente").hasAnyAuthority("ADMIN", "ATENDENTE", "PACIENTE")
                .antMatchers(HttpMethod.PUT, "/paciente").hasAnyAuthority("ADMIN", "ATENDENTE", "PACIENTE")
                .antMatchers(HttpMethod.PATCH, "/paciente").hasAnyAuthority("ADMIN", "ATENDENTE", "PACIENTE")
                .antMatchers(HttpMethod.DELETE, "/paciente").hasAuthority("ADMIN")
                //Permissões de acesso para dentista
                .antMatchers(HttpMethod.GET, "/dentista").hasAnyAuthority("ADMIN", "ATENDENTE", "PACIENTE")
                .antMatchers(HttpMethod.GET, "/dentista/buscarCro/{cro}").hasAnyAuthority("ADMIN", "ATENDENTE", "PACIENTE") //se der erro, tirar o /dentista da frente
                .antMatchers(HttpMethod.POST, "/dentista").hasAnyAuthority("ADMIN", "ATENDENTE", "DENTISTA")
                .antMatchers(HttpMethod.PUT, "/dentista").hasAnyAuthority("ADMIN", "ATENDENTE", "DENTISTA")
                .antMatchers(HttpMethod.PATCH, "/dentista").hasAnyAuthority("ADMIN", "ATENDENTE", "DENTISTA")
                .antMatchers(HttpMethod.DELETE, "/dentista").hasAuthority("ADMIN")
                //Permissões de acesso para consultas
                .antMatchers(HttpMethod.GET, "/consulta").hasAnyAuthority("ADMIN", "ATENDENTE", "PACIENTE")
                .antMatchers(HttpMethod.GET, "/consulta/buscarIdConsulta/{idConsulta}").hasAnyAuthority("ADMIN", "ATENDENTE", "PACIENTE") // se der erro tirar o /consulta da frente
                .antMatchers(HttpMethod.POST, "/consulta").hasAnyAuthority("ADMIN", "ATENDENTE", "PACIENTE")
                .antMatchers(HttpMethod.DELETE, "/consulta").hasAnyAuthority("ADMIN", "ATENDENTE")
                //Permissões de acesso para usuários
                .antMatchers(HttpMethod.GET, "/usuario").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/usuario").hasAnyAuthority("ADMIN", "ATENDENTE", "DENTISTA", "PACIENTE")
                .antMatchers(HttpMethod.PATCH, "/usuario").hasAnyAuthority("ADMIN", "ATENDENTE", "DENTISTA", "PACIENTE")
                .antMatchers(HttpMethod.DELETE, "/usuario").hasAuthority("ADMIN")
                //Permissões de acesso para perfis
                .antMatchers(HttpMethod.GET, "/perfil").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/perfil").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/perfil").hasAuthority("ADMIN")
                //Bloqueando acesso a qualquer outra rota que não tenha sido mapeada aqui
                .anyRequest().authenticated().and().cors()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(autenticacaoViaTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
