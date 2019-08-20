package io.ingodo.ikirori.data;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Update;
import java.util.List;

public class EventViewModel extends AndroidViewModel {

  private EventRepository mEventRepository;
  private LiveData<List<Event>> mAllEvents;
  private LiveData<Event> mUpdateEvents;

  public EventViewModel (Application application) {
    super(application);
    mEventRepository = new EventRepository(application);
    mAllEvents = mEventRepository.getAllEvents();
    mUpdateEvents  = mEventRepository.getEvent();


  }

  public LiveData<List<Event>> getAllEvents() { return mAllEvents; }
  public LiveData<Event> getEvent() { return mUpdateEvents; }
  public void insert(Event event) { mEventRepository.insert(event); }
//  public void deleteAll() {mEventRepository.deleteAll();}
  public void delete(Event event) {mEventRepository.delete(event);}
}
