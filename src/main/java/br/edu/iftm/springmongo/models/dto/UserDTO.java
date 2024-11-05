package br.edu.iftm.springmongo.models.dto;

import br.edu.iftm.springmongo.models.Adress;
import br.edu.iftm.springmongo.models.User;

import java.io.Serializable;

@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@lombok.ToString
public class UserDTO implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private Adress adress;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.age = user.getAge();
        this.adress = user.getAddress();
    }

    public UserDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
