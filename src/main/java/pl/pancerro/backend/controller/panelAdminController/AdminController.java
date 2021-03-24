package pl.pancerro.backend.controller.panelAdminController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pancerro.backend.configuration.AuthenticationBean;
import pl.pancerro.backend.service.accountAdminService.AccountAdminService;

@RestController()
@RequestMapping("/admin/")
public class AdminController {
    private final AccountAdminService accountAdminService;

    public AdminController(AccountAdminService accountAdminService) {
        this.accountAdminService = accountAdminService;
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody String newPassword) {
        if( accountAdminService.changePassword(newPassword)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }
}
