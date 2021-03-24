package pl.pancerro.backend.controller.panelAdminController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pancerro.backend.model.Charts;
import pl.pancerro.backend.model.Member;
import pl.pancerro.backend.model.message.MyEmail;
import pl.pancerro.backend.service.memberService.MemberService;

import javax.mail.MessagingException;
import java.util.List;

@RestController()
@RequestMapping("/admin/member")
public class MemberAdminController {
    private final MemberService memberService;

    public MemberAdminController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/all-member")
    public ResponseEntity<List<Member>> getMember(){
        return new ResponseEntity<>(memberService.getMemberList(), HttpStatus.OK);
    }
    @PutMapping("/edit-member")
    public ResponseEntity<?> editMember(@RequestBody Member member) {
        if(memberService.editMember(member)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete-member/{idMember}")
    public ResponseEntity<?> deleteMember(@PathVariable long idMember) {
        if (memberService.deleteMember(idMember)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody MyEmail myEmail) throws MessagingException {
        memberService.sendEmail(myEmail);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-charts-age")
    public ResponseEntity<List<Charts>> getChartsAge(){
        return new ResponseEntity<>(memberService.getChartsMemberInAge(), HttpStatus.OK);
    }
    @GetMapping("/get-charts-sex")
    public ResponseEntity<List<Charts>> getChartsSex(){
        return new ResponseEntity<>(memberService.getSexInMember(), HttpStatus.OK);
    }
    @GetMapping("/get-charts-education")
    public ResponseEntity<List<Charts>> getChartsEducation(){
        return new ResponseEntity<>(memberService.getEducationInMember(), HttpStatus.OK);
    }
    @GetMapping("/get-charts-area")
    public ResponseEntity<List<Charts>> getChartsArea(){
        return new ResponseEntity<>(memberService.getAreaInMember(), HttpStatus.OK);
    }
}
