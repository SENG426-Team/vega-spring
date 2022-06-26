package com.uvic.venus.repository;

import com.uvic.venus.model.Keys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretKeyDAO extends JpaRepository<Keys, String> {
}
