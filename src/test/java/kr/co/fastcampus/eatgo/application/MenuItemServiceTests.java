package kr.co.fastcampus.eatgo.application;

import static org.mockito.ArgumentMatchers.any;
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
}