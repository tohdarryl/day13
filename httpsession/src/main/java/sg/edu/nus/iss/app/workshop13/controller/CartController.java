package sg.edu.nus.iss.app.workshop13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.app.workshop13.models.Cart;
import sg.edu.nus.iss.app.workshop13.models.Item;

@Controller
@RequestMapping(path= "/cart")
public class CartController {

    @GetMapping
    public String getCart(Model model,HttpSession session){
        //Try to get cart object from the session
        Cart cart = (Cart)session.getAttribute("cart");
        //If new session, cart should be null, hence initialize and create new cart
        if(null==cart){
            cart = new Cart();
            //add the cart object to the session. Cart will remain in the session until session is destroyed    
            session.setAttribute("cart", cart);
        }

        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping
    public String postData(Model model, HttpSession session, @Valid Item item, BindingResult bindResult){
        System.out.println("After Validation!");
        Cart cart = (Cart)session.getAttribute("cart");

        if(bindResult.hasErrors()){
            // model.addAttribute("item", item);
            // model.addAttribute("cart", cart);
            return "cart";
        }

        cart.addItemToCart(item);
        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);
        return "cart";
    }
    
    
}
