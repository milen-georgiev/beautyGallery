package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.CloudinaryImageEntity;
import Project.beautyGallery.model.entity.PicturesEntity;
import Project.beautyGallery.model.serviceModel.PicturesServiceModel;
import Project.beautyGallery.repository.PicturesRepository;
import Project.beautyGallery.repository.UserRepository;
import Project.beautyGallery.service.CloudinaryService;
import Project.beautyGallery.service.PicturesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class PicturesServiceImpl implements PicturesService {

    private final PicturesRepository picturesRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public PicturesServiceImpl(PicturesRepository picturesRepository, UserRepository userRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.picturesRepository = picturesRepository;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    // Cloudinary  ------------------------------
    // upload pictures to cloudinary-------------
    @Override
    public void uploadPictures(PicturesServiceModel picturesServiceModel, String user) throws IOException {
        PicturesEntity pictures = createPicturesEntity(picturesServiceModel.getPictures());

        pictures
                .setCategoryType(picturesServiceModel.getTypeNameEnum())
                .setCategoryStyle(picturesServiceModel.getStyleNameEnum())
                .setUser(userRepository.findByUsername(user).orElseThrow())
                .setPublicationStatus("unverified")
                .setAdded(LocalDate.now())
                .setLikes(0);

        picturesRepository.save(pictures);
    }

    private PicturesEntity createPicturesEntity(MultipartFile file) throws IOException {
        final CloudinaryImageEntity uploaded = this.cloudinaryService.upload(file);

        return new PicturesEntity()
                .setPublicId(uploaded.getPublicId())
                .setUrl(uploaded.getUrl());
    }
}

