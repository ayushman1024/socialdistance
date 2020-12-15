package com.astrotalk.socialdistance.resources;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

public class UserResource {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    @NotBlank
	    @Size(min=3, max = 50)
	    private String name;

	    @NotBlank
	    @Size(min=3, max = 50)
	    private String username;

	    @NaturalId
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
}
