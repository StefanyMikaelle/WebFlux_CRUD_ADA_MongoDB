package com.apirest.webflux.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "clients")
public class ClientModel {

    @Id

    private Integer age;
    private String name;
    private String id = new ObjectId().toString();

    @Indexed(unique = true, background = true)
    private String email;

    public ClientModel(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
