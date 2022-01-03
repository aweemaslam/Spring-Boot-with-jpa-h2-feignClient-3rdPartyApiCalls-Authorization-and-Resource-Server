package com.app.Kitalulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Kitalulus.model.User;

@Repository
public interface RepositoryUser extends JpaRepository<User, Long> {

	User findByPrincipalName(String username);

}
