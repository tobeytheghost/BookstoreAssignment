package ca.sheridancollege.falzonm.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.falzonm.beans.Book;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	//insertBook code for admin 
	public void insertBook (Book book) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		//Adding the parameters 
		namedParameters.addValue("booksIsbn", book.getIsbn());
		namedParameters.addValue("booksTitle", book.getTitle());
		namedParameters.addValue("booksAuthor", book.getAuthor());
		namedParameters.addValue("booksCategory", book.getCategory());
		namedParameters.addValue("booksPrice", book.getPrice());
		namedParameters.addValue("booksDescription", book.getDescription());
		
		//Write Query for the Book
		
		String query = "INSERT INTO books(isbn, title, author, category, price, description) VALUES(:booksIsbn, :booksTitle, :booksAuthor, :booksCategory, :booksPrice, :booksDescription)";
		
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if (rowsAffected > 0) System.out.println("Book inserted into database");
	}
	
	
	//getBookList code to display the list 
	public List<Book> getBookList(){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM books";
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
	}
	
	
	//deleteBookListById for admin
	public void deleteBookById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "DELETE FROM books WHERE id = :id";
		
		namedParameters.addValue("id", id);
		
		if(jdbc.update(query, namedParameters) > 0 ) {
			System.out.println("Deleted book " + id + " from the database");
		}
	}
	
	
	//getBookListById for admin
	public List<Book> getBookListById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM books WHERE id = :id";
		
		namedParameters.addValue("id", id);
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
	}
	
	
	//updateBook
	public void updateBook(Book updatedBook) {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			String query = "UPDATE books SET title = :title, author = :author, price = :price WHERE id = :id";
			
			namedParameters.addValue("title", updatedBook.getTitle());
			namedParameters.addValue("author", updatedBook.getAuthor());
			namedParameters.addValue("price", updatedBook.getPrice());
			namedParameters.addValue("id", updatedBook.getId());
			
		    int rowsAffected = jdbc.update(query, namedParameters);
		    
		    if (rowsAffected > 0) {
		        System.out.println("Updated Book with ID " + updatedBook.getId() + " in the database.");
		    }
	
	}
	
	//filter Category 
	public List<Book> filterCategory(Book book){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("Category", book.getCategory());
		
		String query = "SELECT FROM bookTable WHERE category = :Category";
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
	}
	


}
