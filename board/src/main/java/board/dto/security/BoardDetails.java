package board.dto.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import board.dto.UserDto;
import board.entity.constant.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class BoardDetails implements UserDetails{
    private String uid;
	private String username;
    private String password;
    private String email;
    private UserRoleType userRoleType;
	
    public static BoardDetails of(String uid, String username, String password, String email, UserRoleType userRoleType) {
    	return new BoardDetails(
    				uid,
    				username,
    				password,
    				email,
    				userRoleType
    			);
    }
     
    public static BoardDetails from(UserDto userDto) {
    	return BoardDetails.of(
    				userDto.getUid(),
    				userDto.getUsername(),
    				userDto.getPassword(),
    				userDto.getEmail(),
    				userDto.getUserRoleType()
    			);
    }
    
    public UserDto toDto() {
    	return UserDto.of(uid, 
    					  username, 
    					  password, 
    					  email, 
    					  userRoleType);
    }
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
    	Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
    	collection.add(() -> userRoleType.getRoleType());
    	return collection;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

}

