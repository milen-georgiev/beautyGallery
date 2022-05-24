package Project.beautyGallery.web;

import Project.beautyGallery.model.viewModel.ArticlesViewModel;
import Project.beautyGallery.service.ArticlesService;
import Project.beautyGallery.service.CloudinaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArticlesController {

    private final CloudinaryService cloudinaryService;
    private final ArticlesService articlesService;

    public ArticlesController(CloudinaryService cloudinaryService, ArticlesService articlesService) {
        this.cloudinaryService = cloudinaryService;
        this.articlesService = articlesService;
    }

    @GetMapping("/articles")
    public String articles(Model model) {

        List<ArticlesViewModel> articles = articlesService.allArticlesView();

        for (ArticlesViewModel articlesViewModel: articles){
            articlesViewModel
                    .setDescription(articlesViewModel
                                    .getDescription()
                                    .substring(0,50) + "...");
        }

        model.addAttribute("articles", articles);

        return "articles";
    }

}
