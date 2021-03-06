package kr.co.fastcampus.eatgo.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.eatgo.application.MenuItemService;
import kr.co.fastcampus.eatgo.domain.MenuItem;

import javax.validation.Valid;


@RestController
public class MenuItemController{
    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody List<MenuItem> menuItems){
     //   List<MenuItem> menuItems = new ArrayList<>();
        menuItemService.bulkUpdate(restaurantId,menuItems);
        return "";
    }


}