package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/restaurants")
    public List<Restaurant> list(){
       // List<Restaurant> restaurants = new ArrayList<>();

     //   restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));
      final List<Restaurant> restaurants = restaurantService.getRestaurants();
      return restaurants;
   }

   @GetMapping("/restaurants/{id}")
   public Restaurant detail(@PathVariable("id") final Long id)

   {
      final Restaurant restaurant = restaurantService.getRestaurant(id);
      // 기본 정보 + 메뉴 정보

      // Restaurant restaurant = restaurantService.getRestaurantById(id);
      // Restaurant restaurant = restaurantRepository.findById(id);
      // List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);

      // restaurant.setMenuItems(menuItems);
      return restaurant;
   }

   @PostMapping("/restaurants")
   public ResponseEntity<?> create(@RequestBody final Restaurant resource) throws URISyntaxException {
    
      final String name = resource.getName();
      final String address = resource.getAddress();
      Restaurant restaurant = Restaurant.builder().id(1234L).name(name).address(address).build();
     // final Restaurant restaurant = new Restaurant(name, address);
      restaurantService.addRestaurant(restaurant);
      final URI location = new URI("/restaurants/" + restaurant.getId());
      return ResponseEntity.created(location).body("{}");
   }

   @PatchMapping("/restaurants/{id}")
   public String update(@PathVariable("id") final Long id, @RequestBody final Restaurant resource) {
      final String name = resource.getName();
      final String address = resource.getAddress();

      restaurantService.updateRestaurant(id,name, address);
      return "{}";
   }

   
}
