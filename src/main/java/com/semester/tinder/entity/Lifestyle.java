package com.semester.tinder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "lifestyle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lifestyle {

    @Id
    private int id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "u_id")
    private User user;

    private String pet;
    private String drinking;
    private String smoking;
    private String workout;
    private String dietary_preference;
    private String social_media;
    private String sleeping_habits;
}
