package Project.beautyGallery.repository;

import Project.beautyGallery.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    void deleteUserEntitiesById(UUID id);
}
