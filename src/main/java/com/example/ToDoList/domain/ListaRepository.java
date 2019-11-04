package com.example.ToDoList.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ListaRepository extends CrudRepository<TodoItem, Long> {

    List<TodoItem> findByDateCreated(Date dateCreated);
    List<TodoItem> findByTeksti(String teksti);
    
}
