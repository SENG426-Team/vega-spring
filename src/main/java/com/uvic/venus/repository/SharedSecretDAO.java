package com.uvic.venus.repository;

import com.uvic.venus.model.SharedSecrets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedSecretDAO extends JpaRepository<SharedSecrets, String> {
}
