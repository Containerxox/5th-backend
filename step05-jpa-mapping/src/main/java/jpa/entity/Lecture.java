package jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
//@Entity
public class Lecture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrement 옵션
	@Column(name = "lid")
	private Long lid;
	
	@Column(name = "lname")
	private String lname;
	
	@OneToMany(mappedBy = "lecture") // 여기서 "lecture"는 Student 클래스 안의 필드 이름을 의미함.
	private List<Student> students = new ArrayList<>();
	
}
