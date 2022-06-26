package com.uvic.venus.repository;

import com.uvic.venus.model.Secrets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretElementDAO extends JpaRepository<Secrets, String> {
}
