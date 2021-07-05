package com.example.crud.repo;

import com.example.crud.client.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client,Integer>{
    Client findClientById(Integer id);
}
