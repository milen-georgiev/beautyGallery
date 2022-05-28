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
        TypeNameEnum typeNameEnum = null;
        StyleNameEnum styleNameEnum = null;

        if (filterBindingModel.getType() != 0) {
            switch (filterBindingModel.getType()) {
                case 1:
                    typeNameEnum = TypeNameEnum.MANICURE;
                    break;
                case 2:
                    typeNameEnum = TypeNameEnum.HAIRSTYLES;
                    break;
                case 3:
                    typeNameEnum = TypeNameEnum.MAKEUP;
                    break;
            }
        }

        if (filterBindingModel.getStyle() != 0) {
            switch (filterBindingModel.getStyle()) {
                case 1:
                    styleNameEnum = StyleNameEnum.DAILY;
                    break;
                case 2:
                    styleNameEnum = StyleNameEnum.EVENING;
                    break;
                case 3:
                    styleNameEnum = StyleNameEnum.WEDDING;
                    break;
                case 4:
                    styleNameEnum = StyleNameEnum.GRADUATION;
                    break;
            }
        }

        if (styleNameEnum != null) {
            List<PicturesViewModel> allPictures = picturesService.filterPicturesViewTypeAndStyle(typeNameEnum, styleNameEnum);
            model.addAttribute("allPictures", allPictures);
        } else if (typeNameEnum != null) {
            List<PicturesViewModel> allPictures = picturesService.allPicturesViewType(typeNameEnum);
            model.addAttribute("allPictures", allPictures);
        }
//        else {
//            List<PicturesViewModel> allPictures = picturesService.allPicturesView();
//        model.addAttribute("allPictures", allPictures);
//        return "redirect:gallery";
//        }


        return "redirect:gallery";
    }

    @ModelAttribute("filterBindingModel")
    public FilterBindingModel filterBindingModel() {
        return new FilterBindingModel();
    }
}
