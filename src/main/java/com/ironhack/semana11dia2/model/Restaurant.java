package com.ironhack.semana11dia2.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends User {
    private String address;

    // NUNCA CASCADE EN EL MANYTOONE, ONE TO MANY
    // cuando yo a un restaurante le añada una mesa a su lista y haga .save() también por debajo hará .save() de esa mesa relacionada al restaurante
    // orphan removal -> quita la mesa de la BBDD si la quito de la lista de mesas del restaurante
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Table> tables = new ArrayList<>(); // mutable, se puede modificar

    public Restaurant(String username, String password, String address) {
        super(username, password);
        this.address = address;
    }

    // para manejar más fácil la bidireccionalidad
    // susituye al restaurant.getTables().add(table) --> fuera de esta clase
    public void addTable(Table table) {
        tables.add(table);
        table.setRestaurant(this);
    }

    // susituye al restaurant.getTables().remove(table) --> fuera de esta clase
    public void removeTable(Table table) {
        tables.remove(table);
        table.setRestaurant(null);
    }
}
