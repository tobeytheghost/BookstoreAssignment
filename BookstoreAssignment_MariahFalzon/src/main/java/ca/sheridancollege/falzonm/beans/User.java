package ca.sheridancollege.falzonm.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
	private Long userId;
	
	@NonNull
	private String userName;
	
	@NonNull 
	private String encryptedPassword;
	
	@NonNull
	private String email;
	
	@NonNull
	private Boolean enabled;

}