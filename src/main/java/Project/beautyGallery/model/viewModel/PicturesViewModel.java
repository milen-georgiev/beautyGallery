package Project.beautyGallery.model.viewModel;

import java.time.Instant;
import java.util.UUID;

public class PicturesViewModel {
    private UUID id;
    private String url;
    private String publicId;
    private Instant added;
    private Integer likes;

    public PicturesViewModel() {
    }

    public UUID getId() {
        return id;
    }

    public PicturesViewModel setId(UUID id) {
        this.id = id;
        return this;
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

    public Instant getAdded() {
        return added;
    }

    public void setAdded(Instant added) {
        this.added = added;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
