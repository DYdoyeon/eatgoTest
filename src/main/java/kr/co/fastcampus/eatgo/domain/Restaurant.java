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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    private String name;
    private String address;

    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @Transient
    private List<MenuItem> menuItems=new ArrayList<MenuItem>();


    public String getInformation() {
        return name + " in "+address;
    }


    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
        //this.menuItems = menuItems를 쓸 경우, menuItem이 변경되면 모두 변경되기 때문에 그럼녀 안됨.
    }
    public void updateInformation(String name, String address){
        this.name=name;
        this.address=address;
        
    }
}
