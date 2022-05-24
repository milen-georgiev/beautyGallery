package Project.beautyGallery.model.viewModel;

import Project.beautyGallery.model.entity.CommentsEntity;
import Project.beautyGallery.model.entity.UserEntity;
import Project.beautyGallery.model.entity.enums.ArticlesNameEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticlesViewModel {
    private Long id;
    private String name;
    private String description;
    private ArticlesNameEnum category;
    private List<CommentsEntity> comments = new ArrayList<>();
    private UserEntity published;
    private String url;
    private String publicId;
    private LocalDate added;

    public ArticlesViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArticlesNameEnum getCategory() {
        return category;
    }

    public void setCategory(ArticlesNameEnum category) {
        this.category = category;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

    public UserEntity getPublished() {
        return published;
    }

    public void setPublished(UserEntity published) {
        this.published = published;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }
}
