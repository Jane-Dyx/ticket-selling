package com.xmum.server.mapper;

import com.xmum.server.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface EventMapper {
    int addNewEvent(Event event);

    List<Event> getAllEvents();

    void updateTurnOver(Integer eid, BigDecimal increment);

    Event getEventById(Integer eid);
}
