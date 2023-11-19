package ca.sheridancollege.falzonm.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.falzonm.beans.Book;
import ca.sheridancollege.falzonm.database.DatabaseAccess;


@Controller
public class HomeController {
	
	//inject the database access
	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	//add a copy of the array 
	List<Book> bookList = new CopyOnWriteArrayList<>();
	
	//Mapping for Index
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		
		return "index";
	}
	
	//Mapping for inserting a book into the database
	@PostMapping("/insertBook")
	public String insertBook(Model model, @ModelAttribute Book book) {
		da.insertBook(book);
		
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		
		return "index";
	}
	
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
	
	//Mapping for Public Index to View Book Details 
	@GetMapping("/details/{id}")
	public String viewDetails(@PathVariable Long id, Model model) {
		Book book = da.getBookListById(id).get(0);
		model.addAttribute("book", book);
		return "details";
	}
	
	//Security Controller Information
	
	//Mapping for Login
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/secure")
	public String secureIndex() {
		return "/secure/index";
	}
	
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}
	
	//Mapping for Register Page
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	//Post Mapping after a User Registers 
	@PostMapping("/register")
	public String postRegister(@RequestParam String userName, @RequestParam String password) {
		da.addUser(userName, password);
		
		Long userId = da.findUserAccount(userName).getUserId();
		
		da.addRole(userId, Long.valueOf(1));
		
		return "redirect:/secure/index";
	}
	
	
	

}
