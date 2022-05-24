package Project.beautyGallery.model.entity;

public class CloudinaryImageEntity {

    private String url;
    private String publicId;

    public CloudinaryImageEntity() {
    }

    public String getUrl() {
        return url;
    }

    public CloudinaryImageEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public CloudinaryImageEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
