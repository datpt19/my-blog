package unicorns.backend.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unicorns.backend.exception.ApiException;
import unicorns.backend.util.AuthProvider;
import unicorns.backend.util.AuthoritiesConstants;
import unicorns.backend.entity.Authority;
import unicorns.backend.entity.User;
import unicorns.backend.dto.UserDto;
import unicorns.backend.repository.AuthorityRepository;
import unicorns.backend.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kim Keumtae
 */
@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerAccount(UserDto.Create userDto) {
        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new ApiException("Email Already exists.", HttpStatus.BAD_REQUEST);
                });

        User user = this.createUser(userDto.getEmail().toLowerCase(), userDto.getPassword(), userDto.getUserName());
        return user;
    }

    public User createUser(String email, String password, String userName) {
        User newUser = new User();
        Optional<Authority> authority = authorityRepository.findById(AuthoritiesConstants.USER);
        Set<Authority> authorities = new HashSet<>();
        newUser.setPassword( passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setUserName(userName);
        newUser.setProvider(AuthProvider.local);
        authority.ifPresent(auth -> authorities.add(auth));
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public void updateUser(Long id, String email, String name, boolean activated) {
        userRepository.findOneById(id).ifPresent(user -> {
            user.setEmail(email);
            log.debug("Changed Information for User: {}", user);
        });
    }

    public Page<User> findAllUser(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    public void deleteUser(Long userId) {
        userRepository.findOneById(userId).ifPresent(user -> {
            userRepository.deleteById(userId);
        });
    }
}