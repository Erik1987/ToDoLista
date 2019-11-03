package com.example.ToDoList.web;

import java.util.List;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ToDoList.domain.Lista;
import com.example.ToDoList.domain.ListaRepository;
import com.example.ToDoList.domain.Tekija;
import com.example.ToDoList.domain.TekijaRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Controller
public class TekijaController {
	@Autowired
	private TekijaRepository repository;
	
	@Autowired
	private ListaRepository listarepository;
	
	// Kirjautuminen sivulle
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    // /ToDoList pääte, pääsivulle
	@RequestMapping(value = { "/", "/ToDoList" })
	public String tekijaList(Model model) {
		model.addAttribute("tekijat", repository.findAll());
		return "ToDoList";
	}
	
	
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	// tekijän ja tehtävän lisääminen
	@RequestMapping(value = "/add")
	public String addTekija(Model model) {
		model.addAttribute("tekija", new Tekija());
		model.addAttribute("lista", new Lista());
		return "addtekija";
	}
	// tallennus POSTAUS
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Tekija tekija, Lista lista) {
		repository.save(tekija);
		listarepository.save(lista);
		return "redirect:ToDoList";
	}
	// poisto osio CRUDia varten, poisto vain admin oikeuksin
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTekija(@PathVariable("id") Long tekijaId, Model model) {
		repository.deleteById(tekijaId);
		return "redirect:../ToDoList";
	}
	// edit osio CRUDia varten, muokkaus vain admin oikeuksin
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}")
	public String addBook(@PathVariable("id") Long tekijaId, Model model){
		model.addAttribute("tekija", repository.findById(tekijaId));
		model.addAttribute("listat", listarepository.findAll());
		return "edittekijat";
		
	}
	
	// RESTful service to get all makers
    @RequestMapping(value="/tekijat", method = RequestMethod.GET)
    public @ResponseBody List<Tekija> tekijaListRest() {	
        return (List<Tekija>) repository.findAll();
    }    

	// RESTful service to get maker by id
	@JsonIgnore
    @RequestMapping(value="/tekija/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Tekija> findTekijaRest(@PathVariable("id") Long tekijaId) {	
    	return repository.findById(tekijaId);
    }  
}
