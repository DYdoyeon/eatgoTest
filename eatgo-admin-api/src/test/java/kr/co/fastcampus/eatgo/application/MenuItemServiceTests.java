package kr.co.fastcampus.eatgo.application;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;

public class MenuItemServiceTests {
    private MenuItemService menuItemService;
    @Mock
    private MenuItemRepository menuItemRepository ;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
       menuItemService = new MenuItemService(menuItemRepository);

    }
    
    @Test
    public void bulkUpdate(){
        final List<MenuItem> menuItems = new ArrayList<MenuItem>();
  
       menuItems.add(MenuItem.builder().name("kimchi").build());
       menuItems.add(MenuItem.builder().name("Gokbab").build());

       menuItemService.bulkUpdate(1L,menuItems); 
        verify(menuItemRepository,times(2)).save(any());
    }
    @Test
    public void getMenuItems(){

        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(MenuItem.builder().name("Kimchi").build());

        given(menuItemRepository.findAllByRestaurantId(1004L))
                .willReturn(mockMenuItems);

        List<MenuItem> menuItems = menuItemService.getMenuItems(1004L);
        MenuItem menuItem = menuItems.get(0);
        assertThat(menuItem.getName(),is("Kimchi"));

    }

}