package br.com.daniel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.daniel.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
