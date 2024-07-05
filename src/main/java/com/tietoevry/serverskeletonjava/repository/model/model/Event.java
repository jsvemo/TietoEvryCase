package com.tietoevry.serverskeletonjava.repository.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVENTS")
@Builder
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @Column(name = "sequence_number")
    private Long sequenceNumber;

    @Column(name = "social_security_number")
    private String socSecNum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "event_value")
    private String eventValue;
}