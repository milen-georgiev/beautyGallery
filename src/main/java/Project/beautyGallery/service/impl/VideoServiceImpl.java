package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.VideoEntity;
import Project.beautyGallery.model.serviceModel.VideoServiceModel;
import Project.beautyGallery.repository.UserRepository;
import Project.beautyGallery.repository.VideoRepository;
import Project.beautyGallery.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public VideoServiceImpl(VideoRepository videoRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addVideo(VideoServiceModel videoServiceModel, String username) {

        VideoEntity videoEntity = new VideoEntity();

        videoEntity
                .setVideoUrl(videoServiceModel.getVideoUrl())
                .setPublicationStatus("unverified")
                .setAdded(LocalDate.now())
                .setUser(userRepository.findByUsername(username).orElseThrow());

        videoRepository.save(videoEntity);

    }
}
