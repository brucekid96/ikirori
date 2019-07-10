package io.ingodo.ikirori.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface EventDao {

  @Insert
  void insert(Event event);

  @Query("DELETE FROM event_table")
  void deleteAll();

  @Query("SELECT * from event_table INNER JOIN tickets_table ON event_table.event_id = tickets_table.event_id")
  List<Event> getAllEvents();

}
