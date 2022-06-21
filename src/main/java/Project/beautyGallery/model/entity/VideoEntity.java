package Project.beautyGallery.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "video")
public class VideoEntity extends BaseEntity{

    @Column(nullable = false)
    private String videoUrl;
    private String publicationStatus;
    private LocalDate added;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userId;

    public VideoEntity() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public VideoEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public VideoEntity setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
        return this;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public VideoEntity setUserId(UserEntity userId) {
        this.userId = userId;
        return this;
    }

    public LocalDate getAdded() {
        return added;
    }

    public VideoEntity setAdded(LocalDate added) {
        this.added = added;
        return this;
    }
}
