package com.aaron.iluslinn.util;

import com.aaron.iluslinn.model.City;
import com.aaron.iluslinn.model.Role;
import com.aaron.iluslinn.model.User;
import com.aaron.iluslinn.repository.CityRepository;
import com.aaron.iluslinn.repository.RoleRepository;
import com.aaron.iluslinn.repository.UserRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DBInitialDataRunner implements CommandLineRunner {

    private final CityRepository cityRepository;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        long existingItemCount = cityRepository.count();

        if(existingItemCount>0){
            return;
        }
        City[] data = ResourceUtil.getDataFromResourceFile("/data/city-mock-data.json", City[].class);
        List<City> cityList = new ArrayList<City>();
        for (City obj : data)
        {
            cityList.add((City)obj);
        }

        cityRepository.saveAll(cityList);

        System.out.println("Runs on startup");



    }

    private void createInitialUsers(){


        User user = new User();

        user.setUsername("admin");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEnabled(true);
        user.setPassword("admin");

        user = userRepository.save(user);
        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        userRole.setUser(user);
        roleRepository.save(userRole);
    }
}
