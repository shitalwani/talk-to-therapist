package com.therapist.repositories;

import com.therapist.models.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientsEntity,String> {

    Optional<ClientsEntity> findByIdAndDeletedFalse(String id);

    List<ClientsEntity> findByDeletedFalse();
}
