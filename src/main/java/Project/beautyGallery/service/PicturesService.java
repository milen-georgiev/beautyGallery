package Project.beautyGallery.service;

import Project.beautyGallery.model.serviceModel.PicturesServiceModel;

import java.io.IOException;

public interface PicturesService {

    void uploadPictures(PicturesServiceModel picturesServiceModel, String user) throws IOException;

}
