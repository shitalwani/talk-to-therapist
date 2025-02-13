package com.therapist.controllers;

import com.therapist.dto.TherapistRequestDTO;
import com.therapist.dto.TherapistResponseDTO;
import com.therapist.models.TherapistEntity;
import com.therapist.services.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TherapistController {

    @Autowired
    TherapistService therapistService;

    @PostMapping("/api/therapist")
    public ResponseEntity<TherapistResponseDTO> createTherapist(@RequestBody TherapistRequestDTO therapistRequestDTO){
        TherapistResponseDTO storeTherapistData = therapistService.storeTherapistData(therapistRequestDTO);
        return new ResponseEntity<>(storeTherapistData, HttpStatus.CREATED);
    }

    @GetMapping("/api/therapists")
    public ResponseEntity<List<TherapistResponseDTO>> getAllTherapistList(){
        List<TherapistResponseDTO> getList = therapistService.fetchAllTherapist();
        return new ResponseEntity<>(getList,HttpStatus.OK);
    }


  @GetMapping("/api/therapist/{therapistId}")
  public ResponseEntity<TherapistResponseDTO> getTherapistById(@PathVariable("therapistId") String therapistId){
        TherapistResponseDTO getDataById = therapistService.fetchTherapistById(therapistId);
        return new ResponseEntity<>(getDataById,HttpStatus.OK);
  }

  @PutMapping("/api/therapist/{therapistId}")
  public ResponseEntity<TherapistResponseDTO> updateTherapistById(@RequestBody TherapistRequestDTO therapistRequestDTO,@PathVariable("therapistId") String therapistId){
      TherapistResponseDTO getDataById = therapistService.updateTherapistDataById(therapistRequestDTO,therapistId);
      return new ResponseEntity<>(getDataById,HttpStatus.OK);
  }

  @DeleteMapping("/api/therapist/{therapistId}")
  public ResponseEntity<String> deleteDataById(@PathVariable("therapistId")String therapistId){
       String isDelete = therapistService.deleteTherapistDataById(therapistId);
       return new ResponseEntity<>(isDelete,HttpStatus.OK);
  }

}
