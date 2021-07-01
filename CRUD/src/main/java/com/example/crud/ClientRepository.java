package com.example.crud;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer>{
    Client findClientById(Integer id);
}
