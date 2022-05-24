package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.ArticlesEntity;
import Project.beautyGallery.model.entity.CloudinaryImageEntity;
import Project.beautyGallery.model.serviceModel.ArticlesServiceModel;
import Project.beautyGallery.repository.ArticlesRepository;
import Project.beautyGallery.repository.UserRepository;
import Project.beautyGallery.service.ArticlesService;
import Project.beautyGallery.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    private final ArticlesRepository articlesRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public ArticlesServiceImpl(ArticlesRepository articlesRepository, UserRepository userRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.articlesRepository = articlesRepository;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGrandmasSecret(ArticlesServiceModel articlesServiceModel, String user) throws IOException {
        final CloudinaryImageEntity uploaded = this.
                cloudinaryService.
                upload(articlesServiceModel.getPictures());

        ArticlesEntity articlesEntity = new ArticlesEntity();

        articlesEntity
                .setName(articlesServiceModel.getName())
                .setDescription(articlesServiceModel.getDescription())
                .setCategory(articlesServiceModel.getCategory())
                .setPublished(userRepository.findByUsername(user).orElseThrow())
                .setUrl(uploaded.getUrl())
                .setPublicId(uploaded.getPublicId())
                .setAdded(LocalDate.now())
                .setStatus("unverified");


        articlesRepository.save(articlesEntity);
    }
}
