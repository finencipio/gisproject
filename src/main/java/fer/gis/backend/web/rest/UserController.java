package fer.gis.backend.web.rest;

import fer.gis.backend.model.dto.user.UserLoginReply;
import fer.gis.backend.model.dto.user.UserLoginRequestModel;
import fer.gis.backend.model.dto.user.UserRegistrationModel;
import fer.gis.backend.model.enitity.user.User;
import fer.gis.backend.model.enitity.user.VerificationToken;
import fer.gis.backend.service.security.AuthenticationService;
import fer.gis.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.OPTIONS}, allowCredentials = "true")
@Slf4j
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/users/register")
    public void createNewUser(@RequestBody @Valid UserRegistrationModel userRegistrationModel) {
        userService.registerNewUser(userRegistrationModel);
    }

    @PostMapping("/login")
    public UserLoginReply login(@RequestBody UserLoginRequestModel model) {
        String token = authenticationService.createJWTToken(model.getUsername());
        UserLoginReply reply = UserLoginReply.builder()
                .username(authenticationService.authenticate(model.getUsername(), model.getPassword()).getUsername())
                .token(authenticationService.createJWTToken(model.getUsername()))
                .build();

        System.out.println(String.format("User %s logged with token %s", model.getUsername(), token));
        log.trace(String.format("User %s logged with token %s", model.getUsername(), token));

        return reply;
    }

    @GetMapping("/users/registrationConfirm")
    public void confirmRegistration(@RequestParam("token") String token) {
        userService.confirmRegistration(token);
    }


    @GetMapping("/users/changeName")
    public void changeName(@RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        User user = userService.findByUsername(getCurrentlyLoggedUsername());
        user.setFirstname(name);
        user.setLastname(lastName);
        userService.persistUser(user);
    }


    @DeleteMapping("/users/deleteUser")
    public void deleteUser() {
        userService.deleteUserAccount(getCurrentlyLoggedUsername());
    }

    private String getCurrentlyLoggedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
