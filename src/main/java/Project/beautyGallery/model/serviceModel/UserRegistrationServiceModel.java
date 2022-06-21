package Project.beautyGallery.model.serviceModel;

public class UserRegistrationServiceModel {


    private String username;
    private String password;
    private String email;
    private String town;
    private Integer age;

    public UserRegistrationServiceModel() {
    }

    public String getUsername() {
        return username != null ?
                username.trim() :
                null;
    }

    public UserRegistrationServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationServiceModel setEmail(String email) {
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

    public UserRegistrationServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }
}
