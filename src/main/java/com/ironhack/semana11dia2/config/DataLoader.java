package com.ironhack.semana11dia2.config;

import com.ironhack.semana11dia2.model.Customer;
import com.ironhack.semana11dia2.model.Restaurant;
import com.ironhack.semana11dia2.model.Role;
import com.ironhack.semana11dia2.model.Table;
import com.ironhack.semana11dia2.service.RoleService;
import com.ironhack.semana11dia2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;


    @Override
    public void run(String... args) throws Exception {
        Table table1 = new Table(2);
        Table table2 = new Table(4);
        Table table3 = new Table(4);

        Restaurant restaurant = new Restaurant("pizza@heaven.com", "1234", "123 Heaven St");
        // tableRepository.saveAll(List.of(table1,table2,table3)); --> si no tengo lo de cascade ALL en restaurante

        // restaurant.getTables().add(table1); --> si quiero añadir la mesa a la lista en restaurant y no tengo el método addTable

        restaurant.addTable(table1);
        restaurant.addTable(table2);
        restaurant.addTable(table3);

        Customer customer = new Customer("john@example.com", "1234", "555-1234");

        roleService.save(new Role("ROLE_USER"));
        roleService.save(new Role("ROLE_ADMIN"));

        userService.saveUser(restaurant);
        roleService.addRoleToUser("pizza@heaven.com", "ROLE_ADMIN");

        userService.saveUser(customer);
        roleService.addRoleToUser("john@example.com", "ROLE_USER");
    }
}
