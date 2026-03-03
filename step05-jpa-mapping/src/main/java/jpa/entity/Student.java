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
import lombok.ToString.Exclude;  //방법1) ToString을 제외하도록하여 순환참조하는것을 막기.

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"lecture"}) //방법2) toString을 제외할 필드명을 적어주면 된다.
//@Entity
public class Student {
	@Id
	@Column(name = "sid")
	private Integer sid;
	
	@Column(name = "sname")
	private String sname;
	
	
	// 패러다임 불일치 해결. (객체로 맵핑)
	@ManyToOne
	@JoinColumn(name = "lid")
//	@Exclude  //방법1
	private Lecture lecture;
	
//	@Column(name = "lid")
//	private Long lid;
	
}