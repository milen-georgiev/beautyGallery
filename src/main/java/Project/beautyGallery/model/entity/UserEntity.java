package Project.beautyGallery.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @NotNull
    private String town;
    @NotNull
    private Integer age;
    @Column(nullable = false)
    private LocalDate created;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<PicturesEntity> myPictures;
    @OneToMany(mappedBy = "published",cascade = CascadeType.ALL)
    private List<ArticlesEntity> myArticles;
    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
    private List<VideoEntity> myVideo;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public UserEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<PicturesEntity> getMyPictures() {
        return myPictures;
    }

    public UserEntity setMyPictures(List<PicturesEntity> myPictures) {
        this.myPictures = myPictures;
        return this;
    }

    public List<ArticlesEntity> getMyArticles() {
        return myArticles;
    }

    public UserEntity setMyArticles(List<ArticlesEntity> myArticles) {
        this.myArticles = myArticles;
        return this;
    }

    public List<VideoEntity> getMyVideo() {
        return myVideo;
    }

    public UserEntity setMyVideo(List<VideoEntity> myVideo) {
        this.myVideo = myVideo;
        return this;
    }
}
