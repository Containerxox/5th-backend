package jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity  // 매니저가 Student 객체를 관리하게 됨
public class Student {
	@Id //primary key 선언하는것
	@Column(name="s_id") // 컬럼이름 변경(sid -> s_id)
	private Integer sid;
	
	@Column(columnDefinition = "VARCHAR(30) DEFAULT 'SNAME'") //컬럼의 옵션값을 직접 설정하고 싶다면 columnDefinition 사용
	// @Column(name="sname", nullable = false, length=10) // 옵션 추가(not null) & 10글자만 들어갈수있게 설정
	private String sname;
}
