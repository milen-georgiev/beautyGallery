package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.UserEntity;
import Project.beautyGallery.model.entity.UserRoleEntity;
import Project.beautyGallery.model.entity.enums.RoleNameEnum;
import Project.beautyGallery.model.serviceModel.UserRegistrationServiceModel;
import Project.beautyGallery.model.viewModel.UserViewModel;
import Project.beautyGallery.repository.UserRepository;
import Project.beautyGallery.repository.UserRoleRepository;
import Project.beautyGallery.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final BeautyGalleryUserServiceImpl beautyGalleryUserService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           UserRoleRepository userRoleRepository, ModelMapper modelMapper, BeautyGalleryUserServiceImpl beautyGalleryUserService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.beautyGalleryUserService = beautyGalleryUserService;
    }


    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    @Override
    public boolean isUserNameFree(String username) {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .isEmpty();
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserRoleEntity userRole = userRoleRepository.findByName(RoleNameEnum.USERS);

        UserEntity newUser = new UserEntity();

        newUser
                .setUsername(userRegistrationServiceModel.getUsername())
                .setFirstName(userRegistrationServiceModel.getFirstName())
                .setLastName(userRegistrationServiceModel.getLastName())
                .setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()))
                .setEmail(userRegistrationServiceModel.getEmail())
                .setAge(userRegistrationServiceModel.getAge())
                .setCreated(LocalDate.now())
                .setRoles(Set.of(userRole))
                .setTown(userRegistrationServiceModel.getTown());

        userRepository.save(newUser);

        UserDetails principal = beautyGalleryUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserViewModel userDetails(String user) {
        UserEntity userEntity = userRepository.findByUsername(user).orElseThrow();

        UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);

        return userViewModel;
    }

    public void initializeUsers() {
        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = userRoleRepository.findByName(RoleNameEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByName(RoleNameEnum.USERS);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setAge(20)
                    .setEmail("admin@Adminov.com")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setCreated(LocalDate.now())
                    .setPassword(passwordEncoder.encode("test"))
                    .setTown("test");

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity user = new UserEntity();
            user
                    .setUsername("Ivan")
                    .setAge(20)
                    .setEmail("Ivan@Ivanow.com")
                    .setFirstName("Ivan")
                    .setLastName("Ivanov")
                    .setCreated(LocalDate.now())
                    .setPassword(passwordEncoder.encode("test"))
                    .setTown("test");

            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }

    }

    private void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setName(RoleNameEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setName(RoleNameEnum.USERS);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }
}
