package com.ironhack.semana11dia2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // hacemos que el name sea único y obligatorio en la base de datos
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = LAZY)
    @JsonIgnore // para que en el JSON (en el postman) no me muestre esto
    private List<User> users = new ArrayList<>();

    public Role(String name) {
        this.name = name;
    }
}
