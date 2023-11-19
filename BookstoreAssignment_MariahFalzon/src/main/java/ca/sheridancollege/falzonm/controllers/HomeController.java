package ca.sheridancollege.falzonm.controllers;

import java.util.ArrayList;
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
import jakarta.servlet.http.HttpSession;


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
	@PostMapping("/secure/editBook/insertBook")
	public String insertBook(Model model, @ModelAttribute Book book) {
		da.insertBook(book);
		
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		
		return "secure/editBook";
	}
	
	//Mapping for deleting an entry
	@GetMapping("/secure/editBook/deleteBookById/{id}")
	public String deleteBookById(Model model, @PathVariable Long id) {
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		da.deleteBookById(id);
		
		return "secure/editBook";
	}
	
	//Mapping for Editing the book
	@GetMapping("/secure/editBook/editBookById/{id}")
	public String editBookById(Model model, @PathVariable Long id) {
		Book book = da.getBookListById(id).get(0);
		
		model.addAttribute("book", book);
		
		//delete the existing record
		da.deleteBookById(id);
		
		//refresh the list
		model.addAttribute("bookList", da.getBookList());
		
		return "secure/editBook";
	}
	
	//Mapping for Public Index to View Book Details 
	@GetMapping("/details/{id}")
	public String viewDetails(@PathVariable Long id, Model model) {
		Book book = da.getBookListById(id).get(0);
		model.addAttribute("book", book);
		return "details";
	}
	
	
	//Mapping for Secure Pages
	
	@GetMapping("/secure/index")
	public String secureIndex(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		return "secure/index";
	}
	
	@GetMapping("/secure/details/{id}")
	public String viewSecureDetails(@PathVariable Long id, Model model) {
		Book book = da.getBookListById(id).get(0);
		model.addAttribute("book", book);
		return "secure/details";
	}
	
	@GetMapping("/secure/editBook")
	public String viewEditBooks(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		return "secure/editBook";
	}
	
	
	
	//Security Controller Information
	
	//Mapping for Login
	@GetMapping("/login")
	public String login() {
		return "login";
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
	public String postRegister(@RequestParam String userName, @RequestParam String email, @RequestParam String password) {
		da.addUser(userName, email, password);
		
		Long userId = da.findUserAccount(userName).getUserId();
		
		da.addRole(userId, Long.valueOf(1));
		
		return "redirect:/secure/index";
	}
	
	//Shopping Cart Code
	
	
	List<Book> cart = new CopyOnWriteArrayList<>();
	
	@GetMapping("/secure/shoppingCart")
	public String viewCart(Model model) {
		
		
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		
		//Calculate Cost
		double total = calculateTotal(cart);
		model.addAttribute("total", total);
		
		
		return "secure/shoppingCart";
	}
	
	private double calculateTotal(List<Book> cart) {
		double total = 0.0;
		if (cart != null) {
			for (Book book : cart) {
				total += (book.getPrice() * book.getQuantity());
			}
		}
		return total;
	}


	@PostMapping("/secure/index/addToCart/{id}")
	public String addToCart(@PathVariable Long id, @RequestParam int quantity, Model model, @ModelAttribute Book cart, HttpSession session) {
		
		Book book = da.getBookListById(id).get(0);
		
		 List<Book> cartItems = (List<Book>) session.getAttribute("cartItems");
		    if (cartItems == null) {
		        cartItems = new ArrayList<>();
		        session.setAttribute("cartItems", cartItems);
		    }

		
		for (int i = 0; i < quantity; i++) {
            cart.add(book);
        }
		
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", da.getBookList());
		
	
		return "redirect:/secure/index";
	}

	
	@GetMapping("/secure/thankyou")
	public String viewThankYou() {
		return "secure/thankYou";
	}
	
	
	@PostMapping("/secure/shoppingCart/updateCart")
	public String updateCart(@RequestParam Long id, @RequestParam int quantity, HttpSession session) {
		
		List<Book> cart = (List<Book>) session.getAttribute("cart");
		if (cart != null) {
			if (quantity <= 0) {
				// Remove the book if quantity is less than or equal to 0
				cart.removeIf(book -> book.getIsbn().equals(id));
			} else {
				// Update the quantity for the book
				for (Book cartBook : cart) {
					if (cartBook.getIsbn().equals(id)) {
						cartBook.setQuantity(quantity);
						break;
					}
				}
			}
			session.setAttribute("cart", cart);
		}
		
		return "redirect:/secure/shoppingCart";
	}
	
}
