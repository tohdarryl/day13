package sg.edu.nus.iss.app.workshop13prac.controller;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import sg.edu.nus.iss.app.workshop13prac.models.Contact;
import sg.edu.nus.iss.app.workshop13prac.util.Contacts;

@Controller
@RequestMapping(path="addressbook")
public class AddressBookController {
    @Autowired
    Contacts ctcz;

    @Autowired
    ApplicationArguments appArgs;

    private static final String DEFAULT_DATADIR = "/Users/darryl/src/day13/workshop13prac/data";

    //mvn spring-boot:run -Dspring-boot.run.arguments=--dataDir=/Users/darryl/src/day13/workshop13prac/data
    @GetMapping
    public String getForm(Model model){
        model.addAttribute("contact", new Contact());
        return "addressbook";
    }

    //task 4
    @GetMapping(path="{contactId}")
    public String retrieveContactBy(@PathVariable String contactId, Model model){
        ctcz.getContactById(model, contactId, appArgs, DEFAULT_DATADIR);
        return "result";
    }

    @PostMapping
    public String saveContact(@Valid Contact contact, BindingResult bResult, Model model){
        if(bResult.hasErrors()){
            return "addressbook";
        }

        // if(!checkIfBetween10and100(contact.getDateOfBirth())){
        //     //Adding personalised error for dob
        //     ObjectError err = new ObjectError("dateOfBirth", "Age must be between 10 and 100");
        //     bResult.addError(err);
        //     return "addressbook";
        // }

        ctcz.saveContact(contact, model, appArgs, DEFAULT_DATADIR);
        return "result";
    }

    //Task 5
    @GetMapping(path="/list")
    public String getAllContacts(Model model){
        ctcz.getAllContactInURI(model, appArgs, DEFAULT_DATADIR);
        return "contacts";
    }

    // private boolean checkIfBetween10and100(LocalDate dob){
    //     int calculatedAge = 0;
    //     if(null!=dob){
    //         calculatedAge = Period.between(dob, LocalDate.now()).getYears();
    //     }
    //     if(calculatedAge>=10 && calculatedAge<=100){
    //         return true;
    //     }

    //     return false;

    // }   

}
