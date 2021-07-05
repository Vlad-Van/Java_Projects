package com.example.crud.controller;

import com.example.crud.converter.Converter;
import com.example.crud.client.Client;
import com.example.crud.dto.ClientDto;
import com.example.crud.repo.ClientRepository;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Function;

@RestController
public class Controller {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    Converter converter;

    @Autowired
    EntityManager em;

    @PostMapping("/save")
    public ClientDto addClient(@RequestBody ClientDto dto) {
        Client clt = converter.dtoToEntity(dto);
        clt = clientRepository.save(clt);
        return converter.entityToDto(clt);
    }

    @PostMapping("/saveall")
    public List<ClientDto> addListOfClients(@RequestBody List<ClientDto> dto){
        Iterable<Client> inpt = converter.dtoToEntity(dto);
        inpt = clientRepository.saveAll(inpt);
        return converter.entityToDto((List<Client>) inpt);
    }

    @GetMapping("/alldata")
    public List<ClientDto> getClientData(){
        Iterable<Client> findall = clientRepository.findAll();
        return converter.entityToDto((List<Client>) findall);
    }

    @GetMapping("/data/{pageNumber}")
    public Page<ClientDto> getClientPageOne(@PathVariable Integer pageNumber){
        Pageable pageable = PageRequest.of((pageNumber-1),20);
        Page<Client> clt = clientRepository.findAll(pageable);
        return clt.map(new Function<Client, ClientDto>() {
            @Override
            public ClientDto apply(Client client) {
                return new ModelMapper().map(client, ClientDto.class);
            }
        });
    }

    @GetMapping("/dataName")
    public List<ClientDto> getClientName(){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery <Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = criteriaQuery.from(Client.class);
        criteriaQuery.select(clientRoot).where(criteriaBuilder.like(clientRoot.get("firstName"), "%A%"));
        CriteriaQuery<Client> select = criteriaQuery.select(clientRoot);
        TypedQuery<Client> query = em.createQuery(select);
        return converter.entityToDto(query.getResultList());
    }

    @GetMapping("find/{id}")
    public ClientDto findClientById(@PathVariable Integer id){
        Client clt = clientRepository.findClientById(id);
        return converter.entityToDto(clt);
    }
}
