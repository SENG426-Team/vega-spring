package com.uvic.venus.repository;

import com.uvic.venus.model.Secrets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;
import com.uvic.venus.model.Secrets;
import java.util.List;

@Repository
public interface SecretElementDAO extends JpaRepository<Secrets, String> {

@Query("SELECT s FROM Secrets s WHERE s.username = (?1)")
List<Secrets> findSecretsByUsername(String username);

}
