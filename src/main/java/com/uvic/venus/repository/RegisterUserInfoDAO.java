package com.uvic.venus.repository;

import com.uvic.venus.model.RegisterUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterUserInfoDAO extends JpaRepository<RegisterUserInfo, String> {
}
