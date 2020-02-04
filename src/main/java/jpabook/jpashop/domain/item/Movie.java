package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") // 부모클래스인 Item의 DiscriminatorColumn의 value .
@Getter @Setter
public class Movie extends Item{
    private String director;
    private String actor;
}
