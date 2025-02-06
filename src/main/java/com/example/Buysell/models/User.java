package com.example.Buysell.models;

import com.example.Buysell.models.Enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "ative")
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // CascadeType.ALL
    @JoinColumn(name = "image_id")
    private Image avatar;
    @Column(name = "password", length = 1000)
    private String password;
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user") // CascadeType.REFRESH{CascadeType.MERGE,CascadeType.REFRESH,
    private List<Product> products = new ArrayList<>();
    private LocalDateTime dateOfCreated;



    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();

    }
    public boolean idAdmin(){
        return  roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
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
        return active;
    }


  @Override
  public String toString() {
      return "User{" +
              "id=" + id +
              ", email='" + email + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", name='" + name + '\'' +
              ", active=" + active +
              ", avatar=" + avatar +
              ", password='" + password + '\'' +
              ", roles=" + roles +
//              ", products=" + products.toString() +
              ", dateOfCreated=" + dateOfCreated +
              '}';
  }
}

