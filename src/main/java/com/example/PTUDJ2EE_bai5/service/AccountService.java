package com.example.PTUDJ2EE_bai5.service;

import com.example.PTUDJ2EE_bai5.model.Account;
import com.example.PTUDJ2EE_bai5.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called with: " + username);
        
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("Account not found for username: " + username);
                    return new UsernameNotFoundException("Không tìm thấy tài khoản: " + username);
                });

        System.out.println("Account found: " + account.getUsername() + " with role: " + account.getRole().getName());

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (account.getRole() != null) {
            String authority = "ROLE_" + account.getRole().getName().toUpperCase();
            authorities.add(new SimpleGrantedAuthority(authority));
            System.out.println("Added authority: " + authority);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(authorities)
                .build();
    }
}
