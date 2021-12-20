package fer.gis.backend.model.enitity.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="VerificationTokens")
@Data
@RequiredArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String token;
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
    @NotNull
    private Date expiryDate;

    public VerificationToken(String token, User user, Date expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }
}
