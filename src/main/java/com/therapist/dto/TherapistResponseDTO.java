package com.therapist.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TherapistResponseDTO {

    private String therapistId;
    private Integer userId;
    private String firstname;
    private String lastname;
    private Integer yearOfExperience;
    private String mobile;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
