package by.naumenko.jm.spring_boot_crud.repo;

import by.naumenko.jm.spring_boot_crud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
