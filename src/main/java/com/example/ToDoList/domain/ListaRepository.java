package com.example.ToDoList.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ListaRepository extends CrudRepository<Lista, Long> {

    List<Lista> findByDateCreated(Date dateCreated);
    List<Lista> findByTeksti(String teksti);
    
}
