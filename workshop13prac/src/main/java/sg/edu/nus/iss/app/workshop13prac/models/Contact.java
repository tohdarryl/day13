package sg.edu.nus.iss.app.workshop13prac.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
    @NotNull(message="Must have a name")
    @Size(min=3, max=64, message="Name must be between 3 and 64 chars")
    private String name;

    @NotNull(message="Must have an email")
    @Email(message="Must be valid email")
    private String email;

    @NotNull(message="Must have a phone number")
    @Size(min=7,message="Atleast 7 digits")
    private String phoneNumber;

    @NotNull(message="Date of birth is mandatory")
    @Past(message="Cannot be born in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    
    //For Hex String
    private String id;

    @NotNull(message = "Age cannot be null")
    @Min(value = 10, message = "Age cannot be less than 10")
    @Max(value = 100, message = "Age cannot be more than 100")
    private int age;


    public Contact(){
        this.id = generateID(8);
    }

    private synchronized String generateID(int numOfChar){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < numOfChar){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString();
        //.substring(0,numOfChar);
    }

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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        int calculatedAge = 0;
        if(dateOfBirth!=null){
        calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
        this.age = calculatedAge;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
    
}
