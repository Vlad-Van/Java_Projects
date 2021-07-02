package com.example.crud.repo;

import com.example.crud.client.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer>{
    Client findClientById(Integer id);
}
