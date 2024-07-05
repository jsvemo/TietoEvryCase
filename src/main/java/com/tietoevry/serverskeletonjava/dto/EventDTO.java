package com.tietoevry.serverskeletonjava.dto;

import com.google.gson.Gson;
import com.tietoevry.serverskeletonjava.repository.model.model.Event;
import lombok.*;

import java.time.LocalDateTime;



@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
    static Gson gson = new Gson();
    private String socSecNum;
    private String eventType;
    private String eventValue;
    private Long sequenceNumber;
    private LocalDateTime timestamp;

    public static EventDTO entityToDto(Event event) {
        return EventDTO.builder()
                .socSecNum(event.getSocSecNum())
                .eventType(event.getEventType())
                .eventValue(event.getEventValue())
                .sequenceNumber(event.getSequenceNumber())
                .timestamp(event.getTimestamp())
                .build();
    }



    public static Event dtoToEntity(EventDTO eventDTO) {
        return Event.builder()
                .socSecNum(eventDTO.getSocSecNum())
                .eventType(eventDTO.getEventType())
                .eventValue(eventDTO.getEventValue())
                .sequenceNumber(eventDTO.getSequenceNumber())
                .timestamp(eventDTO.getTimestamp())
                .build();
    }
}