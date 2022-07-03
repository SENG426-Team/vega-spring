package com.uvic.venus.repository;

import com.uvic.venus.model.Secrets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SecretElementDAO extends JpaRepository<Secrets, String> {

@Query("SELECT s FROM Secrets s WHERE s.username = (?1)")
List<Secrets> findSecretsByUsername(String username);

@Query("SELECT s.secret_content FROM Secrets s WHERE s.secret_id = (?1)")
List<String> findSecretsBySecretID(int id);

@Transactional
@Modifying
@Query("DELETE FROM Secrets s WHERE s.secret_id = (?1)")
void deleteSecretsBySecretId(int secret_id);
}
