package com.semester.tinder.repository;

import com.semester.tinder.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProfileRepo extends JpaRepository<Profile, Integer> {
    Optional<Profile> findById(int id);
}
