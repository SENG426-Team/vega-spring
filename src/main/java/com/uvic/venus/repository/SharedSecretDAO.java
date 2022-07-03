package com.uvic.venus.repository;

import com.uvic.venus.model.SharedSecrets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SharedSecretDAO extends JpaRepository<SharedSecrets, String> {

    @Query("SELECT s FROM SharedSecrets s WHERE s.recipient = (?1)")
    List<SharedSecrets> findSharedSecretsByRecipient(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM SharedSecrets s WHERE s.secret_id = (?1)")
    void deleteSharedSecretsBySecretID(int secret_id);
        
}
