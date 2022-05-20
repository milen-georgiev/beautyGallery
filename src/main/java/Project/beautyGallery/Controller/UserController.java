package Project.beautyGallery.Controller;

import Project.beautyGallery.model.binding.UserRegistrationBindingModel;
import Project.beautyGallery.model.serviceModel.UserRegistrationServiceModel;
import Project.beautyGallery.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
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


    @ModelAttribute("userRegistrationBindingModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }
}
