package pl.pancerro.backend.controller.panelAdminController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pancerro.backend.model.Project;
import pl.pancerro.backend.service.projectService.ProjectService;

import java.util.List;
@RestController()
@RequestMapping("/admin/project")
public class ProjectAdminController {
    private final ProjectService projectService;

    public ProjectAdminController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/get-project")
    public ResponseEntity<List<Project>> getProject() {
        return new ResponseEntity<>(projectService.getProjectList(), HttpStatus.OK);
    }
    @PostMapping("/add-project")
    public ResponseEntity<?> addProject(@RequestBody Project project) {
        projectService.addProject(project);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/edit-project")
    public ResponseEntity<?> editProject(@RequestBody Project project) {
        System.out.println(project.isActive());
        if(projectService.editProject(project)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete-project/{idProject}")
    public ResponseEntity<?> deleteProject(@PathVariable long idProject) {
        if (projectService.deleteProject(idProject)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
