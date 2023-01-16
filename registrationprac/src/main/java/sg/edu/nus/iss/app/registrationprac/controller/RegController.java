package sg.edu.nus.iss.app.registrationprac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.registrationprac.models.Person;

@Controller
@RequestMapping(path="/register")
public class RegController {

    @GetMapping
    public String getForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    //@Valid, validates the data captured from the form by the model
    //@BindingResult contains the validation results
    @PostMapping
    public String postReg(@Valid Person person, BindingResult bResult, Model model){
        if(bResult.hasErrors()){
            return "register";
        }
        return "thankyou";
    }
    
}
