package br.com.daniel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/*
 * Classe para testar possiveis problemas em ambientes de alta complexidade com microserviços
 * (Relisience4j)
 */

@RestController
@RequestMapping(value = "book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);

	@GetMapping("/foo-bar")
//	@Retry(name = "default") //tenta 3 vezes antes de fechar
//	@Retry(name = "foo-bar-att", fallbackMethod = "fallbackMethod1") //tenta 5 vezes antes de fechar por application.yml
	@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod1") //pode ser testdo com power shell. Ele não envia todas as requisições e acaba filtrando algumas    ------   no power shell:   while(1) {curl http://localhost:8765/book-service/foo-bar; sleep 0.1}
	public String fooBar() {
		logger.info("Request to foo-bar is received!");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class); //Provocando erro com endereço inexistente
//		return "Foo-bar!!!";
		return "################################################ " + forEntity.getBody();
	}
	
	public String fallbackMethod1(Exception ex) {
		return "fallbackMethod foo-bar!!!";
	}
}
