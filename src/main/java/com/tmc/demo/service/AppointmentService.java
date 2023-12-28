package com.tmc.demo.service;

import java.util.List;

import com.tmc.demo.model.Therapist;
import com.tmc.demo.web.dto.AppointmentDto;

public interface AppointmentService {

    public void makeAppointment(AppointmentDto appointmentDto);
    public List<Therapist> retrieveAllTherapists();
    
}
