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
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @Transactional
    @DeleteMapping("/gallery/delete")
    public String delete(@RequestParam("public_id") String publicId) {
        if (cloudinaryService.delete(publicId)) {
            picturesService.deletePictures(publicId);
        }

        return "redirect:/gallery";
    }

    @ModelAttribute("filterBindingModel")
    public FilterBindingModel filterBindingModel() {
        return new FilterBindingModel();
    }
}
