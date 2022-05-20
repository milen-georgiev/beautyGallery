package Project.beautyGallery.service;

import Project.beautyGallery.model.serviceModel.UserRegistrationServiceModel;

public interface UserService {

    void initializeUsersAndRoles();

    boolean isUserNameFree(String username);

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);
}
