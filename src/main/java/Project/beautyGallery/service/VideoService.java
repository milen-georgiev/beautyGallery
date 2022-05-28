package Project.beautyGallery.service;

import Project.beautyGallery.model.serviceModel.VideoServiceModel;
import Project.beautyGallery.model.viewModel.VideoViewModel;

import java.util.List;

public interface VideoService {

    void addVideo(VideoServiceModel videoServiceModel, String username);

    List<VideoViewModel> viewVideo();

}
