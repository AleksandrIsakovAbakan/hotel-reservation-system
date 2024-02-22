package com.example.hotelreservationsystem.service;

import com.example.hotelreservationsystem.api.v1.request.UserRq;
import com.example.hotelreservationsystem.api.v1.response.UserRs;
import com.example.hotelreservationsystem.entity.Role;
import com.example.hotelreservationsystem.entity.User;
import com.example.hotelreservationsystem.exception.AlreadySuchNameException;
import com.example.hotelreservationsystem.exception.EntityNotFoundException;
import com.example.hotelreservationsystem.mappers.UserMapper;
import com.example.hotelreservationsystem.model.StatisticsEvent;
import com.example.hotelreservationsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final KafkaTemplate<String, StatisticsEvent> kafkaTemplate;

    @Value("${app.kafka.kafkaMessageTopic}")
    private String topicName;

    public User findByUsername(String username) {
        log.info("getByUsername: " + username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Username not found! " + username));
    }

    public UserRs getIdUser(Long id) {

        Optional<User> byId = userRepository.findById(id);

        if (byId.isPresent()) {
            log.info("getIdUser: " + byId.get().getUsername() + ", " + id);
            return UserMapper.INSTANCE.toDTO(byId.get());
        }

        log.info("User not found! id=" + id);
        throw new EntityNotFoundException("User not found!" + id);
    }

    public UserRs putIdUser(Long id, UserRq userRq) {

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));

            if (userRq.getUsername() != null) user.setUsername(userRq.getUsername());
            if (userRq.getPassword() != null) user.setPassword(passwordEncoder.encode(user.getPassword()));

            if (userRq.getRoleType() != null) {
                List<Role> rolesOld = user.getRoles();
                Role role = Role.from(userRq.getRoleType());
                user.setRoles(setRoles(rolesOld, role));
                role.setUser(user);
            }

            User user1 = userRepository.saveAndFlush(user);
            log.info("putIdUser: " + user1.getId() + ", " + userRq);

            return UserMapper.INSTANCE.toDTO(user1);
    }

    public List<Role> setRoles(List<Role> rolesOld, Role role) {

        if (!rolesOld.isEmpty()) {
            boolean flagRole = false;
            for (Role role1 : rolesOld) {
                if (role1.getAuthority().equals(role.getAuthority())) {
                    flagRole = true;
                    break;
                }
            }
            if (!flagRole) {
                rolesOld.add(role);
                return rolesOld;
            }
        } else {
            return Collections.singletonList(role);
        }
        return rolesOld;
    }

    public UserRs addUser(UserRq userRq) {

        if (testUsernameAndEmail(userRq.getUsername(), userRq.getEmail()))
            throw new AlreadySuchNameException("There is already such a news username: " + userRq.getUsername()
                    + " email: " + userRq.getEmail());

        var user = new User();
        user.setPassword(userRq.getPassword());
        user.setUsername(userRq.getUsername());
        user.setEmail(userRq.getEmail());
        var createUser = createNewAccount(user, Role.from(userRq.getRoleType()));

        sendMessage(createUser);

        return UserRs.builder()
                .id(createUser.getId())
                .username(createUser.getUsername())
                .password(createUser.getPassword())
                .email(createUser.getEmail())
                .roles(Collections.singletonList(userRq.getRoleType()))
                .build();

    }

    public User createNewAccount(User user, Role role) {

        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        role.setUser(user);

        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
        log.info("deleteUser: " + id);
    }

    public boolean testUsernameAndEmail(String username, String email){

        Optional<User> byUsername = userRepository.findByUsernameAndEmail(username, email);
        return byUsername.isPresent();
    }

    public void checkingIdUser(long userId) {

        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) throw new EntityNotFoundException("Not found userId: " + userId);
    }

    public void sendMessage(User user){
        StatisticsEvent statisticsEvent = new StatisticsEvent();
        statisticsEvent.setTypeEvent("addUser");
        statisticsEvent.setUserId(user.getId());
        log.info("StatisticsEvent addUser " + statisticsEvent);
        kafkaTemplate.send(topicName, statisticsEvent);
    }
}
