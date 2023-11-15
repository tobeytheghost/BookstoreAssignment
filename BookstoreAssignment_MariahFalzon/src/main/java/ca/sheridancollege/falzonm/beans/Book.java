package ca.sheridancollege.falzonm.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	private Long id;
	private String title;
	private String author;
	private Long isbn;
	private Double price;
	private String description;
	private String category;
	
	private final String[] CATEGORIES = {"Mystery", "Classic", "Non-Fiction", "Sci-Fi", "Childrens", "Thriller", "Dystioian"};
}
