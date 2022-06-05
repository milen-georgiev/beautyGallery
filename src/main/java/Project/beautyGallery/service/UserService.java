package Project.beautyGallery.service;

import Project.beautyGallery.model.serviceModel.UserRegistrationServiceModel;
import Project.beautyGallery.model.viewModel.UserViewModel;

import java.util.UUID;

public interface UserService {

    void initializeUsersAndRoles();

    boolean isUserNameFree(String username);

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    UserViewModel userDetails(String user);

    void deleteUser(UUID id);

}
