package by.naumenko.jm.spring_boot_crud.model;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "security_storage")
public class User extends BaseEntity implements UserDetails  {

    @Column(name = "login", unique = true)
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Transient
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", schema = "security_storage", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();//authorities

    @Transient
    private boolean accountNonExpired = true;
    @Transient
    private boolean accountNonLocked = true;
    @Transient
    private boolean credentialsNonExpired = true;
    @Transient
    private boolean enabled = true;

    public boolean hasAuthorities(String roleName) {
        if (authorities == null || authorities.size() == 0) {
            return false;
        }
        Optional<Role> findRole = authorities.stream()
                .filter(role -> roleName.equals(role.getAuthority()))
                .findFirst();
        return findRole.isPresent();
    }
}
