package com.madrat.spaceshooter.apiserver.database;

import com.madrat.spaceshooter.apiserver.resourcereprs.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAllByOrderByScoreDesc();
    public List<User> findAllByOrderByScoreDesc(Pageable pageable);
    public boolean existsByclientuuid(String clientuuid);
    public boolean existsByserveruuid(String serveruuid);

    @Modifying
    @Transactional
    @Query("update User u set u.score = ?1 where u.serveruuid = ?2 and u.clientuuid = ?3")
    void setScoreByClientUUIDbyServerUUID(int score, String serveruuid, String clientuuid);
}