package com.matheus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.domain.Event;
import com.matheus.dtos.EventRequestDTO;
import com.matheus.dtos.SubscriptionRequestDTO;
import com.matheus.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @GetMapping
  public List<Event> getAllEvents() {
    return eventService.getAllEvents();
  }

  @GetMapping("/upcoming")
  public List<Event> getUpcomingEvents() {
    return eventService.getUpComingEvents();
  }

  @PostMapping
  public Event createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
    return eventService.createEvent(eventRequestDTO);
  }

  @PostMapping("/{eventId}/register")
  public void registerParticipant(@RequestBody String eventId,
      @RequestBody SubscriptionRequestDTO subscriptionRequestDTO) {
    eventService.registerParticipants(eventId, subscriptionRequestDTO.participantEmail());
  }

}
