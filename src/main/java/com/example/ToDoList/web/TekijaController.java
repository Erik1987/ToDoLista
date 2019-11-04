package com.example.ToDoList.web;

import java.util.List;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ToDoList.domain.TodoItem;
import com.example.ToDoList.ToDoListApplication;
import com.example.ToDoList.domain.ListaRepository;
import com.example.ToDoList.domain.Tekija;
import com.example.ToDoList.domain.TekijaRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Controller
public class TekijaController {
	private static final Logger log = LoggerFactory.getLogger(ToDoListApplication.class);
	
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
		model.addAttribute("todoitems", listarepository.findAll());
		log.info("fetch all books");
		for (TodoItem book : listarepository.findAll()) {
			for (int i = 0; i < book.getTekijat().size(); i++) {
				System.out.println("TEKIJAT: " + book.getTekijat().get(i));
			}
		System.out.println(book.getTeksti());
		System.out.println(book.getDateCreated());
		}
		return "ToDoList";
	}
	
	
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	// tekijän ja tehtävän lisääminen
	@RequestMapping(value = "/add")
	public String addTekija(Model model) {
		model.addAttribute("tekija", new Tekija());
		model.addAttribute("lista", new TodoItem());
		return "addtekija";
	}
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	// tallennus POSTAUS
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Tekija tekija) {

		System.out.println("PAIVA " + tekija.getLista().getDateCreated());
		System.out.println(tekija.getLista().getTeksti());
		TodoItem item = listarepository.save(new TodoItem(tekija.getLista().getDateCreated(), tekija.getLista().getTeksti()));
		Long todoid =   item.getListaId();
		tekija.setLista(item);
		
		repository.save(tekija);
		
		log.info("fetch all books");
		for (TodoItem book : listarepository.findAll()) {
			System.out.println("Taneli " + item.getListaId());
		System.out.println(book.getTeksti());
		System.out.println(book.getDateCreated());
		}
		
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
		model.addAttribute("itemit", listarepository.findAll());
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
