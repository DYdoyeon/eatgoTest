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
    @Setter
    private Long id;

    @Transient
    private List<MenuItem> menuItems=new ArrayList<MenuItem>();


    public String getInformation() {
        return name + " in " + address;
    }


    public void setMenuItems(final List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
        //this.menuItems = menuItems는 쓰면 안됨
        //setMenuItems 를 쓰는 곳에서 해당 함수를 호출해서 값을 바꿀 경우, 이것은 참조만 하기 때문에 값이 모두 변경이 되버림

    }

    public void updateInformation(final String name, final String address) {
        this.name=name;
        this.address=address;
        
    }
}
