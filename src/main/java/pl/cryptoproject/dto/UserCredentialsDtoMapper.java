package pl.cryptoproject.dto;


import java.util.Set;
import java.util.stream.Collectors;

import pl.cryptoproject.dto.UserCredentialsDto;
import pl.cryptoproject.entity.User;
import pl.cryptoproject.entity.UserRole;

public class UserCredentialsDtoMapper {
    public static UserCredentialsDto map(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(email, password, roles);
    }
}