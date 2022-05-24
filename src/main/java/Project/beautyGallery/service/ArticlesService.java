package Project.beautyGallery.service;

import Project.beautyGallery.model.serviceModel.ArticlesServiceModel;
import Project.beautyGallery.model.viewModel.ArticlesViewModel;

import java.io.IOException;
import java.util.List;

public interface ArticlesService {

    void addGrandmasSecret(ArticlesServiceModel articlesServiceModel, String user) throws IOException;

    List<ArticlesViewModel> allArticlesView();
}
