package com.curso.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.crud.model.Usuario;
import com.curso.crud.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	//Inserir Usuário

	@RequestMapping(value = "/usuario/{nome}/idade/{idade}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String salvarUsuario(@PathVariable String nome, @PathVariable int idade) {

		Usuario usuario = new Usuario();

		usuario.setNome(nome);

		usuario.setIdade(idade);

		usuarioRepository.save(usuario);

		return "Salvo o usuario " + nome;

	}

	//Deletar Usuário
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {

		usuarioRepository.deleteById(id);

		return "Deletado com Sucesso";
	}
	
	//Listar todos os Usuários

	@GetMapping(value = "listartodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listar() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);

	}

}
