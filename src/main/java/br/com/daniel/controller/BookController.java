package br.com.daniel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniel.model.Book;
import br.com.daniel.repository.BookRepository;

@RestController
@RequestMapping(value = "book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findById(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency) {
		var port = environment.getProperty("local.server.port");
		Book obj = repository.findById(id).get();
		obj.setCurrency(currency);
		obj.setEnvironment(port);
		return obj;
	}

}
