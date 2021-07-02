package com.example.crud.controller;

import com.example.crud.converter.Converter;
import com.example.crud.client.Client;
import com.example.crud.dto.ClientDto;
import com.example.crud.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    Converter converter;

    @PostMapping("/save")
    public ClientDto addClient(@RequestBody ClientDto dto) {
        Client clt = converter.dtoToEntity(dto);
        clt = clientRepository.save(clt);
        return converter.entityToDto(clt);
    }

    @GetMapping("/data")
    public List<ClientDto> getClient(){
        Iterable<Client> findall = clientRepository.findAll();
        return converter.entityToDto((List<Client>)findall);
    }

    @GetMapping("find/{id}")
    public ClientDto findClientById(@PathVariable Integer id){
        Client clt = clientRepository.findClientById(id);
        return converter.entityToDto(clt);
    }
}
