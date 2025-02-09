package com.therapist.implementation;

import com.therapist.Exception.HandleBadRequestError;
import com.therapist.dto.ClientGetResponseDTO;
import com.therapist.dto.ClientRequestDTO;
import com.therapist.dto.ClientResponseDTO;
import com.therapist.models.ClientsEntity;
import com.therapist.repositories.ClientRepository;
import com.therapist.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ClientResponseDTO storeClientInformation(ClientRequestDTO clientRequestDTO) {

        ClientsEntity clientsEntity = new ClientsEntity();

        clientsEntity.setName(clientRequestDTO.getName());

        clientsEntity.setMobile(clientRequestDTO.getMobile());
        clientsEntity.setEmail(clientRequestDTO.getEmail());
        clientsEntity.setAddress(clientRequestDTO.getAddress());
        clientsEntity.setPassword(clientRequestDTO.getPassword());

        LocalDateTime now = LocalDateTime.now();
        clientsEntity.setCreatedAt(now);
        clientsEntity.setModifiedAt(now);

        clientRepository.save(clientsEntity);

        ModelMapper modelMapper = new ModelMapper();

        ClientResponseDTO clientResponseDTO = modelMapper.map(clientsEntity,ClientResponseDTO.class);

        return clientResponseDTO;
    }

    @Override
    public List<ClientGetResponseDTO> fetchAllClients() {
        List<ClientsEntity> clientsEntities = clientRepository.findAll();

        List<ClientGetResponseDTO> clientResponseDTOList = new ArrayList<>();

        clientsEntities.forEach(clientsEntity -> {

          ClientGetResponseDTO clientGetResponseDTO = new ClientGetResponseDTO();

          clientGetResponseDTO.setId(clientsEntity.getId());
          clientGetResponseDTO.setName(clientsEntity.getName());
          clientGetResponseDTO.setMobile(clientsEntity.getMobile());
          clientGetResponseDTO.setEmail(clientsEntity.getEmail());
          clientGetResponseDTO.setAddress(clientsEntity.getAddress());
          clientGetResponseDTO.setPassword(clientsEntity.getPassword());
          clientGetResponseDTO.setCreatedAt(clientsEntity.getCreatedAt());
          clientGetResponseDTO.setModifiedAt(clientsEntity.getModifiedAt());
          clientResponseDTOList.add(clientGetResponseDTO);
        });
        return clientResponseDTOList;
    }

    @Override
    public ClientGetResponseDTO fetchClientById(String id) {
       Optional<ClientsEntity> clientsEntity = clientRepository.findById(id);
       if(clientsEntity.isEmpty()){
           throw new RuntimeException("record not found for id: " +id);
       }

        ClientGetResponseDTO clientGetResponseDTO = new ClientGetResponseDTO();
       clientGetResponseDTO.setId(clientsEntity.get().getId());
       clientGetResponseDTO.setName(clientsEntity.get().getName());
       clientGetResponseDTO.setEmail(clientsEntity.get().getEmail());
       clientGetResponseDTO.setMobile(clientsEntity.get().getMobile());
       clientGetResponseDTO.setAddress(clientsEntity.get().getAddress());
       clientGetResponseDTO.setPassword(clientsEntity.get().getPassword());
       clientGetResponseDTO.setCreatedAt(clientsEntity.get().getCreatedAt());
       clientGetResponseDTO.setModifiedAt(clientsEntity.get().getModifiedAt());
        return clientGetResponseDTO;
    }

    @Override
    public ClientResponseDTO updateClientDataById(ClientRequestDTO clientRequestDTO, String id) {
        Optional<ClientsEntity> clientsEntity = clientRepository.findById(id);
        if(clientsEntity.isEmpty()){
            throw new RuntimeException("record not found for id : " +id);
        }

        clientsEntity.get().setName(clientRequestDTO.getName());
        clientsEntity.get().setAddress(clientRequestDTO.getAddress());
        clientsEntity.get().setEmail(clientRequestDTO.getEmail());
        clientsEntity.get().setMobile(clientRequestDTO.getMobile());
        clientsEntity.get().setPassword(clientRequestDTO.getPassword());

        clientRepository.save(clientsEntity.get());
        return modelMapper.map(clientsEntity.get(),ClientResponseDTO.class);
    }

    @Override
    public String deleteClientDataById(String id) {
        Optional<ClientsEntity> clientsEntity = clientRepository.findByIdAndDeletedFalse(id);
        if (clientsEntity.isPresent()) {
            clientsEntity.get().setDeleted(true);
            clientsEntity.get().setDeletedAt(LocalDateTime.now());
            clientRepository.save(clientsEntity.get());
            return "Record deleted successfully";
        }else{
            throw new HandleBadRequestError("Record already deleted");
        }
    }
}
