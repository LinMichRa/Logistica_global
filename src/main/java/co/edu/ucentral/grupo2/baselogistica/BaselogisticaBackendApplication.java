package co.edu.ucentral.grupo2.baselogistica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"co.edu.ucentral.grupo2.baselogistica","co.edu.ucentral.grupo2.baselogistica.useCase.IAuthCaseConductor",
"co.edu.ucentral.grupo2.baselogistica.controladores.Controconductor"})
public class BaselogisticaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaselogisticaBackendApplication.class, args);
	}

}
