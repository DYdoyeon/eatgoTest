package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
     RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;


    public RestaurantService(RestaurantRepository restaurantRepository,MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }


    public Restaurant getRestaurant(Long id){

        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return  restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants=restaurantRepository.findAll();
        return restaurants;
        
    }

    public Restaurant addRestaurant(Restaurant restaurant) {

       // restaurant.setId(1234L);
       // return new Restaurant(1234L, restaurant.getName(), restaurant.getAddress());
        return restaurantRepository.save(restaurant);
    }

    @Transactional
	public Restaurant updateRestaurant(long id, String name, String address) {
        //TODO : update Restaurant...
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.updateInformation(name,address);

      
        return restaurant;
	}
}
