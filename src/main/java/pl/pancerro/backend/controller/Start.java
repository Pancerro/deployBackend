package pl.pancerro.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pancerro.backend.model.*;
import pl.pancerro.backend.model.enumPack.Area;
import pl.pancerro.backend.model.enumPack.DegreeOfStudy;
import pl.pancerro.backend.model.enumPack.Education;
import pl.pancerro.backend.model.enumPack.Voivodeship;
import pl.pancerro.backend.model.member.*;
import pl.pancerro.backend.model.message.AcceptEmail;
import pl.pancerro.backend.model.message.WebStarterDesc;
import pl.pancerro.backend.repository.*;
import pl.pancerro.backend.repository.memberRepo.*;
import pl.pancerro.backend.service.formService.FormService;
import pl.pancerro.backend.service.memberService.MemberService;
import pl.pancerro.backend.service.memberService.basicInformationService.BasicInformationService;

import javax.mail.MessagingException;

@Component
public class Start {
    private AdditionalInformationRepo additionalInformationRepo;
    private BasicInformationRepo basicInformationRepo;
    private ContactDetailsRepo contactDetailsRepo;
    private DirectionOfEducationRepo directionOfEducationRepo;
    private LaborMarketStatusRepo laborMarketStatusRepo;
    private StatementRepo statementRepo;
    private MemberRepo memberRepo;
    private ProjectRepo projectRepo;
    private SaveMemberToProjectRepo saveMemberToProjectRepo;
    private UniversityCoursesRepo universityCoursesRepo;
    private UniversityDepartmentsRepo universityDepartmentsRepo;
    private MemberService memberService;
    private final BasicInformationService basicInformationService;
    private final FormService formService;
    private final AcceptEmailRepo acceptEmailRepo;
    private final WebStarterDescRepo webStarterDescRepo;
    private final UserRepo userRepo;

