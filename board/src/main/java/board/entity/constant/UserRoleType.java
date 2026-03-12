package board.entity.constant;

import java.util.Arrays;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum UserRoleType {
	ROLE_USER("ROLE_USER"),
	ROLE_MANAGER("ROLE_MANAGER"),
	ROLE_ADMIN("ROLE_ADMIN");
	
	private String roleType;
	
	UserRoleType(String roleType){
		this.roleType = roleType;
	}

	// db 문자열값 -> java enum 상수명	
//	public static UserRoleType getInstance(String dbData) {
//		return Arrays.stream(UserRoleType.values())
//						.filter(role -> role.getRoleType().equals(dbData))
//						.findFirst()
//						.orElseThrow();
//	}
	
	
}
