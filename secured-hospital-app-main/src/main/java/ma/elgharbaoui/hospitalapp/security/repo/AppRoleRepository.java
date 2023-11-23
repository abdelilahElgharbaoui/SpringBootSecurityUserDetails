package ma.elgharbaoui.hospitalapp.security.repo;

import ma.elgharbaoui.hospitalapp.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {
}
