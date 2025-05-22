package com.matheus.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.matheus.domain.Event;
import com.matheus.domain.Subscription;
import com.matheus.dtos.EmailRequestDTO;
import com.matheus.dtos.EventRequestDTO;
import com.matheus.exceptions.EventFullException;
import com.matheus.exceptions.EventNotFoundException;
import com.matheus.repositories.EventRepository;
import com.matheus.repositories.SubscriptionRepository;

public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @Autowired
  private EmailService emailService;

  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  public List<Event> getUpComingEvents() {
    return eventRepository.findUpcomingEvents(LocalDateTime.now());

  }

  public Event createEvent(EventRequestDTO eventRequestDTO) {
    Event newEvent = new Event(eventRequestDTO);
    return eventRepository.save(newEvent);
  }

  private Boolean isEventFull(Event event) {
    return event.getRegisteredParticipants() >= event.getMaxParticipants();
  }

  public void registerParticipants(String eventId, String participantEmail) {
    Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

    if (isEventFull(event)) {
      throw new EventFullException();
    }

    Subscription subscription = new Subscription(event, participantEmail);
    subscriptionRepository.save(subscription);

    event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

    EmailRequestDTO emailRequestDTO = new EmailRequestDTO(participantEmail, "Confirmação de inscrição",
        "Você foi inscrito com sucesso!");

    emailService.sendEmail(emailRequestDTO);
  }

}
