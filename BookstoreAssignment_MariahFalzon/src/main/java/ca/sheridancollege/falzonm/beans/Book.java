package ca.sheridancollege.falzonm.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	private Long id;
	private Long isbn;
	private String title;
	private String author;
	private double price;
	private String description;
	private int quantity;
	public void add(Book book) {
		// TODO Auto-generated method stub
		
	} 


}
