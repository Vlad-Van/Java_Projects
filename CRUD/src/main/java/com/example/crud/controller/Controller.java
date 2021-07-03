package com.example.crud.controller;

import com.example.crud.converter.Converter;
import com.example.crud.client.Client;
import com.example.crud.dto.ClientDto;
import com.example.crud.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @PostMapping("/saveall")
    public List<ClientDto> addListOfClients(@RequestBody List<ClientDto> dto){
        Iterable<Client> inpt = converter.dtoToEntity(dto);
        inpt = clientRepository.saveAll(inpt);
        return converter.entityToDto((List<Client>) inpt);
    }

    @GetMapping("/data")
    public List<ClientDto> getClient(){
        Iterable<Client> findall = clientRepository.findAll();
        return converter.entityToDto((List<Client>)findall);
    }

    @GetMapping("/dataName")
    public List<ClientDto> getClientName(){
//        Iterable<Client> findall = clientRepository.findAll();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery <Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = criteriaQuery.from(Client.class);
        criteriaQuery.select(clientRoot.get("id").get("firstName").get("lastName").get("email"));
        CriteriaQuery<Client> select = criteriaQuery.select(clientRoot);
        TypedQuery<Client> query = em.createQuery(select);

        return converter.entityToDto((List<Client>) query);
    }

    @GetMapping("find/{id}")
    public ClientDto findClientById(@PathVariable Integer id){
        Client clt = clientRepository.findClientById(id);
        return converter.entityToDto(clt);
    }
}
