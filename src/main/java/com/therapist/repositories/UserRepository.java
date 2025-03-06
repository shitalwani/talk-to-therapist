package com.therapist.repositories;

import com.therapist.dto.UserRequestDTO;
import com.therapist.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    //UserEntity findByUsernameAndPassword(UserRequestDTO userRequestDTO);

    UserEntity findByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByUsername(String username);
}
