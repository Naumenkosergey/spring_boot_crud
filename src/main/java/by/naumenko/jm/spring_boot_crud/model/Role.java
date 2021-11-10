package by.naumenko.jm.spring_boot_crud.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role", schema = "security_storage")
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "role")
    private String authority;

}
