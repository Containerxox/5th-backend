package jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity
public class Student {
	@Id
	@Column(name = "sid")
	private Integer sid;
	
	@Column(name = "sname")
	private String sname;
	
	
	// 패러다임 불일치 해결. (객체로 맵핑)
	@ManyToOne
	@JoinColumn(name = "lid")
	private Lecture lecture;
	
//	@Column(name = "lid")
//	private Long lid;
	
}