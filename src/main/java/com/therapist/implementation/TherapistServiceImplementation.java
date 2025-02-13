package com.therapist.implementation;

import com.therapist.Exception.HandleBadRequestError;
import com.therapist.dto.TherapistRequestDTO;
import com.therapist.dto.TherapistResponseDTO;
import com.therapist.models.TherapistEntity;
import com.therapist.repositories.TherapistRepository;
import com.therapist.services.TherapistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TherapistServiceImplementation implements TherapistService {

    @Autowired
    TherapistRepository therapistRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public TherapistResponseDTO storeTherapistData(TherapistRequestDTO therapistRequestDTO) {

        TherapistEntity therapistEntity = new TherapistEntity();
        therapistEntity.setFirstname(therapistRequestDTO.getFirstname());
        therapistEntity.setLastname(therapistRequestDTO.getLastname());
        therapistEntity.setMobile(therapistRequestDTO.getMobile());
        therapistEntity.setYearOfExperience(therapistRequestDTO.getYearOfExperience());
        therapistEntity.setAddress(therapistRequestDTO.getAddress());
        therapistEntity.setUserId(therapistRequestDTO.getUserId());
        therapistRepository.save(therapistEntity);

        return modelMapper.map(therapistEntity,TherapistResponseDTO.class);
    }

    @Override
    public List<TherapistResponseDTO> fetchAllTherapist() {
         List<TherapistEntity> therapistEntity = therapistRepository.findByDeletedFalse();

         List<TherapistResponseDTO> therapistResponseDTOList = new ArrayList<>();
        therapistEntity.forEach(therapistEntity1 -> {
            TherapistResponseDTO therapistResponseDTO = new TherapistResponseDTO();
            therapistResponseDTO.setTherapistId(therapistEntity1.getTherapistId());
            therapistResponseDTO.setFirstname(therapistEntity1.getFirstname());
            therapistResponseDTO.setLastname(therapistEntity1.getLastname());
            therapistResponseDTO.setMobile(therapistEntity1.getMobile());
            therapistResponseDTO.setUserId(therapistEntity1.getUserId());
            therapistResponseDTO.setAddress(therapistEntity1.getAddress());
            therapistResponseDTO.setYearOfExperience(therapistEntity1.getYearOfExperience());
            therapistResponseDTO.setCreatedAt(therapistEntity1.getCreatedAt());
            therapistResponseDTO.setUpdatedAt(therapistEntity1.getUpdatedAt());
            therapistResponseDTOList.add(therapistResponseDTO);

        });
        return therapistResponseDTOList;
    }

    @Override
    public TherapistResponseDTO fetchTherapistById(String therapistId) {
       Optional<TherapistEntity> therapistEntity = therapistRepository.findByTherapistIdAndDeletedFalse(therapistId);
       if(therapistEntity.isEmpty()){
           throw new HandleBadRequestError("Record not found gor given Id: "+therapistId);
       }

       TherapistResponseDTO therapistResponseDTO = new TherapistResponseDTO();
       therapistResponseDTO.setTherapistId(therapistEntity.get().getTherapistId());
       therapistResponseDTO.setFirstname(therapistEntity.get().getFirstname());
       therapistResponseDTO.setLastname(therapistEntity.get().getLastname());
       therapistResponseDTO.setAddress(therapistEntity.get().getAddress());
       therapistResponseDTO.setMobile(therapistEntity.get().getMobile());
       therapistResponseDTO.setUserId(therapistEntity.get().getUserId());
       therapistResponseDTO.setYearOfExperience(therapistEntity.get().getYearOfExperience());
       therapistResponseDTO.setCreatedAt(therapistEntity.get().getCreatedAt());
       therapistResponseDTO.setUpdatedAt(therapistEntity.get().getUpdatedAt());

        return therapistResponseDTO;
    }

    @Override
    public TherapistResponseDTO updateTherapistDataById(TherapistRequestDTO therapistRequestDTO, String therapistId) {

        Optional<TherapistEntity> therapistEntity = therapistRepository.findByTherapistIdAndDeletedFalse(therapistId);
        if(therapistEntity.isEmpty()){
            throw new HandleBadRequestError("Record not found gor given Id: "+therapistId);
        }

        therapistEntity.get().setFirstname(therapistRequestDTO.getFirstname());
        therapistEntity.get().setLastname(therapistRequestDTO.getLastname());
        therapistEntity.get().setAddress(therapistRequestDTO.getAddress());
        therapistEntity.get().setMobile(therapistRequestDTO.getMobile());
        therapistEntity.get().setUserId(therapistRequestDTO.getUserId());
        therapistEntity.get().setYearOfExperience(therapistRequestDTO.getYearOfExperience());
        therapistRepository.save(therapistEntity.get());

        return modelMapper.map(therapistEntity.get(),TherapistResponseDTO.class);
    }

    @Override
    public String deleteTherapistDataById(String therapistId) {
       Optional<TherapistEntity> therapistEntity = therapistRepository.findByTherapistIdAndDeletedFalse(therapistId);
        if (therapistEntity.isPresent()) {
            therapistEntity.get().setDeleted(true);
            therapistEntity.get().setDeletedAt(LocalDateTime.now());
            therapistRepository.save(therapistEntity.get());
            return "Record deleted successfully!!!";
        } else {
            throw new HandleBadRequestError("Record already deleted!!!");
        }

    }
}
