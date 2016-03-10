package br.com.arabella.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.arabella.app.service.EnderecoService;
import br.com.arabella.app.viewmodel.Endereco;

@RestController
@RequestMapping(value = "/cep", produces=MediaType.APPLICATION_JSON_VALUE)
public class EnderecoController {
	@Autowired
	private EnderecoService service;
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public Endereco findByCep(String CEP, boolean mask) {
		return service.findByCep(CEP, mask);
	}
}
