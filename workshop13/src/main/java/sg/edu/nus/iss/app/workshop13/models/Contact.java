package sg.edu.nus.iss.app.workshop13.models;

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
    @NotNull(message="Name cannot be null")
    @Size(min = 3, max = 64, message="Must be between 3 and 64 chars")
    private String name;

    @Email(message="Invalid Email Address")
    private String email;
    // Cannot apply @Size constraint to an Integer
    @Size(min = 7, message="Phone number must be at least 7 digit")
    private String phoneNumber;

    //For HEX text
    private String id;

    @Past(message="Cannot be born in the future")
    @NotNull(message="Date of birth is mandatory")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "User's age cannot be null.")
    @Min(value = 10, message = "Must be above 10 years old")
    @Max(value = 100, message = "Must be below 100 years old")
    private int age;
    
    public Contact(){
        this.id=generateID(8);

    }

    public Contact(String name, String email, String phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id=generateID(8);
        
    }

    public Contact(String id, String name, String email, String phoneNumber, LocalDate dob){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dob;
    }

    //synchronized is a queuing instance; takes 1 caller at a time, not simultaneous, to AVOID Duplication.
    private synchronized String generateID(int numOfChar){
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while(strBuilder.length() < numOfChar){
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString()
                .substring(0,numOfChar);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        int calculatedAge = 0;
        if ((dateOfBirth != null)) {
            calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
        this.age = calculatedAge;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }  

    

        
}
