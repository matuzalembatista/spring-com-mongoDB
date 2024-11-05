package br.edu.iftm.springmongo.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "users")
public class User {
    @Id
    private Object id;
    @Field("name")
    private String name;
    private Integer age;
    private Adress address;

    public User() {
    }

    public User(String name, Integer age, Adress adress) {
        this.name = name;
        this.age = age;
        this.address = adress;
    }
}
