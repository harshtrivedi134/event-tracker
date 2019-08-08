package com.eventprocessor.eventprocessor.eventprocessormodel;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "event")
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Event {

    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String userId;

    @Column(columnDefinition="TIMESTAMP")
    @UpdateTimestamp
    private ZonedDateTime eventTime;

    @Column
    private String eventType;

    @Column
    private String deviceId;

    @Column
    private String agent;
}
