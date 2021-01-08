package ru.tkachenko.ecare.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.tkachenko.ecare.models.Client;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final String userName;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;

    public SecurityUser(String userName, String password, List<SimpleGrantedAuthority> authorities) {
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static UserDetails fromAdmin(Client client){
        return new org.springframework.security.core.userdetails.User(
                client.getEmail(), client.getPassword(), client.getRole().getAuthorities()
        );
    }
}
