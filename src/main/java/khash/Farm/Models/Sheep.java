package khash.Farm.Models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Sheep implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long gender;
    Long weight;
    Long fatherId;
    Long motherId;
    Long farm;

    public Sheep(long gender, long weight, long fatherId, long motherId, long farm){
        this.gender = gender;
        this.weight = weight;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.farm = farm;
    }

    public Sheep() {

    }

    public void Print(){
        System.out.println("Sheep: " + id);
        System.out.println("gender: " + gender);
        System.out.println("weight: " + weight);
        System.out.println("fatherId: " + fatherId);
        System.out.println("motherId: " + motherId);
        System.out.println("farm: " + farm);
    }

}
