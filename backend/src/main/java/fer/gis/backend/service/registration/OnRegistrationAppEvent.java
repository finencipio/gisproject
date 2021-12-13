package fer.gis.backend.service.registration;

import fer.gis.backend.model.enitity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class OnRegistrationAppEvent extends ApplicationEvent {

    private String appUrl;
    private User user;

    public OnRegistrationAppEvent(String appUrl, User user) {
        super(user);
        this.appUrl = appUrl;
        this.user = user;
    }
}
