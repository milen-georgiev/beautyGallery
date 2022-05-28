package Project.beautyGallery.web;

import Project.beautyGallery.model.viewModel.ArticlesViewModel;
import Project.beautyGallery.service.ArticlesService;
import Project.beautyGallery.service.CloudinaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/details/{id}")
    public String detailsArticles(@PathVariable("id") UUID id, Model model) {

//        if (grandmasSecretArticlesService.findArticlesById(id) == null) {
//            throw new ObjectNotFoundException("Обект с номер " + id + " не е намерен!");
//        }

//        try {
//            int idNumber = Integer.parseInt(id.toString());w
//        } catch (NumberFormatException exception){
//            throw new NumberFormatException("В номера на обекта " + id + " е намерен текст!");
//        }

        model.addAttribute("articles", articlesService.findArticlesById(id));

        return "details";
    }



}
