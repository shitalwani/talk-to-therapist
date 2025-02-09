package com.therapist.services;

import com.therapist.dto.ClientGetResponseDTO;
import com.therapist.dto.ClientRequestDTO;
import com.therapist.dto.ClientResponseDTO;
import com.therapist.models.ClientsEntity;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ClientResponseDTO storeClientInformation(ClientRequestDTO clientRequestDTO);

    List<ClientGetResponseDTO> fetchAllClients();

    ClientGetResponseDTO fetchClientById(String id);

    ClientResponseDTO updateClientDataById(ClientRequestDTO clientRequestDTO, String id);

    String deleteClientDataById(String id);
}
