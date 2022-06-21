package Project.beautyGallery.web;

import Project.beautyGallery.model.binding.UserRegistrationBindingModel;
import Project.beautyGallery.model.serviceModel.UserRegistrationServiceModel;
import Project.beautyGallery.model.viewModel.ArticlesViewModel;
import Project.beautyGallery.model.viewModel.PicturesViewModel;
import Project.beautyGallery.model.viewModel.UserViewModel;
import Project.beautyGallery.model.viewModel.VideoViewModel;
import Project.beautyGallery.service.ArticlesService;
import Project.beautyGallery.service.PicturesService;
import Project.beautyGallery.service.UserService;
import Project.beautyGallery.service.VideoService;
import Project.beautyGallery.service.impl.BeautyGalleryUsers;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PicturesService picturesService;
    private final VideoService videoService;
    private final ArticlesService articlesService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, PicturesService picturesService,
                          VideoService videoService, ArticlesService articlesService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.picturesService = picturesService;
        this.videoService = videoService;
        this.articlesService = articlesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("isBusy", true);
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid UserRegistrationBindingModel userRegistrationBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {


        if (!userService.isUserNameFree(userRegistrationBindingModel.getUsername())) {
            redirectAttributes
                    .addFlashAttribute("isBusy", false)
                    .addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel",
                            bindingResult);

            return "redirect:register";
        }


        if (bindingResult.hasErrors() || !userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);

            return "redirect:/register";
        }


        UserRegistrationServiceModel UserRegistrationServiceModel =
                modelMapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        userService.registerAndLoginUser(UserRegistrationServiceModel);

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal BeautyGalleryUsers user) {

        UserViewModel userViewModel = userService.userDetails(user.getUserIdentified());
        List<PicturesViewModel> userPictures = picturesService.onlyPicturesOfUser(user.getUsername());
        List<ArticlesViewModel> userArticles = articlesService.onlyArticlesUser(user.getUsername());
        List<VideoViewModel> userVideo = videoService.onlyVideoUser(user.getUsername());

        model.addAttribute("userViewModel", userViewModel);
        model.addAttribute("userPictures", userPictures);
        model.addAttribute("userArticles", userArticles);
        model.addAttribute("userVideo", userVideo);

        return "profile";
    }

    @Transactional
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") UUID id,HttpServletRequest httpServletRequest) throws ServletException {

        userService.deleteUser(id);
        httpServletRequest.logout();
        return "register";
    }


    @ModelAttribute("userRegistrationBindingModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }
}
