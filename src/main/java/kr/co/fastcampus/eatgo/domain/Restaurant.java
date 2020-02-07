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


    public Restaurant(final String name, final String address) {
        this.address = address;
        this.name = name;
    }

    public Restaurant(final Long id, final String name, final String address) {
        this.id = id;
        this.name = name;
        this.address = address;

    }

    public String getInformation() {
        return name + " in " + address;
    }

    public void addMenuItem(final MenuItem menuItem) {

        menuItems.add(menuItem);
    }

    public void setMenuItems(final List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>();
        for (final MenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
    }

    public void updateInformation(final String name, final String address) {
        this.name=name;
        this.address=address;
        
    }
}
