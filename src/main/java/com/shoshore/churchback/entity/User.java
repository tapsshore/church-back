package com.shoshore.churchback.entity;

import com.shoshore.churchback.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "church_member_id")
    private ChurchMember churchMember;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the user's roles to SimpleGrantedAuthority objects
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        // Return whether the user's account is non-expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Return whether the user's account is non-locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Return whether the user's credentials are non-expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Return whether the user's account is enabled
        return true;
    }
}
