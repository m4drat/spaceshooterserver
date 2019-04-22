package com.madrat.spaceshooter.apiserver.database;

import com.madrat.spaceshooter.apiserver.resourcereprs.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAllByOrderByScoreDesc();
    public List<User> findAllByOrderByScoreDesc(Pageable pageable);
}