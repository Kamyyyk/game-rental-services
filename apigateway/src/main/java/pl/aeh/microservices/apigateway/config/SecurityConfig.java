package pl.aeh.microservices.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, Converter<Jwt, Mono<AbstractAuthenticationToken>> authenticationConverter) throws Exception {
        http.authorizeExchange(exchange -> exchange
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/inventory/**").hasRole("USER")
                        .anyExchange().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(authenticationConverter)));

        http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    ReactiveJwtAuthenticationConverter jwtAuthenticationConverter(Converter<Jwt, Flux<GrantedAuthority>> authoritiesConverter) {
        final var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return jwtAuthenticationConverter;
    }

    interface ReactiveAuthoritiesConverter extends Converter<Jwt, Flux<GrantedAuthority>> {
    }

    @SuppressWarnings("unchecked")
    @Bean
    ReactiveAuthoritiesConverter authoritiesConverter() {
        return jwt -> {
            final List<String> allRoles = new ArrayList<>();
            final var realmAccess = (Map<String, Object>) jwt.getClaims().getOrDefault("realm_access", Map.of());
            allRoles.addAll((List<String>) realmAccess.getOrDefault("roles", List.of()));

            final var resourceAccess = (Map<String, Object>) jwt.getClaims().getOrDefault("resource_access", Map.of());
            for (final var clientId : resourceAccess.keySet()) {
                final var clientAccess = (Map<String, Object>) resourceAccess.getOrDefault(clientId, Map.of());
                allRoles.addAll((List<String>) clientAccess.getOrDefault("roles", List.of()));
            }

            allRoles.addAll((List<String>) jwt.getClaims().getOrDefault("roles", List.of()));

            return Flux.fromStream(allRoles.stream().map(r -> "ROLE_%s".formatted(r)).map(SimpleGrantedAuthority::new));
        };
    }
}