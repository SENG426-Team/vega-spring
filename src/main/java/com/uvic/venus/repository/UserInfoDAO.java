package com.uvic.venus.repository;

import com.uvic.venus.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface UserInfoDAO extends JpaRepository<UserInfo, String> {
    @Query("SELECT u FROM UserInfo u WHERE u.username = (?1)")
    List<UserInfo> findUserInfoByUsername(String username);
}
