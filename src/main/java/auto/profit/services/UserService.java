//package auto.profit.services;
//
//import auto.profit.enums.Role;
//import auto.profit.models.User;
//import auto.profit.repositories.UserRepo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepo userRepo;
//
//
//    public boolean createUser(User user) {
//        String email = user.getEmail();
//        if (userRepo.findByEmail(email) != null) return false;
//        user.setActive(true);
//        user.getRoles().add(Role.ROLE_USER);
//        log.info("Saving new User with email: {}", email);
//        return true;
//    }
//}
