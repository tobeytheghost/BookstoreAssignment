package ca.sheridancollege.falzonm.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.sheridancollege.falzonm.beans.Book;
import ca.sheridancollege.falzonm.database.DatabaseAccess;


@Controller
public class HomeController {
	
	//inject the database access
	@Autowired
	private DatabaseAccess da;
	
	//add a copy of the array 
	List<Book> bookList = new CopyOnWriteArrayList<>();
	
	//Mapping for Index
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		
		da.insertBook(new Book());
		return "index";
	}
	
	//Mapping for inserting a book into the database
	
	//Mapping for deleting an entry
	@GetMapping("/deleteBookById/{id}")
	public String deleteBookById(Model model, @PathVariable Long id) {
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		da.deleteBookById(id);
		
		return "index";
	}
	
	//Mapping for Editing the book
	@GetMapping("/editBookById/{id}")
	public String editBookById(Model model, @PathVariable Long id) {
		Book book = da.getBookListById(id).get(0);
		
		model.addAttribute("book", book);
		
		//delete the existing record
		da.deleteBookById(id);
		
		//refresh the list
		model.addAttribute("bookList", da.getBookList());
		
		return "index";
	}
	
	

}
