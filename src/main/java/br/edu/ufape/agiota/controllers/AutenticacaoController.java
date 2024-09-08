package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.DadosAutenticacao;
import br.edu.ufape.agiota.dtos.DadosTokenJWT;
import br.edu.ufape.agiota.dtos.UsuarioAutenticadoDTO;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import br.edu.ufape.agiota.negocio.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        var usuario = authentication.getPrincipal();
        var tipo = usuario.getClass().toString();
        tipo = tipo.substring(tipo.lastIndexOf(".") + 1);

        UsuarioAutenticadoDTO usuarioAutenticadoDTO = new UsuarioAutenticadoDTO(
                ((Usuario) usuario).getId(),
                tipo,
                ((Usuario) usuario).getNome(),
                ((Usuario) usuario).getEmail()
        );


        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT, usuarioAutenticadoDTO));
    }
}