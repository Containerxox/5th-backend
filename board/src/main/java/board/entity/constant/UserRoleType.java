package board.entity.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum UserRoleType {
	ROLE_USER("ROLE_USER"),
	ROLE_ADMIN("ROLE_ADMIN");
	
	private String roleType;
	
	UserRoleType(String roleType){
		this.roleType = roleType;
	}
	
}
