package pl.pancerro.backend.controller.panelAdminController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pancerro.backend.model.Charts;
import pl.pancerro.backend.model.UniversityCourses;
import pl.pancerro.backend.model.UniversityDepartments;
import pl.pancerro.backend.service.univeristyService.universityCoursesService.UniversityCoursesService;
import pl.pancerro.backend.service.univeristyService.universityDepartmentsService.UniversityDepartmentsService;

import java.util.List;
@RestController()
@RequestMapping("/admin/university")
public class UniversityAdminController {
    private final UniversityDepartmentsService universityDepartmentsService;
    private final UniversityCoursesService universityCoursesService;

    public UniversityAdminController(UniversityDepartmentsService universityDepartmentsService, UniversityCoursesService universityCoursesService) {
        this.universityDepartmentsService = universityDepartmentsService;
        this.universityCoursesService = universityCoursesService;
    }

    @PostMapping("/add-university-department")
    public ResponseEntity<?> addUniversityDepartment(@RequestBody UniversityDepartments universityDepartments) {
        if(universityDepartmentsService.addUniversityDepartments(universityDepartments)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PutMapping("/edit-university-department")
    public ResponseEntity<?> editUniversityDepartment(@RequestBody UniversityDepartments universityDepartments) {
        if(universityDepartmentsService.editUniversityDepartments(universityDepartments)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete-university-department/{idDepartment}")
    public ResponseEntity<?> deleteUniversityDepartment(@PathVariable long idDepartment) {
        if (universityDepartmentsService.deleteUniversityDepartments(idDepartment)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("/add-university-courses")
    public ResponseEntity<?> addUniversityCourses(@RequestBody UniversityCourses universityCourses) {
        if(universityCoursesService.addUniversityCourses(universityCourses)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PutMapping("/edit-university-courses")
    public ResponseEntity<?> editUniversityCourses(@RequestBody UniversityCourses universityCourses) {
        if(universityCoursesService.editUniversityCourses(universityCourses)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete-university-courses/{idCourses}")
    public ResponseEntity<?> deleteUniversityCourses(@PathVariable long idCourses) {
        if (universityCoursesService.deleteUniversityCourses(idCourses)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/get-charts-courses")
    public ResponseEntity<List<Charts>> getChartsCountCoursesInDepartment() {
        return new ResponseEntity<>(universityCoursesService.getChartsCoursesInDepartments(), HttpStatus.OK);
    }
}
