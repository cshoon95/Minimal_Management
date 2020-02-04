package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 부모클래스인 Item의 DiscriminatorColumn의 value .
@Getter
@Setter
public class Album extends Item{
    private String artist;
    private String etc;
}
