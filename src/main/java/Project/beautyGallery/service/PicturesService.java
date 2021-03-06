package Project.beautyGallery.service;

import Project.beautyGallery.model.serviceModel.PicturesServiceModel;
import Project.beautyGallery.model.viewModel.PicturesViewModel;

import java.io.IOException;
import java.util.List;

public interface PicturesService {

    void uploadPictures(PicturesServiceModel picturesServiceModel, String user) throws IOException;

    List<PicturesViewModel> allPicturesView();

    List<PicturesViewModel> filterPicturesView(int type, int style);

    void deletePictures(String publicId);

    List<PicturesViewModel> onlyPicturesOfUser(String username);

 }
