package school.sptech.loginormyou2up.service.usuario.autenticacao.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "YOU2UP", version = "v1"))
@SecurityScheme(
        name = "requiredAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer"
)
public class SwaggerConfig {
}
