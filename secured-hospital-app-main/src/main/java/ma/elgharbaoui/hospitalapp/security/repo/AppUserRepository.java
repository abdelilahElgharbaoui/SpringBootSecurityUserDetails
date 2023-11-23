package ma.elgharbaoui.hospitalapp.security.repo;

import ma.elgharbaoui.hospitalapp.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
