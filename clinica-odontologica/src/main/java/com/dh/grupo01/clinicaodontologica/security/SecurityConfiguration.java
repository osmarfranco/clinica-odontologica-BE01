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
                //
                // NÃO ESTÁ TERMINADO POIS APRENDEREMOS O PERMISSIONAMENTO NA QUINTA
                //
                //Permissões de acesso para paciente
                .antMatchers(HttpMethod.GET, "/paciente").permitAll()
                .antMatchers(HttpMethod.GET, "/paciente/buscarCpf/{cpf}").permitAll() // se der erro, tirar o /paciente da frente
                .antMatchers(HttpMethod.POST, "/paciente").permitAll()
                .antMatchers(HttpMethod.PUT, "/paciente").permitAll()
                .antMatchers(HttpMethod.PATCH, "/paciente").permitAll()
                .antMatchers(HttpMethod.DELETE, "/paciente").permitAll()
                //Permissões de acesso para dentista
                .antMatchers(HttpMethod.GET, "/dentista").permitAll()
                .antMatchers(HttpMethod.GET, "/dentista/buscarCro/{cro}").permitAll() //se der erro, tirar o /dentista da frente
                .antMatchers(HttpMethod.POST, "/dentista").permitAll()
                .antMatchers(HttpMethod.PUT, "/dentista").permitAll()
                .antMatchers(HttpMethod.PATCH, "/dentista").permitAll()
                .antMatchers(HttpMethod.DELETE, "/dentista").permitAll()
                //Permissões de acesso para consultas
                .antMatchers(HttpMethod.GET, "/consulta").permitAll()
                .antMatchers(HttpMethod.GET, "/consulta/buscarIdConsulta/{idConsulta}").permitAll() // se der erro tirar o /consulta da frente
                .antMatchers(HttpMethod.POST, "/consulta").permitAll()
                .antMatchers(HttpMethod.DELETE, "/consulta").permitAll()
                //Bloqueando acesso a qualquer outra rota que não tenha sido mapeada aqui
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(autenticacaoViaTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
