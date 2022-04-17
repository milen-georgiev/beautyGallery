package Project.beautyGallery.repository;

import Project.beautyGallery.model.entity.UserRoleEntity;
import Project.beautyGallery.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UUID> {

    UserRoleEntity findByName(RoleNameEnum role);

}
