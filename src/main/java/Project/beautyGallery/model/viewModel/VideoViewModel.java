package Project.beautyGallery.model.viewModel;

import Project.beautyGallery.model.entity.UserEntity;


public class VideoViewModel {

    private String videoUrl;
    private UserEntity setPublishedVideo;

    public VideoViewModel() {
    }


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public UserEntity getUserId() {
        return setPublishedVideo;
    }

    public VideoViewModel setUserId(UserEntity userId) {
        this.setPublishedVideo = setPublishedVideo;
        return this;
    }
}
