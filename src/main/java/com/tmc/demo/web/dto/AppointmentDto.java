package com.tmc.demo.web.dto;

public class AppointmentDto {

    private String patientEmail;
    private String therapistEmail;
    private String appointmentSubject;
    private String appointmentMessage;

    public AppointmentDto() {
        
    }

    public AppointmentDto(String patientEmail, String therapistEmail, String appointmentSubject, String appointmentMessage) {
        this.patientEmail = patientEmail;
        this.therapistEmail = therapistEmail;
        this.appointmentSubject = appointmentSubject;
        this.appointmentMessage = appointmentMessage;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getTherapistEmail() {
        return therapistEmail;
    }

    public void setTherapistEmail(String therapistEmail) {
        this.therapistEmail = therapistEmail;
    }

    public String getAppointmentSubject() {
        return appointmentSubject;
    }

    public void setAppointmentSubject(String appointmentSubject) {
        this.appointmentSubject = appointmentSubject;
    }

    public String getAppointmentMessage() {
        return appointmentMessage;
    }

    public void setAppointmentMessage(String appointmentMessage) {
        this.appointmentMessage = appointmentMessage;
    }
    
}
