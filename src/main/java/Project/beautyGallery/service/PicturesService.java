package Project.beautyGallery.service;

import Project.beautyGallery.model.entity.enums.StyleNameEnum;
import Project.beautyGallery.model.entity.enums.TypeNameEnum;
import Project.beautyGallery.model.serviceModel.PicturesServiceModel;
import Project.beautyGallery.model.viewModel.PicturesViewModel;

import java.io.IOException;
import java.util.List;

public interface PicturesService {

    void uploadPictures(PicturesServiceModel picturesServiceModel, String user) throws IOException;

    List<PicturesViewModel> allPicturesView();

    List<PicturesViewModel> filterPicturesView(int type, int style);



 }
