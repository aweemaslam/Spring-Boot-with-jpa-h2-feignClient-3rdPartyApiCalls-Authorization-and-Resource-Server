package com.app.Kitalulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Kitalulus.model.Role;


@Repository
public interface RepositoryRoles extends JpaRepository<Role, Long> {

}
