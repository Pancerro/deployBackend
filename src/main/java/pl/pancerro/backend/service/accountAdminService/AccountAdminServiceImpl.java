package pl.pancerro.backend.service.accountAdminService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pancerro.backend.model.User;
import pl.pancerro.backend.repository.UserRepo;

import java.util.Optional;
@Service
public class AccountAdminServiceImpl implements  AccountAdminService {
    private final UserRepo userRepo;
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AccountAdminServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean changePassword(String newPassword) {
        Optional<User> userOptional = Optional.ofNullable(userRepo.getUserByUsername("Admin"));
        if(userOptional.isPresent()){
            User updateUser = userOptional.get();
            updateUser.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(updateUser);
            return true;
        } else return false;
    }
}
