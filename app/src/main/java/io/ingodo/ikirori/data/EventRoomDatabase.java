package io.ingodo.ikirori.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import io.ingodo.ikirori.Converters;

@TypeConverters({Converters.class})
@Database(entities = {Event.class}, version = 1)
public abstract class EventRoomDatabase extends RoomDatabase {
  public abstract EventDao eventDao();

  private static volatile EventRoomDatabase INSTANCE;

  static EventRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (EventRoomDatabase.class) {
        if (INSTANCE == null) {
          // Create database here
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              EventRoomDatabase.class, "event_database")

              .build();
        }
      }
    }


    return INSTANCE;

  }


}
