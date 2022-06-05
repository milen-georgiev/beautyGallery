package Project.beautyGallery.model.entity;

import Project.beautyGallery.model.entity.enums.ArticlesNameEnum;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grandmas_secret_articles")
public class ArticlesEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Size(min = 50)
    @Column(nullable = false, columnDefinition = "LongText")
    private String description;
    @Enumerated(EnumType.STRING)
    private ArticlesNameEnum category;
    @OneToMany
    private List<CommentsEntity> comments = new ArrayList<>();
    @ManyToOne
    private UserEntity published;
    private String url;
    private String publicId;
    private LocalDate added;
    private String status;

    public ArticlesEntity() {
    }

    public String getName() {
        return name;
    }

    public ArticlesEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArticlesEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public ArticlesNameEnum getCategory() {
        return category;
    }

    public ArticlesEntity setCategory(ArticlesNameEnum category) {
        this.category = category;
        return this;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public ArticlesEntity setComments(List<CommentsEntity> comments) {
        this.comments = comments;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ArticlesEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public ArticlesEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public UserEntity getPublished() {
        return published;
    }

    public ArticlesEntity setPublished(UserEntity published) {
        this.published = published;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ArticlesEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public LocalDate getAdded() {
        return added;
    }

    public ArticlesEntity setAdded(LocalDate added) {
        this.added = added;
        return this;
    }
}