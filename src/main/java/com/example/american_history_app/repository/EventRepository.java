package com.example.american_history_app.repository;

import com.example.american_history_app.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
//    Collection<Event> findAllByOrderByEventYearAscEventMonthAsc();

    Collection<Event> findAllByOrderByEventDate();
}