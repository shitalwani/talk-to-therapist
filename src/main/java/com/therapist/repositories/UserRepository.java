package com.therapist.repositories;

import com.therapist.dto.UserRequestDTO;
import com.therapist.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    //UserEntity findByUsernameAndPassword(UserRequestDTO userRequestDTO);

    UserEntity findByUsernameAndPassword(String username, String password);
}
