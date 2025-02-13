package com.therapist.repositories;

import com.therapist.models.TherapistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TherapistRepository extends JpaRepository<TherapistEntity,String> {


    Optional<TherapistEntity> findByTherapistIdAndDeletedFalse(String therapistId);

    List<TherapistEntity> findByDeletedFalse();
}
