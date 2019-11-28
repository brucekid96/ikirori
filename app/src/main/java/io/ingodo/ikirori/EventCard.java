package io.ingodo.ikirori;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.ingodo.ikirori.data.Event;
import io.ingodo.ikirori.data.EventRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class EventCard extends ConstraintLayout {
  private String TAG = Event.class.getSimpleName();
  private Context mContext;
  private ImageView mImage;
  private TextView mTitle;
  private TextView mStartDate;
  private TextView mVenue;
  private TextView mCity;
  private TextView mPlace;
  private TextView more;
  private TextView mEditEvent;
  private ImageView mEventImageView;

  private Event mEvent;


  EventRepository mRepository;
  public EventCard(Context context) {
    super(context);

    mContext = context;

    LayoutInflater.from(mContext).inflate(R.layout.brouillon ,this);
    this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    mImage = findViewById(R.id.card_image);
    mTitle = findViewById(R.id.title_id);
    mStartDate = findViewById(R.id.calendar_id);
    mVenue = findViewById(R.id.venue_view_id);
    mCity = findViewById(R.id.city_view_id);
    mPlace = findViewById(R.id.place_view_id);
    more = findViewById(R.id.more_textview_id);
    mEditEvent = findViewById(R.id.edit_event);
  }


  public void bindEvent(Event event) {
    Log.d(TAG,event.toString());
    mEvent = event;
    mImage.setImageURI(mEvent.getEventImageUri());
    mTitle.setText(mEvent.getTitle());
    mStartDate.setText(calendarToString(mEvent.getStartDate()));
    mVenue.setText(mEvent.getAddress());
    mCity.setText(mEvent.getCity());
    mPlace.setText(mEvent.getPlace());

    mEditEvent.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mRepository = new EventRepository(getContext());
        Intent intent = new Intent(getContext(), EditEvent.class);
        intent.putExtra(Event.EVENT_EXTRA, mEvent);
        getContext().startActivity(intent);

      }
    });

    more.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showCardDialog();
      }
    });
  }



  private void showCardDialog() {
    mRepository = new EventRepository(mContext.getApplicationContext());
    AlertDialog.Builder cardDialog = new AlertDialog.Builder(mContext);
    cardDialog.setTitle("Select Action");
    String[] cardDialogItems = {
        "delete",
        };

    cardDialog.setItems(cardDialogItems,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            switch (which) {
              case 0:
                mRepository.delete(mEvent);

                break;
            }
          }
        });
    cardDialog.show();
  }


  private String calendarToString(Date date) {
    DateFormat dateFormatter = new SimpleDateFormat(
        "MMM d yyyy h:mm a");
    return dateFormatter.format(date.getTime());
  }

}






