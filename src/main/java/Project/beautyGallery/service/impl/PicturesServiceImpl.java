package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.CloudinaryImageEntity;
import Project.beautyGallery.model.entity.PicturesEntity;
import Project.beautyGallery.model.entity.enums.StyleNameEnum;
import Project.beautyGallery.model.entity.enums.TypeNameEnum;
import Project.beautyGallery.model.serviceModel.PicturesServiceModel;
import Project.beautyGallery.model.viewModel.PicturesViewModel;
import Project.beautyGallery.repository.PicturesRepository;
import Project.beautyGallery.repository.UserRepository;
import Project.beautyGallery.service.CloudinaryService;
import Project.beautyGallery.service.PicturesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    // Pictures View  ----------------------------
    // all pictures ------------------------------
    public List<PicturesViewModel> allPicturesView() {
        List<PicturesEntity> picturesEntityList = picturesRepository
                .findAll();

        return picturesEntityList
                .stream()
                .map(picturesEntity -> modelMapper.map(picturesEntity, PicturesViewModel.class))
                .collect(Collectors.toList());

    }

    // filter pictures ---------------------------
    @Override
    public List<PicturesViewModel> filterPicturesView(int type, int style) {

        TypeNameEnum typePictures = null;
        if (type > 0){
            switch (type){
                case 1:
                    typePictures = TypeNameEnum.MANICURE;
                    break;
                case 2:
                    typePictures = TypeNameEnum.HAIRSTYLES;
                    break;
                case 3:
                    typePictures = TypeNameEnum.MAKEUP;
                    break;
            }
        }
        StyleNameEnum stylePictures = null;
        if (style > 0) {
            switch (style){
                case 1:
                    stylePictures = StyleNameEnum.DAILY;
                    break;
                case 2:
                    stylePictures = StyleNameEnum.EVENING;
                    break;
                case 3:
                    stylePictures = StyleNameEnum.WEDDING;
                    break;
                case 4:
                    stylePictures = StyleNameEnum.GRADUATION;
                    break;
            }
        }

        List<PicturesEntity> picturesEntityList = null;
        if (style > 0) {
            picturesEntityList = picturesRepository
                    .findPicturesEntityByCategoryTypeAndCategoryStyle(typePictures, stylePictures);

        } else if (type > 0) {

            picturesEntityList = picturesRepository
                    .findPicturesEntityByCategoryType(typePictures);
        }
        else {
            picturesEntityList = picturesRepository
                    .findAll();

        }

        return picturesEntityList
                .stream()
                .map(picturesEntity -> modelMapper.map(picturesEntity, PicturesViewModel.class))
                .collect(Collectors.toList());
    }

    // delete pictures ---------------------------
    @Override
    public void deletePictures(String publicId) {
        picturesRepository.deleteAllByPublicId(publicId);
    }

    // create pictures ---------------------------
    private PicturesEntity createPicturesEntity(MultipartFile file) throws IOException {
        final CloudinaryImageEntity uploaded = this.cloudinaryService.upload(file);

        return new PicturesEntity()
                .setPublicId(uploaded.getPublicId())
                .setUrl(uploaded.getUrl());
    }
}

