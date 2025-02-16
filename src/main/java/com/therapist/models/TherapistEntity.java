package com.therapist.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "therapist")
public class TherapistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "therapist_id")
    private String therapistId;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "user_id") // This will create a foreign key column in the TherapistEntity table
    private UserEntity userEntity;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "year_of_experience")
    private Integer yearOfExperience;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(nullable = true)
    private Boolean deleted = false;  // Flag to indicate if the record is deleted

    @Column(nullable = true)
    private LocalDateTime deletedAt;

}
