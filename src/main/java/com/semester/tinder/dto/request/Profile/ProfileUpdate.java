package com.semester.tinder.dto.request.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdate {

    private int id;

    private String bio;

    private String relationship_goals;

    private String interests;

    private String height;

    private String languages;
}