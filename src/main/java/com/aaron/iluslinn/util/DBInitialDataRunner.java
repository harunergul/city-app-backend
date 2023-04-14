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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DBInitialDataRunner implements CommandLineRunner {

    private final CityRepository cityRepository;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        log.info("Loading users and cities to DB");
        createInitialUsers();
        addCitiesToDB();
        log.info("Loading users and cities to DB completed");
    }

    private void addCitiesToDB() {
        long cityCount = cityRepository.count();

        if(cityCount>0){
            return;
        }
        City[] data = ResourceUtil.getDataFromResourceFile("/data/city-mock-data.json", City[].class);
        List<City> cityList = new ArrayList<City>();
        for (City city : data)
        {
            cityList.add(city);
        }

        cityRepository.saveAll(cityList);
        log.info("Saved {} new cities to DB ", cityList.size() );
    }

    private void createInitialUsers(){

        long existingUserCount = roleRepository.count();

        if(existingUserCount>0){
            return;
        }

        User user = new User();
        user.setUsername("admin");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEnabled(true);

        user.setPassword(passwordEncoder.encode("admin"));

        user = userRepository.save(user);
        Role userRole = new Role();
        userRole.setRole("ROLE_ALLOW_EDIT");
        userRole.setUser(user);
        roleRepository.save(userRole);

        userRole = new Role();
        userRole.setRole("ROLE_ALLOW_CREATE");
        userRole.setUser(user);
        roleRepository.save(userRole);


        User normalUser = new User();
        normalUser.setUsername("harun");
        normalUser.setFirstName("harun");
        normalUser.setLastName("ergul");
        normalUser.setEnabled(true);
        normalUser.setPassword(passwordEncoder.encode("harun"));
        userRepository.save(normalUser);

        userRole = new Role();
        userRole.setRole("ROLE_ALLOW_CREATE");
        userRole.setUser(normalUser);
        roleRepository.save(userRole);


    }
}
