package com.semester.tinder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.Date;

@Entity(name = "report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    private int id;

    private String content;

    private Date report_time;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "u_report")
    private User user_report;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "u_accused")
    private User user_accused;

}
