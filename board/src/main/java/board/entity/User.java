package board.entity;

import board.entity.constant.UserRoleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
public class User extends AuditingFields {
	
	@Id
	@Column(name = "uid", length = 20)
    private String uid; // userId
    
	@Column(length = 20)
	private String username;
	
	@Column(length = 20)
    private String password;

    private String email;
    
    @Enumerated(EnumType.STRING)
	@Column(name = "role_type", columnDefinition = "VARCHAR(50)")
    private UserRoleType userRoleType;
    
    protected User() {}
    
	private User(String uid, String username, String password, String email, UserRoleType userRoleType) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userRoleType = userRoleType;
	}
	
	public static User of(String userId, String username, String password, String email, UserRoleType userRoleType) {
		return new User(userId, username, password, email, userRoleType);
	}
	
}
