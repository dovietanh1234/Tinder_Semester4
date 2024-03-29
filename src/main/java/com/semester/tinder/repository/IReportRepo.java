package com.semester.tinder.repository;

import com.semester.tinder.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReportRepo extends JpaRepository<Report, Integer> {
    Optional<Report> findById(int id);
}