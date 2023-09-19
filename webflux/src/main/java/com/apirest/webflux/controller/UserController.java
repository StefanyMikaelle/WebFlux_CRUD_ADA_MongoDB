package com.apirest.webflux.controller;
import com.apirest.webflux.controller.UserController;
import com.apirest.webflux.model.ClientModel;
import com.apirest.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/client")
public class UserController {
	 @Autowired
	    UserRepository userRepository;

	    @GetMapping("/{name}")
	    public Flux<ClientModel> getClientByName(@PathVariable String name){
	        Flux<ClientModel> clientsFlux = userRepository.findClientByName(name);
	        return clientsFlux;
	    }

	    @PostMapping("/create")
	    public Mono<ClientModel> createClient(@RequestBody ClientModel newClient){
	        Mono<ClientModel> savedClientMono = userRepository.save(newClient);
	        return savedClientMono;
	    }


	    @DeleteMapping("/delete/{id}")
	    public Mono<ClientModel> deleteClientById(@PathVariable String id){
	        Mono<ClientModel> deletedClientMono = userRepository.deleteById(id);
	        return deletedClientMono;
	    }

	    @PatchMapping("/update/{id}")
	    public Mono<ClientModel> updateClientById(@PathVariable String id, @RequestBody ClientModel updatedClient)
	            throws ExecutionException, InterruptedException {
	        Mono<ClientModel> existingClientMono = userRepository.findById(id);
	        existingClientMono = existingClientMono.map(c -> {
	            c.setAge(updatedClient.getAge());
	            c.setEmail(updatedClient.getEmail());
	            c.setName(updatedClient.getName());
	            return c;
	        });
	        Mono<ClientModel> clientUpdated = userRepository.save(existingClientMono.toFuture().get());
	        return clientUpdated;
	    }
}
