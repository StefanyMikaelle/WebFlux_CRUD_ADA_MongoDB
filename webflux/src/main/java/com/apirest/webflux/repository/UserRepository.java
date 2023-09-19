package com.apirest.webflux.repository;
import com.apirest.webflux.model.ClientModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<ClientModel, Long> {
    Flux<ClientModel> findClientByName(String name);
    Mono<ClientModel> deleteById(String id);
    Mono<ClientModel> findById(String id);
}

