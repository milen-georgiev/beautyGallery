package Project.beautyGallery.service;

import Project.beautyGallery.model.serviceModel.ArticlesServiceModel;

import java.io.IOException;

public interface ArticlesService {

    void addGrandmasSecret(ArticlesServiceModel articlesServiceModel, String user) throws IOException;
}
