package Project.beautyGallery.model.viewModel;

import Project.beautyGallery.model.entity.UserRoleEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserViewModel {

    private UUID id;
    private String username;
    private Integer age;
    private String email;
    private String town;
    private LocalDate created;
    private Set<UserRoleEntity> roles = new HashSet<>();

    public UserViewModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public UserViewModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserViewModel setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}