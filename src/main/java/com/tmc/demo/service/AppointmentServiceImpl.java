package com.tmc.demo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.tmc.demo.config.DatabaseConnectorConfiguration;
import com.tmc.demo.config.MailConfiguration;
import com.tmc.demo.model.Therapist;
import com.tmc.demo.repository.DatabaseConnector;
import com.tmc.demo.web.dto.AppointmentDto;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private MailConfiguration mailConfig;
    private DatabaseConnector dbConnector;
    private DatabaseConnectorConfiguration dbcConfig;

    public AppointmentServiceImpl(MailConfiguration mailConfig,
        DatabaseConnector dbConnector, DatabaseConnectorConfiguration dbcConfig) {
        this.mailConfig = mailConfig;
        this.dbConnector = dbConnector;
        this.dbcConfig = dbcConfig;
    }

    @Override
    public void makeAppointment(AppointmentDto appointmentDto) {

        String[] preAppointmentTextBody = new String[5];
        preAppointmentTextBody[0] = "Hello there! Hope you're doing good.\n";
        preAppointmentTextBody[1] = "Since you are subscribed to TheMentalCare, a client tried making an appointment with you.\n";
        preAppointmentTextBody[2] = "You can send an appointment acceptance email to the client fixing the schedule.\n\n";
        preAppointmentTextBody[3] = "Client's Email ID:- ";
        preAppointmentTextBody[4] = "Client's Message:- \n";

        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.host", mailConfig.getSMTP_HOST());
        prop.setProperty("mail.smtp.user", mailConfig.getGMAIL_USERNAME());
        prop.setProperty("mail.smtp.password", mailConfig.getGMAIL_PASSWORD());
        prop.setProperty("mail.smtp.port", mailConfig.getSMTP_PORT());
        prop.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfig.getGMAIL_USERNAME(),
                mailConfig.getGMAIL_PASSWORD());
            }
        });

        MimeMessage mailMessage = new MimeMessage(session);

        try {

            mailMessage.setFrom(new InternetAddress(mailConfig.getGMAIL_USERNAME()));
            mailMessage.setSubject(appointmentDto.getAppointmentSubject());
            // mailMessage.setText(appointmentDto.getAppointmentMessage());
            mailMessage.setText(preAppointmentTextBody[0] + preAppointmentTextBody[1]
            + preAppointmentTextBody[2] + preAppointmentTextBody[3] + appointmentDto.getPatientEmail()
            + "\n" + preAppointmentTextBody[4] + appointmentDto.getAppointmentMessage());

            mailMessage.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(appointmentDto.getTherapistEmail()));
            Transport transport = session.getTransport("smtp");
            transport.connect(mailConfig.getSMTP_HOST(), mailConfig.getGMAIL_USERNAME(),
                mailConfig.getGMAIL_PASSWORD());
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Therapist> retrieveAllTherapists() {
        
        ResultSet dbResult;
        List<Therapist> therapists = new ArrayList<>();

        dbConnector = new DatabaseConnector(dbcConfig);
        String query = "SELECT * FROM tmc2.retrieve_therapists;";
        dbResult = dbConnector.executeQuery(query);
        try {
            while (dbResult.next()) {
                Therapist therapist = new Therapist();
                therapist.setFullname(dbResult.getString("full_name"));
                therapist.setEmail(dbResult.getString("email"));
                therapists.add(therapist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dbResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        dbConnector.terminateConnection();
        return therapists;
    } 
    
}
