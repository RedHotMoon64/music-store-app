package org.vlad.music.store.app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.vlad.music.store.app.constants.SecurityConstants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${regular.user.username}")
    private String username;
    @Value("${regular.user.password}")
    private String userPassword;
    @Value("${supplier.user.username}")
    private String supplierUsername="supplier";
    @Value("${supplier.user.password}")
    private String supplierPassword="supplier-password";
    @Value("${vendor.user.username}")
    private String vendorUsername;
    @Value("${vendor.user.password}")
    private String vendorPassword;
    @Value("${admin.user.username}")
    private String adminUsername;
    @Value("${admin.user.password}")
    private String adminPassword;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("music-store/get-all-albums").hasRole(USER)
                        .requestMatchers("music-store/add").hasRole(SUPPLIER)
                        .requestMatchers("music-store/get-album/**").hasRole(VENDOR)
                        .requestMatchers("music-store/update-price/**").hasRole(VENDOR)
                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User
                .withUsername(username)
                .password(passwordEncoder.encode(userPassword))
                .roles(USER)
                .build();

        UserDetails supplier = User
                .withUsername(supplierUsername)
                .password(passwordEncoder.encode(supplierPassword))
                .roles(SUPPLIER, USER)
                .build();

        UserDetails vendor = User
                .withUsername(vendorUsername)
                .password(passwordEncoder.encode(vendorPassword))
                .roles(VENDOR, USER)
                .build();

        UserDetails admin = User
                .withUsername(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .roles(VENDOR, SUPPLIER, USER)
                .build();

        return new InMemoryUserDetailsManager(user, supplier, vendor, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
