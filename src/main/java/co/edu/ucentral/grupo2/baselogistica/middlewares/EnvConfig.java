package co.edu.ucentral.grupo2.baselogistica.middlewares;

import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class EnvConfig {

    private final Dotenv dotenv;

    public EnvConfig() {
        this.dotenv = Dotenv.load();
    }

    public String getSecretKey() {
        return dotenv.get("SECRET_KEY");
    }
}
