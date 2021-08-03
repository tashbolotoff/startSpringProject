package kg.brigada.startspringproject.repos;

import kg.brigada.startspringproject.entities.PermissionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionCategoryRepo extends JpaRepository<PermissionCategory, UUID> {
}
