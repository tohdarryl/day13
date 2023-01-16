package sg.edu.nus.iss.app.httpsessionprac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.app.httpsessionprac.models.Cart;
import sg.edu.nus.iss.app.httpsessionprac.models.Item;

@Controller
@RequestMapping(path="/cart")
public class CartController {

    @GetMapping
    public String getCart(Model model, HttpSession session){
        //Get cart from session
        Cart cart = (Cart)session.getAttribute("cart");
        //If new session, cart should be null, hence initialize and create new cart
        if(cart==null){
            cart = new Cart();
            //add the cart object to the session
            session.setAttribute("cart", cart);
        }
        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping
    public String postData(Model model, HttpSession session, @Valid Item item, BindingResult bResult){
        System.out.println("After Validation");
        //retrieve item from session. Should not be null since cart has been created in the GET /cart handler
        Cart cart = (Cart)session.getAttribute("cart");

        if(bResult.hasErrors()){
            return "cart";
        }
        //add item from the form to the cart
        cart.addItemToCart(item);
        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);
        return "cart";
    }
    
}
