package sg.edu.nus.iss.app.workshop13.models;

import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<Item> contents = new LinkedList<>();

    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }

    public void addItemToCart(Item item){
        this.contents.add(item);
    }
    
}
