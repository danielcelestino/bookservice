package br.com.daniel.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.daniel.model.Book;
import br.com.daniel.repository.BookRepository;
import br.com.daniel.response.Cambio;

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
		var book = repository.findById(id).get();
		if(book == null) throw new RuntimeException("Book not found!");
		
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from","USD");
		params.put("to", currency);
		var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);
		Cambio cambio = response.getBody();
		var port = environment.getProperty("local.server.port");
		book.setPrice(cambio.getConvertedValue());
		book.setEnvironment(port);
		return book;
	}

}
