package Project.beautyGallery.model.viewModel;

import Project.beautyGallery.model.entity.CommentsEntity;
import Project.beautyGallery.model.entity.UserEntity;
import Project.beautyGallery.model.entity.enums.ArticlesNameEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArticlesViewModel {
    private UUID id;
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

    public UUID getId() {
        return id;
    }

    public ArticlesViewModel setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArticlesViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArticlesViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ArticlesNameEnum getCategory() {
        return category;
    }

    public ArticlesViewModel setCategory(ArticlesNameEnum category) {
        this.category = category;
        return this;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public ArticlesViewModel setComments(List<CommentsEntity> comments) {
        this.comments = comments;
        return this;
    }

    public UserEntity getPublished() {
        return published;
    }

    public ArticlesViewModel setPublished(UserEntity published) {
        this.published = published;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ArticlesViewModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public ArticlesViewModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public LocalDate getAdded() {
        return added;
    }

    public ArticlesViewModel setAdded(LocalDate added) {
        this.added = added;
        return this;
    }
}
