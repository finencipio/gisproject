package fer.gis.backend.web.rest;

import fer.gis.backend.model.dto.user.UserLoginReply;
import fer.gis.backend.model.dto.user.UserLoginRequestModel;
import fer.gis.backend.model.dto.user.UserRegistrationModel;
import fer.gis.backend.model.enitity.User;
import fer.gis.backend.service.security.AuthenticationService;
import fer.gis.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.OPTIONS}, allowCredentials = "true")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/users/register")
    public void createNewUser(@RequestBody @Valid UserRegistrationModel userRegistrationModel) {
        userService.registerNewUser(userRegistrationModel);
    }

    @PostMapping("/login")
    public UserLoginReply login(@RequestBody UserLoginRequestModel model) {
        return UserLoginReply.builder()
                .username(authenticationService.authenticate(model.getUsername(), model.getPassword()).getUsername())
                .token(authenticationService.createJWTToken(model.getUsername()))
                .build();
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
