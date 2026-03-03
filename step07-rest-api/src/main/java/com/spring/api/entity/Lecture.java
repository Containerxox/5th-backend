package com.spring.api.entity;

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
@Entity
public class Lecture {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "lid")
		private Long lid;
		
		@Column(name = "lname")
		private String lname;
		
		@OneToMany(mappedBy = "lecture")
		private List<Student> students = new ArrayList<>();
		
}


