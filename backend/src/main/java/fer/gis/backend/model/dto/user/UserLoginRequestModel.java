package fer.gis.backend.model.dto.user;

import lombok.Data;

@Data
public class UserLoginRequestModel {

    private String username;
    private String password;
}
