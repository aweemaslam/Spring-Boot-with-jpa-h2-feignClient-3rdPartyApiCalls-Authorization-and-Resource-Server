package com.app.kitalulus.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.app.kitalulus.constants.AccountType;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotEmpty
	@Column(name = "full_name", length = 255, nullable = false)
	private String fullName;

	@Column(length = 100)
	@NotNull
	@Email
	private String email;

	@Length(max = 45)
	@Column(name = "mobile_number", length = 45)
	private String mobileNumber;

	@Column(length = 70)
	@Length(max = 70)
	private String password;

	@Length(max = 45)
	@Column(name = "principal_name", length = 45)
	private String principalName;

	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

}