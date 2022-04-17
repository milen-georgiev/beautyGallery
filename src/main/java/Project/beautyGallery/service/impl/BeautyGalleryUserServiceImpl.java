package Project.beautyGallery.service.impl;

import Project.beautyGallery.model.entity.UserEntity;
import Project.beautyGallery.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeautyGalleryUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public BeautyGalleryUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity =
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User with name" + username + "not found"));

        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        List<GrantedAuthority> authorities = userEntity
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
                .collect(Collectors.toList());

        return new BeautyGalleryUsers(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
