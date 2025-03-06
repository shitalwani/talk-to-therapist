package com.therapist.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "clients")
public class ClientsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Use UUID generation strategy
    @Column(name = "id")
    private String id; // Store UUID as a String

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // This will create a foreign key column in the TherapistEntity table
    private UserEntity userEntity;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(nullable = true)
    private Boolean deleted = false;  // Flag to indicate if the record is deleted

    @Column(nullable = true)
    private LocalDateTime deletedAt;  // Timestamp for soft deletion

}