    @Autowired
    public Start(AdditionalInformationRepo additionalInformationRepo, BasicInformationRepo basicInformationRepo, ContactDetailsRepo contactDetailsRepo, DirectionOfEducationRepo directionOfEducationRepo, LaborMarketStatusRepo laborMarketStatusRepo, StatementRepo statementRepo, MemberRepo memberRepo, ProjectRepo projectRepo, SaveMemberToProjectRepo saveMemberToProjectRepo, UniversityCoursesRepo universityCoursesRepo, UniversityDepartmentsRepo universityDepartmentsRepo, MemberService memberService, BasicInformationService basicInformationService, FormService formService, AcceptEmailRepo acceptEmailRepo, WebStarterDescRepo webStarterDescRepo, UserRepo userRepo) {
        this.additionalInformationRepo = additionalInformationRepo;
        this.basicInformationRepo = basicInformationRepo;
        this.contactDetailsRepo = contactDetailsRepo;
        this.directionOfEducationRepo = directionOfEducationRepo;
        this.laborMarketStatusRepo = laborMarketStatusRepo;
        this.statementRepo = statementRepo;
        this.memberRepo = memberRepo;
        this.projectRepo = projectRepo;
        this.saveMemberToProjectRepo = saveMemberToProjectRepo;
        this.universityCoursesRepo = universityCoursesRepo;
        this.universityDepartmentsRepo = universityDepartmentsRepo;
        this.memberService = memberService;
        this.basicInformationService = basicInformationService;
        this.formService = formService;
        this.acceptEmailRepo = acceptEmailRepo;
        this.webStarterDescRepo = webStarterDescRepo;
        this.userRepo = userRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws MessagingException {

        //dane do uzytkownika
        AdditionalInformation additionalInformation = new AdditionalInformation(false,false,"","","","","","",false,"","","","","","");
        BasicInformation basicInformation = new BasicInformation("Blazej","Karabin","97102704691","21154","23.01.1996", Education.Wyzsze);
        ContactDetails contactDetails = new ContactDetails("Metalowa","2b","Gdynia","Gdynia","81-146","Gdynia","Gdynia", Voivodeship.Pomorskie, Area.Miejski,"506-553-353","pancerro@gmail.com");
        DirectionOfEducation directionOfEducation = new DirectionOfEducation("Akademia Pana Kleksa","Informatyczny","Informatyka","Proramowanie", DegreeOfStudy.PierwszyStopien,"1997");
        LaborMarketStatus laborMarketStatus = new LaborMarketStatus(true,true,true,true,false,false,false,false,false,false,false,false,false,"","",false,false,false);
        Statement statement = new Statement("Nie","Nie",true);
        //uzytkownik
        Member member = new Member();
        member.setAdditionalInformation(additionalInformation);
        member.setBasicInformation(basicInformation);
        member.setContactDetails(contactDetails);
        member.setDirectionOfEducation(directionOfEducation);
        member.setLaborMarketStatus(laborMarketStatus);
        member.setStatement(statement);

        //przypisanie uzytkownika do projektu
        Project project = new Project("Java","Kurs z Javy","01-12-2020","20-12-2020",true);
        SaveMemberToProject saveMemberToProject = new SaveMemberToProject();
        saveMemberToProject.setMember(member);
        saveMemberToProject.setProject(project);
        //wydzialy z kierunkami
        UniversityDepartments universityDepartments = new UniversityDepartments("Wydzial Mechaniczno Elektroniczny","WME");
        UniversityDepartments universityDepartments1 = new UniversityDepartments("Wydzial Nagwigacji i Uzbrojenia","WNIOU");

        UniversityCourses universityCourses = new UniversityCourses("Informatyka");
        universityCourses.setUniversityDepartments(universityDepartments);
        //zapisy do bazy danych
        universityDepartmentsRepo.save(universityDepartments);
        universityDepartmentsRepo.save(universityDepartments1);
        universityCoursesRepo.save(universityCourses);
        projectRepo.save(project);
        basicInformationRepo.save(basicInformation);
        additionalInformationRepo.save(additionalInformation);
        contactDetailsRepo.save(contactDetails);
        directionOfEducationRepo.save(directionOfEducation);
        laborMarketStatusRepo.save(laborMarketStatus);
        statementRepo.save(statement);
        memberRepo.save(member);
        saveMemberToProjectRepo.save(saveMemberToProject);
        Member member1 = new Member(
                new BasicInformation("Adrian","Lawecki","97102704691","21154","23.11.1996", Education.Brak),
                new ContactDetails("Metalowa","2b","Gdynia","Gdynia","81-146","Gdynia","Gdynia", Voivodeship.Pomorskie, Area.Miejski,"506-553-353","pancerro@gmail.com"),
                new LaborMarketStatus(true,true,true,true,false,false,false,false,false,false,false,false,false,"","",false,false,false),
                new DirectionOfEducation("Akademia Pana Kleksa","Informatyczny","Informatyka","Proramowanie", DegreeOfStudy.PierwszyStopien,"1997"),
                new AdditionalInformation(false,false,"","","","","","",false,"","","","","",""),
                new Statement("nie","nie",true));
        SaveMemberToProject saveMemberToProject1 = new SaveMemberToProject();
        saveMemberToProject1.setMember(member1);
        saveMemberToProject1.setProject(project);
        basicInformationRepo.save(member1.getBasicInformation());
        additionalInformationRepo.save(member1.getAdditionalInformation());
        contactDetailsRepo.save(member1.getContactDetails());
        directionOfEducationRepo.save(member1.getDirectionOfEducation());
        laborMarketStatusRepo.save(member1.getLaborMarketStatus());
        statementRepo.save(member1.getStatement());
        memberRepo.save(member1);
        saveMemberToProjectRepo.save(saveMemberToProject1);
        BasicInformation newBasic = new BasicInformation();
        newBasic = member.getBasicInformation();
        newBasic.setSurname("Michal");
        newBasic.setName("HE HE HEHE");
        member.setBasicInformation(newBasic);
        //memberService.editMember(member);
        //memberService.deleteMember(1);

        //dane do uzytkownika
        AdditionalInformation additionalInformation2 = new AdditionalInformation(false,false,"","","","","","",false,"","","","","","");
        BasicInformation basicInformation2 = new BasicInformation("Andrzej","Madry","97102704691","221221","31.01.2001", Education.Podstawowe);
        ContactDetails contactDetails2 = new ContactDetails("Zelazna","22b","Gdansk","Gdynia","81-146","Gdynia","Gdynia", Voivodeship.Lodzkie, Area.Miejski,"507-553-353","panzerro@gmail.com");
        DirectionOfEducation directionOfEducation2 = new DirectionOfEducation("Akademia Pana Kleksa","Informatyczny","Informatyka","Programowanie", DegreeOfStudy.TrzeciStopien_Doktoranckie,"2007");
        LaborMarketStatus laborMarketStatus2 = new LaborMarketStatus(true,true,true,true,false,false,false,false,false,false,false,false,false,"","",false,false,true);
        Statement statement2 = new Statement("tak","nie",true);
        //uzytkownik
        Member member2 = new Member();
        member2.setAdditionalInformation(additionalInformation2);
        member2.setBasicInformation(basicInformation2);
        member2.setContactDetails(contactDetails2);
        member2.setDirectionOfEducation(directionOfEducation2);
        member2.setLaborMarketStatus(laborMarketStatus2);
        member2.setStatement(statement2);

        //formService.sendForm(member2,1);
        String htmlText="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en-GB\">\n" +
                "<head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  <title>UE Projekt</title>\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                "\n" +
                "  <style type=\"text/css\">\n" +
                "    a[x-apple-data-detectors] {color: inherit !important;}\n" +
                "  </style>\n" +
                "\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0;\">\n" +
                "  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "    <tr>\n" +
                "      <td style=\"padding: 20px 0 30px 0;\">\n" +
                "\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse; border: 1px solid #cccccc;\">\n" +
                "  <tr>\n" +
                "    <td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0;\">\n" +
                "      <img src=\"https://img.freepik.com/darmowe-wektory/flaga-unii-europejskiej_1284-43013.jpg?size=626&ext=jpg\" alt=\"EU flag.\" width=\"300\" height=\"230\" style=\"display: block;\" />\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n" +
                "        <tr>\n" +
                "          <td style=\"color: #153643; font-family: Arial, sans-serif;\">\n" +
                "            <h1 style=\"font-size: 24px; margin: 0;\">Hej, uzytkowniku</h1>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 20px 0 30px 0;\">\n" +
                "            <p style=\"margin: 0;\">Gratuluje, właśnie otrzymałes/otrzymałas potwierdzenie na to ze twój formularz został wysłany i do nas dotarł. Uprzejmnie prosimy o cierpliwość i czekanie na dalsze informacje.</p>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n" +
                "              <tr>\n" +
                "                <td width=\"260\" valign=\"top\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n" +
                "                    <tr>\n" +
                "                      <td>\n" +
                "                        <img src=\"https://www.amw.gdynia.pl/images/AMW/Logotypy/AMW/amw_logo.png\" alt=\"\" width=\"100%\" height=\"240\" style=\"display: block;\" />\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 25px 0 0 0;\">\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "                <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>\n" +
                "                <td width=\"260\" valign=\"top\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n" +
                "                    <tr>\n" +
                "                      <td>\n" +
                "                        <img src=\"https://www.amw.gdynia.pl/images/AMW/Logotypy/AMW/amw_logo.png\" alt=\"\" width=\"100%\" height=\"240\" style=\"display: block;\" />\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px;\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n" +
                "        <tr>\n" +
                "          <td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px; text-align:center\">\n" +
                "           \t<h1>Pozdrawiamy <br/> UE_PROJECT</h1>\n" +
                "          </td>\n" +
                " \n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
        AcceptEmail acceptEmail = new AcceptEmail("Potwierdzam", htmlText,true);
        acceptEmailRepo.save(acceptEmail);

        WebStarterDesc webStarterDesc = new WebStarterDesc("<div style=\"color:gray;\">O co tu chodzi</div>","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer interdum, urna accumsan convallis placerat, ante sapien dictum tortor, id bibendum lectus magna eget nulla. Sed ultrices mattis sem vitae iaculis. Ut ac odio eget velit vehicula eleifend. Duis elementum sagittis vestibulum. Integer a consequat nunc, id iaculis lectus. Vivamus pulvinar ligula suscipit, pretium urna vel, vehicula lectus. Fusce lobortis varius nunc in varius. Curabitur tincidunt varius justo, eu volutpat neque sodales ac. Integer non tincidunt massa, non mattis mauris. Proin sit amet efficitur magna, efficitur commodo augue. Fusce pharetra vulputate neque, quis ultrices nunc. Vestibulum egestas non velit vitae aliquam. Duis vitae purus non purus sollicitudin sodales. Nam feugiat ipsum sed dictum semper. Nunc molestie hendrerit dolor, eget ullamcorper quam auctor id. Aenean commodo dignissim ligula, et dignissim odio hendrerit sit amet. Proin metus mi, pellentesque hendrerit dignissim ac, sagittis in arcu. Donec cursus molestie neque, commodo laoreet augue cursus vel. Vivamus et urna lectus. Pellentesque varius ut nulla id viverra. Sed in pharetra ligula. Nam nec imperdiet erat, eu efficitur tellus. Nunc est sem, dapibus at diam ut, gravida vestibulum risus. Aliquam sed dictum justo. Integer nec justo semper, varius diam non, malesuada metus. Aenean bibendum orci tellus, sit amet ornare lorem gravida eget. Quisque ut augue nec odio ultrices pharetra. Phasellus facilisis, magna eget euismod iaculis, arcu neque dignissim ex, vel commodo lorem leo in lacus. Sed tincidunt bibendum auctor. Sed euismod enim quis enim fermentum, vitae luctus nisl porttitor. Aliquam ac ornare leo. Etiam congue nibh iaculis, ullamcorper risus vel, pellentesque tortor. Integer ullamcorper ligula nec ligula imperdiet, non lobortis lorem vestibulum. Nam pellentesque eget mauris ornare molestie.",false);
        webStarterDescRepo.save(webStarterDesc);

        User user = new User("Admin",passwordEncoder.encode("Admin123"),"ROLE_ADMIN",true);
        userRepo.save(user);
        System.out.println("DZIALA");

    }
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

}
