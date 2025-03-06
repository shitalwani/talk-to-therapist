package com.therapist.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientResponseDTO {
    private String id;
    private String name;
    private String mobile;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
