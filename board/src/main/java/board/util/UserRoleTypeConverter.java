package board.util;

import board.entity.constant.UserRoleType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserRoleTypeConverter implements AttributeConverter<UserRoleType, String>{
	
	// java -> db 저장될때 변경
	@Override
	public String convertToDatabaseColumn(UserRoleType attribute) {
		return attribute.getRoleType();
	}
	
	// db 문자열("ROLE_USER") -> java enum (USER) 객체  매핑 변환
	@Override
	public UserRoleType convertToEntityAttribute(String dbData) {
//		return UserRoleType.valueOf(dbData);
		return UserRoleType.getInstance(dbData);
	}

}

