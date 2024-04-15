package people.spheres.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import people.spheres.demo2.dto.EmailDTO;
import people.spheres.demo2.services.EmailGeneratorService;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1.0")
public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;

    @Autowired
    public EmailGeneratorController(EmailGeneratorService emailGeneratorService) {
        this.emailGeneratorService = emailGeneratorService;
    }

    @GetMapping("/emails-generator")
    public EmailDTO generateEmail(@RequestParam String[] inputs,
                                  @RequestParam String expression) {

        return emailGeneratorService.generateEmail(inputs, expression);
    }

}
