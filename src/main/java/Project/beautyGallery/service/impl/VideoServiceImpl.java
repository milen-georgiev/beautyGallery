package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.VideoEntity;
import Project.beautyGallery.model.serviceModel.VideoServiceModel;
import Project.beautyGallery.model.viewModel.VideoViewModel;
import Project.beautyGallery.repository.UserRepository;
import Project.beautyGallery.repository.VideoRepository;
import Project.beautyGallery.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<VideoViewModel> viewVideo() {
        List<VideoEntity> allVideo = videoRepository.findAll();

        return allVideo
                .stream()
                .map(videoEntity -> modelMapper.map(videoEntity,VideoViewModel.class))
                .collect(Collectors.toList());
    }
}
