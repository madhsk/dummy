package com.springboot.filmrentalstore.initializer;

import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RoleRepo roleRepository;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("initializer executed *********");
		// Create roles
//        Role adminRole = new Role("ROLE_ADMIN");
//        Role userRole = new Role("ROLE_USER");
//        Role staffRole = new Role("ROLE_STAFF");
//        roleRepository.save(adminRole);
//        roleRepository.save(userRole);
//        roleRepository.save(staffRole);
		// roleRepository.saveAll(Arrays.asList(adminRole, userRole));
		// Create users
//        UserEntity adminUser = new UserEntity();
//        UserEntity userUser = new UserEntity();
//         adminUser.setUsername("admin");
//         adminUser.setPassword(passwordEncoder.encode("password@123"));
//         adminUser.setRoles(Arrays.asList(adminRole,userRole));
//         userUser.setUsername("user");
//         userUser.setPassword(passwordEncoder.encode("password@12345"));
//         userUser.setRoles(Arrays.asList(userRole));
//       userRepository.saveAll(Arrays.asList(adminUser, userUser));
	}

}
