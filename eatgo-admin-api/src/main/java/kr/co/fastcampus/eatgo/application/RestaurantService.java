package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import kr.co.fastcampus.eatgo.interfaces.RestaurantNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;


    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
   }


    public Restaurant getRestaurant(Long id){

        Restaurant restaurant = restaurantRepository.findById(id)
                    .orElseThrow(() -> new RestaurantNotFoundException(id));

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
