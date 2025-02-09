package com.therapist.controllers;

import com.therapist.dto.ClientGetResponseDTO;
import com.therapist.dto.ClientRequestDTO;
import com.therapist.dto.ClientResponseDTO;
import com.therapist.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/client/add")
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO clientRequestDTO){
       ClientResponseDTO add = clientService.storeClientInformation(clientRequestDTO);
        return new ResponseEntity<>(add, HttpStatus.CREATED);

    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientGetResponseDTO>> fetchClients(){
        List<ClientGetResponseDTO> getList = clientService.fetchAllClients();
        return new ResponseEntity<>(getList,HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientGetResponseDTO> fetchDataById(@PathVariable(name="id") String id){
        ClientGetResponseDTO getById = clientService.fetchClientById(id);
        return new ResponseEntity<>(getById,HttpStatus.OK);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<ClientResponseDTO> updateClientData(@RequestBody ClientRequestDTO clientRequestDTO,@PathVariable("id") String id){
      ClientResponseDTO updatedData = clientService.updateClientDataById(clientRequestDTO,id);
      return new ResponseEntity<>(updatedData,HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> softDeleteUser(@PathVariable("id")String id){
        String deletedData = clientService.deleteClientDataById(id);
        return new ResponseEntity<>(deletedData,HttpStatus.OK);

    }
}
