package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.RoleType;
import com.sales.market.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class InventorySendEmailService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    public void sendEmailToSupervisorItemsLowerAndUpper(List<ItemInventory> listInventoriesLower, List<ItemInventory> listInventoriesUpper) throws MessagingException {
        List<User> listUsers= userService.findUsersByRol(RoleType.SUPERVISOR.getType());
        String subject = "Items con el minimo y maximo de Stock, ";
        String text = getMessage(listInventoriesLower, listInventoriesUpper);
        for(User user : listUsers) {
            emailService.sendMessage(user, subject+user.getEmployee().getFirstName(), text);
        }
    }

    public String getMessage (List<ItemInventory> listLower, List<ItemInventory> listUpper){
        String text = "";
        for (ItemInventory itemInventory : listLower){
            text += " Item: " +itemInventory.getItem().getName() +
                " Stock: "+itemInventory.getStockQuantity().toString()+
                " Limite Minimo de stock: "+itemInventory.getLowerBoundThreshold()+"\n\n";
        }
        text+="====================================\n\n";
        for (ItemInventory itemInventory : listUpper){
            text += " Item: " +itemInventory.getItem().getName() +
                    " Stock: "+itemInventory.getStockQuantity().toString()+
                    " Limite Maximo de stock: "+itemInventory.getUpperBoundThreshold()+"\n\n";
        }
        return text;
    }
}
