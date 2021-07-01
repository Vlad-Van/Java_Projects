package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/add")
    public String addClient(@RequestParam String first, @RequestParam String last, @RequestParam String mail) throws IOException {
        Client client = new Client();
        client.setFirstName(first);
        client.setLastName(last);
        client.setEmail(mail);
        clientRepository.save(client);
        return "New client added!";
    }

    @GetMapping("/data")
    public Iterable<Client> getClient(){
        return clientRepository.findAll();
    }

    @GetMapping("find/id")
    public Client findClientById(@RequestParam Integer id){
        return clientRepository.findClientById(id);
    }
}
