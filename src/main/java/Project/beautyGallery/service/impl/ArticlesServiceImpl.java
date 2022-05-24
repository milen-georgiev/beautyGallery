package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.ArticlesEntity;
import Project.beautyGallery.model.entity.CloudinaryImageEntity;
import Project.beautyGallery.model.serviceModel.ArticlesServiceModel;
import Project.beautyGallery.model.viewModel.ArticlesViewModel;
import Project.beautyGallery.repository.ArticlesRepository;
import Project.beautyGallery.repository.UserRepository;
import Project.beautyGallery.service.ArticlesService;
import Project.beautyGallery.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    @Override
    public List<ArticlesViewModel> allArticlesView() {
        return articlesRepository
                .findAll()
                .stream()
                .map(articlesEntity -> {
                    ArticlesViewModel articlesViewModel = modelMapper
                            .map(articlesEntity, ArticlesViewModel.class);

                    return articlesViewModel;
                })
                .collect(Collectors.toList());
    }
}
