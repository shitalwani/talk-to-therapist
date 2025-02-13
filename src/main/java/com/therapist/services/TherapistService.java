package com.therapist.services;

import com.therapist.dto.TherapistRequestDTO;
import com.therapist.dto.TherapistResponseDTO;

import java.util.List;

public interface TherapistService {
    TherapistResponseDTO storeTherapistData(TherapistRequestDTO therapistRequestDTO);

    List<TherapistResponseDTO> fetchAllTherapist();

    TherapistResponseDTO fetchTherapistById(String therapistId);

    TherapistResponseDTO updateTherapistDataById(TherapistRequestDTO therapistRequestDTO, String therapistId);

    String deleteTherapistDataById(String therapistId);
}
