package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;


    @Test
    public void list() throws Exception {
         List<Restaurant> restaurants = new ArrayList<>();

        //given(restaurantService.getRestaurants()).willReturn(restaurants);

        restaurants.add(Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")

                ))
                .andExpect(content().string(
                        containsString( "\"name\":\"Bob zip")

                ));


    }
    @Test
    public void detailWithExisted() throws Exception{
      //  final Restaurant restaurant1 = new Restaurant(1004L,"Bob Zip","Seoul");
        Restaurant restaurant1 = Restaurant.builder().id(1004L).name("Bob Zip").address("Seoul").build();
        
        restaurant1.setMenuItems(Arrays.asList());

        final Restaurant restaurant2 = Restaurant.builder()
                .id(2020L)
                .name("Cyber Food")
                .address("Seoul")
                .build();
     //   restaurant2.addMenuItem(new MenuItem("Kimchi"));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);

        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);


        mvc.perform(get("/restaurants/1004")).
                andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")

                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob Zip")
                ))
             //   .andExpect(content().string(containsString("Kimchi")))
        ;;

        mvc.perform(get("/restaurants/2020")).
                andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")

                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food")

                ));;


    }

    @Test
    public void detailWithNotExisted() throws Exception{
        given(restaurantService.getRestaurant(100L)).willThrow( new RestaurantNotFoundException(100L));
        mvc.perform(get("/restaurants/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }


    @Test
    public void createWithValidData() throws Exception {
       
        given(restaurantService.addRestaurant(any())).will(invocation ->{
           Restaurant restaurant = invocation.getArgument(0);
           return   Restaurant.builder()
                .id(1004L)
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .build();

        });
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\"name\" : \"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1004"))
                .andExpect(content().string("{}"));
        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void createWithInValidData() throws Exception {
     
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\"\" : \"\",\"\":\"\"}"))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void updateWithValidData() throws Exception {
            mvc.perform(patch("/restaurants/1004")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"JOKER Bar\",\"address\":\"Busan\"}"))
            .andExpect(status().isOk());

            verify(restaurantService).updateRestaurant(1004L,"JOKER Bar","Busan");
    }

    @Test
    public void updateWithInValidData() throws Exception {
            mvc.perform(patch("/restaurants/1004")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"\": \"\",\"\":\"\"}"))
            .andExpect(status().isBadRequest());
        }
       // public void updateWithoutDate() throw Exception{} 처럼 만들어서 테스트하면 좋아
}