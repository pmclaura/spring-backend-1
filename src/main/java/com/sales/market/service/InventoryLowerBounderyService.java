package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.RoleType;
import com.sales.market.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class InventoryLowerBounderyService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    public void sendEmailToSupervisor(List<ItemInventory> listInventories) throws MessagingException {
        List<User> listUsers= userService.findUsersByRol(RoleType.SUPERVISOR.getType());
        String subject = "Items debajo el minimo de Stock, ";
        String text = getMessage(listInventories);
        for(User user : listUsers) {
            emailService.sendMessage(user, subject+user.getEmployee().getFirstName(), text);
        }
    }

    public String getMessage (List<ItemInventory> listInventories){
        String text = "";
        for (ItemInventory itemInventory : listInventories){
            text += " Item: " +itemInventory.getItem().getName() +
                " Stock: "+itemInventory.getStockQuantity().toString()+
                " Limite minimo de stock: "+itemInventory.getLowerBoundThreshold()+"\n\n";
        }
        return text;
    }
}
