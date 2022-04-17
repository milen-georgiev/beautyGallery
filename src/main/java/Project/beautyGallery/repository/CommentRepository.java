package Project.beautyGallery.repository;

import Project.beautyGallery.model.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentsEntity, UUID> {

}
