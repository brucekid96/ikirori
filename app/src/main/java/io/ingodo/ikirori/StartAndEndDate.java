package io.ingodo.ikirori;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import io.ingodo.ikirori.data.Event;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StartAndEndDate extends AppCompatActivity {
  private static final String TAG = StartAndEndDate.class.getSimpleName();

  private Toolbar mToolbar;
  private Button mStartCalendarButton;
  private Button mEndCalendarButton;


  Event mEvent;
  private Calendar mStartCalendar;
  private Calendar mEndCalendar;
  private TextView pickstartdateError;
  private TextView pickenddateError;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.start_and_end_date);

    mToolbar = findViewById(R.id.toolbar_event_date);
    pickstartdateError = findViewById(R.id.pickstartdateError);
    pickenddateError = findViewById(R.id.pickendDateError);

    setSupportActionBar(mToolbar);

    Intent intent = getIntent();
    mEvent = intent.getParcelableExtra(Event.EVENT_EXTRA);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    FloatingActionButton fab = (FloatingActionButton)
        findViewById(R.id.floatingActionButtonCalendar);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if(mStartCalendar==null) {
          pickstartdateError.setVisibility(view.VISIBLE);
        } else if (mEndCalendar==null) {
          pickenddateError.setVisibility(View.VISIBLE);
        }
        else
        if (mStartCalendar!=null && mEndCalendar!=null){

          pickstartdateError.setVisibility(view.INVISIBLE);
          pickenddateError.setVisibility(View.INVISIBLE);
          mEvent.setStartDate(mStartCalendar.getTime());
          mEvent.setEndDate(mEndCalendar.getTime());
          Log.d(TAG, mEvent.toString());
          Intent intent = new Intent(StartAndEndDate.this, Location.class);
          intent.putExtra(Event.EVENT_EXTRA, mEvent);
          startActivity(intent);
        }
      }
    });

    final DateAndTimePicker startDateAndTimePicker = new DateAndTimePicker(this,
        new OnDateAndTimeSetListener() {
          @Override
          public void onDateAndTimeSet(Calendar calendar) {
            mStartCalendar = calendar;
            mStartCalendarButton.setText(calendarToString(calendar));
          }
        });

    mStartCalendarButton = findViewById(R.id.create_event_startdate);
    mStartCalendarButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startDateAndTimePicker.show();
        pickstartdateError.setVisibility(View.INVISIBLE);
      }
    });

    final DateAndTimePicker endDateAndTimePicker = new DateAndTimePicker(this,
        new OnDateAndTimeSetListener() {
          @Override
          public void onDateAndTimeSet(Calendar calendar) {
            mEndCalendar = calendar;
            mEndCalendarButton.setText(calendarToString(calendar));
          }
        });
    mEndCalendarButton = findViewById(R.id.create_event_enddate);
    mEndCalendarButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        endDateAndTimePicker.show();
        pickenddateError.setVisibility(View.INVISIBLE);
      }
    });

  }


  private String calendarToString(Calendar calendar) {
    SimpleDateFormat dateFormatter = new SimpleDateFormat(
        "EEEE, MMM d yyyy   h:mm a");
    return dateFormatter.format(calendar.getTime());
  }

  private class DateAndTimePicker
      implements DatePickerDialog.OnDateSetListener,
      TimePickerDialog.OnTimeSetListener {

    private Calendar mCalendar;
    private Context mContext;
    private OnDateAndTimeSetListener mListener;
    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;


    public DateAndTimePicker(Context context, OnDateAndTimeSetListener listener) {
      mContext = context;
      mListener = listener;
      mCalendar = Calendar.getInstance();

      mDatePickerDialog = new DatePickerDialog(mContext, this,
          mCalendar.get(Calendar.YEAR),
          mCalendar.get(Calendar.MONTH),
          mCalendar.get(Calendar.DAY_OF_MONTH));

      mTimePickerDialog = new TimePickerDialog(mContext, this,
          mCalendar.get(Calendar.HOUR_OF_DAY),
          mCalendar.get(Calendar.MINUTE),
          DateFormat.is24HourFormat(mContext));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
      mCalendar.set(year, month, dayOfMonth);
      mTimePickerDialog.show();
    }

    public void show() {
      mDatePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
      mCalendar.set(Calendar.MINUTE, minute);
      mListener.onDateAndTimeSet(mCalendar);
    }
  }

  private interface OnDateAndTimeSetListener {
    void onDateAndTimeSet(Calendar calendar);
  }
}

