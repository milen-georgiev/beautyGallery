package Project.beautyGallery.web;

import Project.beautyGallery.model.binding.FilterBindingModel;
import Project.beautyGallery.model.binding.UserRegistrationBindingModel;
import Project.beautyGallery.model.entity.enums.StyleNameEnum;
import Project.beautyGallery.model.entity.enums.TypeNameEnum;
import Project.beautyGallery.model.viewModel.PicturesViewModel;
import Project.beautyGallery.service.CloudinaryService;
import Project.beautyGallery.service.PicturesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GalleryController {

    private final PicturesService picturesService;
    private final CloudinaryService cloudinaryService;

    public GalleryController(PicturesService picturesService, CloudinaryService cloudinaryService) {
        this.picturesService = picturesService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        List<PicturesViewModel> allPictures = picturesService.allPicturesView();

        model.addAttribute("allPictures", allPictures);
        return "gallery";
    }

    @PostMapping("/gallery")
    public String filterGallery(FilterBindingModel filterBindingModel, Model model) {

        List<PicturesViewModel> allPictures = picturesService.filterPicturesView(filterBindingModel.getType(),filterBindingModel.getStyle());

        model.addAttribute("allPictures", allPictures);

        return "gallery";
    }

    @ModelAttribute("filterBindingModel")
    public FilterBindingModel filterBindingModel() {
        return new FilterBindingModel();
    }
}
