package com.semester.tinder.dto.request.Profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.semester.tinder.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private int u_id;

    private String bio;

    private String relationship_goals;

    private String interests;

    private String height;

    private String languages;
}
