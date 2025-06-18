package org.vlad.music.store.app.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.vlad.music.store.app.enums.AlbumType;

//import javax.persistence.*;



import static java.lang.Math.random;


@Entity
@Data
@AllArgsConstructor
public class Album {
    @Id
//    @SequenceGenerator(
//            name = "album_id_sequence",
//            sequenceName = "album_id_sequence"
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "album_id_sequence"
//    )
    @Column(name = "id", unique = true)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String details;
    @NotNull
    private AlbumType type;
    @NotNull
    private int price;
    @NotNull
    private int stock;

    public Album() {
        this.id = (int)(random()*100);
    }
}
