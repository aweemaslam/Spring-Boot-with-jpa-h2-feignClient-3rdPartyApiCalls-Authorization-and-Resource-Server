package com.app.kitalulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.kitalulus.model.Role;


@Repository
public interface RepositoryRoles extends JpaRepository<Role, Long> {

}
