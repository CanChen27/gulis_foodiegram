package main.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PubliJOINUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userid;
    private String name;
    private String userimage;
    private String text;
    private String image;
    private String localization;
    private Float media;
    private Integer numerototalval;
}
