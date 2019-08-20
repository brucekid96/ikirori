package io.ingodo.ikirori.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import java.util.UUID;

@Dao
public interface EventDao {
  @Insert
  void insert(Event event);

  @Delete
  int delete(Event event);
  @Update
  int update(Event event);

  @Query("SELECT * from event_table")
  LiveData<List<Event>> getAllEvents();

  @Query("SELECT * from  event_table where id = :eventId ")
  LiveData<Event> getEvent(UUID eventId);




}