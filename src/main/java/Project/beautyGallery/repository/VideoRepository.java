package Project.beautyGallery.repository;

import Project.beautyGallery.model.entity.UserEntity;
import Project.beautyGallery.model.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {

    List<VideoEntity> findVideoEntityByUser(UserEntity user);

}
