package com.example.crud.converter;

import com.example.crud.client.Client;
import com.example.crud.dto.ClientDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {

    public ClientDto entityToDto(Client client){
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setEmail(client.getEmail());
        return dto;
    }

    public List<ClientDto> entityToDto(List<Client> client){
        return client.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public Client dtoToEntity(ClientDto dto){
        Client clt = new Client();
        clt.setId(dto.getId());
        clt.setFirstName(dto.getFirstName());
        clt.setLastName(dto.getLastName());
        clt.setEmail(dto.getEmail());
        return clt;
    }

    public List<Client> dtoToEntity(List<ClientDto> dto){
        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

}
