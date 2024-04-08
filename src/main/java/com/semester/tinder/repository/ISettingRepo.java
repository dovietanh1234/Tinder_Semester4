package com.semester.tinder.repository;

import com.semester.tinder.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISettingRepo extends JpaRepository<Setting, Integer> {
    Optional<Setting> findById(int id);
}
