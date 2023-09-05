package com.xmum.server.service;

import com.xmum.server.entity.Event;

import java.util.List;
import java.util.concurrent.Future;

public interface EventService {
    int createEvent(Event event);


    Future<List<Event>> getAllEvents();

}
