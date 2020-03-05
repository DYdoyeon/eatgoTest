package kr.co.fastcampus.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue
    @Setter
    private Long id;


    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> menuItems;
    //두번째 어노테이션은 menuItems가 Null일 때는 안보이도록 하는거야


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
