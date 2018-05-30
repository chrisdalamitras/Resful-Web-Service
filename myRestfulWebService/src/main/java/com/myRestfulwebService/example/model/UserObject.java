package com.myRestfulwebService.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UserObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 2, message = "Name must be at least two characters")
	private String name;
	@Past(message = "You cant give future date as birthdate")
	private Date birthDate;

	@OneToMany(mappedBy = "user")
	private List<UserPosts> posts;

	public UserObject(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public UserObject(String name, Date birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

}
