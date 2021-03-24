package pl.pancerro.backend.controller.panelAdminController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pancerro.backend.model.message.AcceptEmail;
import pl.pancerro.backend.service.myEmailService.AcceptEmailService;


@RestController()
@RequestMapping("/admin/mail")
public class MailController {
    private final AcceptEmailService acceptEmailService;

    public MailController(AcceptEmailService acceptEmailService) {
        this.acceptEmailService = acceptEmailService;
    }

    @GetMapping("/get-accept-email")
    public ResponseEntity<AcceptEmail> getAcceptEmail() {
        return new ResponseEntity<>(acceptEmailService.getAcceptEmail(), HttpStatus.OK);
    }
    @PostMapping("/add-accept-email")
    public ResponseEntity<?> addAcceptEmail(@RequestBody AcceptEmail acceptEmail) {
        acceptEmailService.addAcceptEmail(acceptEmail);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/edit-accept-email")
    public ResponseEntity<?> editAcceptEmail(@RequestBody AcceptEmail acceptEmail) {
        if(acceptEmailService.editAcceptEmail(acceptEmail)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete-accept-email")
    public ResponseEntity<?> deleteAcceptEmail() {
        if (acceptEmailService.deleteAcceptEmail()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
