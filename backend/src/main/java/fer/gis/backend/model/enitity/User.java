package fer.gis.backend.model.enitity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    @NotNull
    @Column(unique = true)
    @Getter
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    @Column(unique = true)
    private String email;
    private Boolean confirmed;
}
