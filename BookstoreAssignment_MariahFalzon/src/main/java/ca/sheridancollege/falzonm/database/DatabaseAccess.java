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

	// getBookList code to display the list
	public List<Book> getBookList() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM books";

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
	}

	// getBookListById for admin
	public List<Book> getBookListById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM books WHERE id = :id";

		namedParameters.addValue("id", id);

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
	}

	// insertBook code for admin
	public void insertBook(Book book) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		// Adding the parameters
		namedParameters.addValue("isbn", book.getIsbn());
		namedParameters.addValue("title", book.getTitle());
		namedParameters.addValue("author", book.getAuthor());
		namedParameters.addValue("price", book.getPrice());
		namedParameters.addValue("description", book.getDescription());

		// Write Query for the Book

		String query = "INSERT INTO books (isbn, title, author, price, description) VALUES(:isbn, :title, :author, :price, :description)";

		jdbc.update(query, namedParameters);

//		int rowsAffected = jdbc.update(query, namedParameters);
//		
//		if (rowsAffected > 0) System.out.println("Book inserted into database");
	}

	// updateBook
	public void updateBook(Book book) {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			String query = "UPDATE books SET title = :title, author = :author, price = :price, description = :description WHERE id = :id";
			
			namedParameters.addValue("title", book.getTitle());
			namedParameters.addValue("author", book.getAuthor());
			namedParameters.addValue("price", book.getPrice());
			namedParameters.addValue("id", book.getId());
			namedParameters.addValue("description", book.getDescription());
			
		    int rowsAffected = jdbc.update(query, namedParameters);
		    
		    if (rowsAffected > 0) {
		        System.out.println("Updated Book with ID " + book.getId() + " in the database.");
		    }
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
	
	//NEED ADD TO CART CODE
	
	
	//NEED UPDATE CART CODE
	
	
	//NEED REMOVE CART ITEM CODE
	
	


}
