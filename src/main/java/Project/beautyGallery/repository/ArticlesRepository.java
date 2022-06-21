package Project.beautyGallery.repository;

import Project.beautyGallery.model.entity.ArticlesEntity;
import Project.beautyGallery.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticlesRepository extends JpaRepository<ArticlesEntity, UUID> {

    List<ArticlesEntity> findArticlesEntityByPublished(UserEntity userEntity);

}
