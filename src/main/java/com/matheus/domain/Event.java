package com.matheus.domain;

import com.matheus.dtos.EventRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "event")
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private int maxParticipants;
  private int registeredParticipants;
  private String date;
  private String title;
  private String description;

  public Event(EventRequestDTO eventRequestDTO) {
    this.date = eventRequestDTO.date();
    this.maxParticipants = eventRequestDTO.maxParticipants();
    this.registeredParticipants = eventRequestDTO.registeredParticipants();
    this.title = eventRequestDTO.title();
    this.description = eventRequestDTO.description();
  }
}
