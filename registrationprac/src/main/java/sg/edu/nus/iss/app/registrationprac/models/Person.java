package sg.edu.nus.iss.app.registrationprac.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Person {
    @NotNull(message="Name cannot be null")
    @Size(min=2,max=32,message = "Name must be inbetween of 2 and 32 chars")
    private String name;

    @Email(message = "Must be valid email")
    private String email;

    private Boolean isMarried;
    
    @Min(value=1, message="Age cannot be less than 1")
    @Max(value=100, message="Age cannot be more than 100")
    private Integer age;

    @Past(message="You cannot be born in the future")
    @NotNull(message ="Must enter DOB")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotEmpty(message ="Please get a life")
    @Size(min = 1,message ="Have atleast 1 hobby")
    private List<String> hobbies;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getIsMarried() {
        return isMarried;
    }
    public void setIsMarried(Boolean isMarried) {
        this.isMarried = isMarried;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public List<String> getHobbies() {
        return hobbies;
    }
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    
    
}
