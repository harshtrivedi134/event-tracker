package com.eventprocessor.eventprocessor.eventprocessorrepository;

import com.eventprocessor.eventprocessor.eventprocessormodel.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByUserId(String userId);
}
