package io.ingodo.ikirori.data;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import io.ingodo.ikirori.Live;
import java.util.List;
import java.util.UUID;


public class EventRepository {

  private EventDao mEventDao;
  private LiveData<List<Event>> mAllEvents;
  private LiveData<Event>mUpdateEvents;
  Event mEvent;


  public EventRepository(Context context) {
    EventRoomDatabase db = EventRoomDatabase.getDatabase(context);
    mEventDao = db.eventDao();;
    mAllEvents = mEventDao.getAllEvents();

//    mUpdateEvents = mEventDao.getEvent(mEvent.getId());
  }


   public LiveData<List<Event>> getAllEvents() {
    return mAllEvents;
  }
  public LiveData<Event> getEvent() {
    return mUpdateEvents;
  }


    public void insert(Event event) {
    new insertAsyncTask(mEventDao).execute(event);
  }
    private static class insertAsyncTask extends AsyncTask<Event, Void, Void> {

    private EventDao mAsyncTaskDao;

    insertAsyncTask(EventDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Event... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }

    public void delete(Event event)  {
    new deleteAsyncTask(mEventDao).execute(event);
  }
    private static class deleteAsyncTask extends AsyncTask<Event, Void, Void> {
    private EventDao mAsyncTaskDao;

    deleteAsyncTask(EventDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Event... params) {
      mAsyncTaskDao.delete(params[0]);
      return null;
    }
  }

  public void update(Event event)  {
    new updateAsyncTask(mEventDao).execute(event);
  }
  private static class updateAsyncTask extends AsyncTask<Event, Void, Void> {
    private EventDao mAsyncTaskDao;

    updateAsyncTask(EventDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Event... params) {
      mAsyncTaskDao.update(params[0]);
      return null;
    }
  }




  ;
}
