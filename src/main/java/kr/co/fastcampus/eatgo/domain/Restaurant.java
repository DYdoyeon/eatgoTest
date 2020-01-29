package kr.co.fastcampus.eatgo.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Restaurant {
    private String name;
    private String address;

    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private List<MenuItem> menuItems=new ArrayList<MenuItem>();

    public void setId(long id)
    {
        this.id = id;
    }

    public Restaurant(String name, String address) {
        this.address = address;
        this.name = name;
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return name + " in "+address;
    }
    public String getAddress() {
        return address;
    }

    public Long getId(){
        return id;
    }
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public Restaurant() {
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }
}
