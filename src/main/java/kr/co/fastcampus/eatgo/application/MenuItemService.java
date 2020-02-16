package kr.co.fastcampus.eatgo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;

@Service
public class MenuItemService{
	@Autowired
	private MenuItemRepository menuItemRepository;

	public void bulkUpdate(Long restaurantId,List<MenuItem> menuItems)  {
		for (MenuItem menuItem : menuItems){
			menuItem.setRestaurantId(restaurantId);
			menuItemRepository.save(menuItem);
		}
	}

	public MenuItemService(MenuItemRepository menuItemRepository){
		this.menuItemRepository = menuItemRepository;
	}


}