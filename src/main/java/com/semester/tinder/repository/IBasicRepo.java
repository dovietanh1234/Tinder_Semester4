package com.semester.tinder.repository;

import com.semester.tinder.entity.Basic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBasicRepo extends JpaRepository<Basic, Integer> {
    Optional<Basic> findById(int id);
}
