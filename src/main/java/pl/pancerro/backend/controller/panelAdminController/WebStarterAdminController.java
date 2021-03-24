package pl.pancerro.backend.controller.panelAdminController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pancerro.backend.model.message.WebStarterDesc;
import pl.pancerro.backend.service.webStarterDescService.WebStarterDescService;

@RestController()
@RequestMapping("/admin/web-starter")
public class WebStarterAdminController {
    private final WebStarterDescService webStarterDescService;

    public WebStarterAdminController(WebStarterDescService webStarterDescService) {
        this.webStarterDescService = webStarterDescService;
    }

    @GetMapping("/get-web-starter")
    public ResponseEntity<WebStarterDesc> getWebStarterDesc() {
        return new ResponseEntity<>(webStarterDescService.getWebStarterDesc(), HttpStatus.OK);
    }
    @PostMapping("/add-web-starter")
    public ResponseEntity<?> addWebStarterDesc(@RequestBody WebStarterDesc webStarterDesc) {
        webStarterDescService.addWebStarterDesc(webStarterDesc);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/edit-web-starter")
    public ResponseEntity<?> editWebStarterDesc(@RequestBody WebStarterDesc webStarterDesc) {
        if(webStarterDescService.editWebStarterDesc(webStarterDesc)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete-web-starter")
    public ResponseEntity<?> deleteWebStarterDesc() {
        if (webStarterDescService.deleteWebStarterDesc()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
