package com.dh.grupo01.clinicaodontologica.service.impl;

import com.dh.grupo01.clinicaodontologica.entity.Perfil;
import com.dh.grupo01.clinicaodontologica.entity.Usuario;
import com.dh.grupo01.clinicaodontologica.entity.dto.UsuarioDTO;
import com.dh.grupo01.clinicaodontologica.exception.CadastroInvalidoException;
import com.dh.grupo01.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.grupo01.clinicaodontologica.repository.PerfilRepository;
import com.dh.grupo01.clinicaodontologica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UsuarioIMPLService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PerfilRepository perfilRepository;

    public List<UsuarioDTO> buscar(){
        List<Usuario> listUsuario = repository.findAll();
        List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Usuario usuario : listUsuario) {
            UsuarioDTO usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);
            listUsuarioDTO.add(usuarioDTO);
        }
        log.info("Buscando Usuários |  public List<UsuarioDTO> buscar()|");
        return listUsuarioDTO;
    }

    public ResponseEntity salvar(UsuarioDTO usuarioDTO) throws CadastroInvalidoException {
        ObjectMapper mapper = new ObjectMapper();
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);

        try{
            //Encriptando a senha do usuário antes de salvá-la no banco
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            usuario.setPassword(bCrypt.encode(usuario.getPassword()));

            //Convertendo a descrição da permissão passada pelo DTO em um perfil
            List<Perfil> perfil = new ArrayList<>();
            perfil.add(perfilRepository.findByDescricao(usuarioDTO.getPerfil()).get());
            usuario.setPerfis(perfil);

            Usuario usuarioSalvo = repository.save(usuario);
            log.info("Salvando Usuário |  public ResponseEntity salvar()|" + usuarioSalvo.getUsername());
            return new ResponseEntity("Usuário " + usuarioSalvo.getUsername() + " criado com sucesso", HttpStatus.CREATED);

        }catch (Exception e){
            log.info("Erro ao salvar Usuário |  public ResponseEntity salvar()|");
            throw  new CadastroInvalidoException("Usuário não cadastrado");
        }
    }

    public ResponseEntity deletar(String username) throws ResourceNotFoundException {
        Optional<Usuario> usuario = repository.findByUsername(username);
        if (usuario.isEmpty()){
            log.info("Erro deletando Usuário |  public ResponseEntity deletar |");
            throw  new ResourceNotFoundException("Usuário não encontrado");
        }
        repository.deleteById(usuario.get().getId());
        log.info("Deletando Usuário |  public ResponseEntity deletar|" + usuario.get().getId());
        return new ResponseEntity("Excluído com sucesso", HttpStatus.OK);

    }

    public ResponseEntity atualizarSenha(UsuarioDTO usuarioDTO) throws ResourceNotFoundException {
        Optional<Usuario> usuario = repository.findByUsername(usuarioDTO.getUsername());
        if (usuario.isEmpty()){
            log.info("Erro ao encontrar Usuário |  public ResponseEntity trocarSenha |");
            throw  new ResourceNotFoundException("Usuário não encontrado");
        }
        Usuario usuarioToUpdate = usuario.get();
        if(usuarioDTO.getPassword() != null){
            usuarioToUpdate.setPassword(usuarioDTO.getPassword());
        }
        repository.save(usuarioToUpdate);
        log.info("Atualizando senha do Usuário| public ResponseEntity atualizarParcial |"+ usuarioDTO.getUsername());
        return new ResponseEntity("Senha alterada com sucesso", HttpStatus.OK);
    }

}
