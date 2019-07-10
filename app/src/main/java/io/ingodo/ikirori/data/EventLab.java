package io.ingodo.ikirori.data;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventLab {

  private static EventLab EventLab;

  private List<Event> mEvents;

  public static EventLab get(Context context) {
    if (EventLab == null) {
      EventLab = new EventLab(context);
    }
    return EventLab;
  }
  private EventLab(Context context) {
    mEvents = new ArrayList<>();
  }

  public List<Event> getEvents() {
    return mEvents;
  }

  public Event getEvent(UUID id) {
    for (Event event : mEvents) {
      if (event.getId().equals(id)) {
        return event;
      }
    }
    return null;
  }

}
