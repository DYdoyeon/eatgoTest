package kr.co.fastcampus.eatgo.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    private String name;
    private String address;

 //   private String regionName;
//    private String categoryName;
  //  private String category;


    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private List<MenuItem> menuItems=new ArrayList<MenuItem>();


    public Restaurant(String name, String address) {
        this.address = address;
        this.name = name;
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;

    }


    public String getInformation() {
        return name + " in "+address;
    }
    public void addMenuItem(MenuItem menuItem) {

        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>();
        for(MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }
    public void updateInformation(String name, String address){
        this.name=name;
        this.address=address;
        
    }
}
