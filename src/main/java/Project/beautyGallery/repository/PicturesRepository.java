package Project.beautyGallery.repository;

import Project.beautyGallery.model.entity.PicturesEntity;
import Project.beautyGallery.model.entity.enums.StyleNameEnum;
import Project.beautyGallery.model.entity.enums.TypeNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PicturesRepository extends JpaRepository<PicturesEntity, UUID> {

    List<PicturesEntity> findPicturesEntityByCategoryType(TypeNameEnum type);

    List<PicturesEntity> findPicturesEntityByCategoryTypeAndCategoryStyle(TypeNameEnum type, StyleNameEnum style);
}
