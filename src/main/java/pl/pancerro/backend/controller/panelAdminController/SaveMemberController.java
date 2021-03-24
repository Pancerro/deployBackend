package pl.pancerro.backend.controller.panelAdminController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pancerro.backend.model.Charts;
import pl.pancerro.backend.model.SaveMemberToProject;
import pl.pancerro.backend.service.saveMemberToProjectService.SaveMemberToProjectService;

import java.util.List;
@RestController()
@RequestMapping("/admin/save")
public class SaveMemberController {
    private final SaveMemberToProjectService saveMemberToProjectService;

    public SaveMemberController(SaveMemberToProjectService saveMemberToProjectService) {
        this.saveMemberToProjectService = saveMemberToProjectService;
    }

    @GetMapping("/get-save-to-project")
    public ResponseEntity<List<SaveMemberToProject>> getSaveToProject() {
        return new ResponseEntity<>(saveMemberToProjectService.getSaveMemberToProjectList(), HttpStatus.OK);
    }
    @PostMapping("/add-save-to-project")
    public ResponseEntity<?> addSaveToProject(@RequestBody SaveMemberToProject saveMemberToProject) {
        saveMemberToProjectService.addSaveMemberToProject(saveMemberToProject);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/edit-save-to-project")
    public ResponseEntity<?> editSaveToProject(@RequestBody SaveMemberToProject saveMemberToProject) {
        if(saveMemberToProjectService.editSaveMemberToProject(saveMemberToProject)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete-save-to-project/{idSave}")
    public ResponseEntity<?> deleteSaveToProject(@PathVariable long idSave) {
        if (saveMemberToProjectService.deleteSaveMemberToProject(idSave)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/get-charts-member-in-project")
    public ResponseEntity<List<Charts>> getChartsCountMemberInProject() {
        return new ResponseEntity<>(saveMemberToProjectService.getChartsMemberInProject(), HttpStatus.OK);
    }
}
