package co.edu.ucentral.grupo2.baselogistica.Auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController{

    @PostMapping(value = "login")
    public String login() {
      return "Login working";
    }

    @PostMapping(value = "register")
    public String register() {
      return "Register working";
    }
}