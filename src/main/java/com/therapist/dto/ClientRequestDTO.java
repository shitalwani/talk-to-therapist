package com.therapist.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ClientRequestDTO {
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
