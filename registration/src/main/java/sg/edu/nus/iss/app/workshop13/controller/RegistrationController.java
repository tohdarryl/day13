package sg.edu.nus.iss.app.workshop13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.workshop13.models.Person;

@Controller
@RequestMapping(path="/register")
public class RegistrationController {

    @GetMapping
    public String getRegForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping
    public String postRegistration(@Valid Person person, BindingResult bResult, Model model){
        if(bResult.hasErrors()){
            // model.addAttribute("registration", person);
            return "register";
        }
        return "thankyou";
    }



    
}
