package com.semester.tinder.repository;

import com.semester.tinder.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepo extends JpaRepository<Images, Integer> {
}
