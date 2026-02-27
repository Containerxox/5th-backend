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
@Entity
public class Student {
	@Id
	@Column(name = "sid")
	private Integer sid;
	
	@Column(name = "sname")
	private String sname;
	
	@Column(name = "lid")
	private Long lid;
	
}