package com.example.todoist.controller;

import com.example.todoist.serviceImplementer.GoogleCalenderService;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/calender")
public class GoogleVerificationController {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    @Autowired
    GoogleCalenderService googleCalenderService;

    @GetMapping("/login/{username}")
    public ResponseEntity<String> calendarLogin(@PathVariable(name = "username") String username) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleCalenderService.getCredentials(HTTP_TRANSPORT, username))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return new ResponseEntity<String>("Calendar Login success", HttpStatus.OK);
    }

    @GetMapping("/getUpcomingEvents/{username}")
    public ResponseEntity<String> getUpcomingEvents(@PathVariable(name = "username") String username) throws IOException, GeneralSecurityException {
        GoogleCalenderService.getUserEvents(username);
        return new ResponseEntity<String>("google-site-verification: googlec4994c3d3ef026f1.html", HttpStatus.OK);
    }
}