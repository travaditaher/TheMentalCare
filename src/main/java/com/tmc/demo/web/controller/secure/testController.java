package com.tmc.demo.web.controller.secure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
    
    @GetMapping("/patient/test")
    public String showPatientPage() {
        return "/secure/patient";
    }

    @GetMapping("/therapist/test")
    public String showTherapistPage() {
        return "/secure/therapist";
    }

}
