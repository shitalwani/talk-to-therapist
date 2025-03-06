package com.therapist.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ClientGetResponseDTO {
    private String id;
    private String name;
    private String mobile;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
