package sg.edu.nus.iss.app.workshop13.controller;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import sg.edu.nus.iss.app.workshop13.models.Contact;
import sg.edu.nus.iss.app.workshop13.util.Contacts;

@Controller
@RequestMapping(path = "/addressbook")
public class AddressBookController {
    //Task 3
    @Autowired
    Contacts ctcz;

    @Autowired
    ApplicationArguments appArgs;

    private static final String DEFAULT_DATADIR = "/Users/darryl/src/day13/workshop13/data";
    //mvn spring-boot:run -Dspring-boot.run.arguments=--dataDir=
    @GetMapping
    public String showAddrBookForm(Model model){
        model.addAttribute("contact", new Contact());
        return "addressbook";
    }

    @GetMapping(path="{contactId}")
        public String retrieveContactBy(@PathVariable String contactId, Model model){
            ctcz.getContactById(model, contactId, appArgs, DEFAULT_DATADIR);
            return "result";
        }

    @PostMapping
    public String saveContact(@Valid Contact contact, BindingResult bindResult, Model model){
        //if bindResult has error, return to addressbook.html View
        if (bindResult.hasErrors())
            return "addressbook";
        
            //adding a custom error
        // if(!checkAgeInBetween10and100(contact.getDateOfBirth())){
        //     ObjectError err = new ObjectError("globalError"
        //             , "Age must be between 10 and 100 years old");
        //     bindResult.addError(err);
        //     return "addressbook";
        // }

        ctcz.saveContact(contact, model, appArgs, DEFAULT_DATADIR);
        return "result";
    }
    //Task 5
    @GetMapping(path = "/list")
    public String getAllContacts(Model model) {
        ctcz.getAllContactInURI(model, appArgs, DEFAULT_DATADIR);
        return "contacts";
    }


    // private boolean checkAgeInBetween10and100(LocalDate dob){
    //     int calculatedAge = 0;
    //     if(null != dob){
    //         calculatedAge = Period.between(dob, LocalDate.now()).getYears();
    //     }

    //     if(calculatedAge >= 10 && calculatedAge <=100){
    //         return true;
    //     }

    //     return false;
    
    // }
    
}
