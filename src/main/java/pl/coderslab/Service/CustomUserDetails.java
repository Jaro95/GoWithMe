package pl.coderslab.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.coderslab.model.Role;

import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private long id;
    private String username;
    private String password;
    private Set<Role> authorities;

    public CustomUserDetails(long id, String username, String password, Set<Role> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }

}
