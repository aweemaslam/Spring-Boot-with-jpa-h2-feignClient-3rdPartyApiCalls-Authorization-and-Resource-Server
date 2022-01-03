package com.app.kitalulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.kitalulus.model.CurrencyAuditLog;
import com.app.kitalulus.model.User;

@Repository
public interface RepositoryCurrencyAuditLog extends JpaRepository<CurrencyAuditLog, Long> {

}
