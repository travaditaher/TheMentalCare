package com.tmc.demo.web.controller.secure;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.tmc.demo.model.Therapist;
import com.tmc.demo.model.User;
import com.tmc.demo.repository.UserRepository;
import com.tmc.demo.service.AppointmentService;
import com.tmc.demo.web.dto.AppointmentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/patient/make-appointment")
public class PatientAppointmentController {

    UserRepository userRepository;
    AppointmentService appointmentService;

    public PatientAppointmentController(UserRepository userRepository, AppointmentService appointmentService) {
        this.userRepository = userRepository;
        this.appointmentService = appointmentService;
    }

    @ModelAttribute("appointmentDto")
    public AppointmentDto appointmentDto() {
        return new AppointmentDto();
    }

    @GetMapping
    public String showPatientDashPage(HttpServletRequest req, Model model) {
        
        // To show currently logged-in user's info on sidebar
        String loggedInUserMail = req.getUserPrincipal().getName();
        User user = userRepository.findByEmail(loggedInUserMail);
        String fullName = user.getFirstName() + " " + user.getLastName();
        model.addAttribute("fullname", fullName);

        // To add appointment model to appointment form
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPatientEmail(user.getEmail());
        model.addAttribute("appointmentDto", appointmentDto);

        // To retrieve all therapist from the database and show data on table
        List<Therapist> therapists = appointmentService.retrieveAllTherapists();
        model.addAttribute("therapists", therapists);

        return "/secure/patientDash";
    }

    @PostMapping
    public String makeAppointment(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto) {

        appointmentService.makeAppointment(appointmentDto);

        return "redirect:/patient/make-appointment";
    }
    
}
