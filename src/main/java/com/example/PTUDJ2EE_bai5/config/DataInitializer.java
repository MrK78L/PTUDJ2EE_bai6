package com.example.PTUDJ2EE_bai5.config;

import com.example.PTUDJ2EE_bai5.model.Account;
import com.example.PTUDJ2EE_bai5.model.Role;
import com.example.PTUDJ2EE_bai5.repository.AccountRepository;
import com.example.PTUDJ2EE_bai5.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== DataInitializer: Starting data initialization ===");
        
        // Tạo role nếu chưa có
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            Role adminRole = new Role("ADMIN");
            roleRepository.save(adminRole);
            System.out.println("Created ADMIN role");
        }

        if (roleRepository.findByName("USER").isEmpty()) {
            Role userRole = new Role("USER");
            roleRepository.save(userRole);
            System.out.println("Created USER role");
        }

        // Tạo tài khoản demo nếu chưa có
        if (accountRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName("ADMIN").get();
            Account admin = new Account();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(adminRole);
            accountRepository.save(admin);
            System.out.println("Created admin account with password: admin");
        } else {
            System.out.println("Admin account already exists");
        }

        if (accountRepository.findByUsername("user").isEmpty()) {
            Role userRole = roleRepository.findByName("USER").get();
            Account user = new Account();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRole(userRole);
            accountRepository.save(user);
            System.out.println("Created user account with password: user");
        } else {
            System.out.println("User account already exists");
        }
        
        System.out.println("=== DataInitializer: Data initialization complete ===");
    }
}
