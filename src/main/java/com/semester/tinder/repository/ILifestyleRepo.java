package com.semester.tinder.repository;

import com.semester.tinder.entity.Lifestyle;
import com.semester.tinder.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ILifestyleRepo extends JpaRepository<Lifestyle, Integer> {
    Optional<Lifestyle> findById(int id);
}