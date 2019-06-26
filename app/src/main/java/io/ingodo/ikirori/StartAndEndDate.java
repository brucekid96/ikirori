package io.ingodo.ikirori;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StartAndEndDate extends AppCompatActivity {
    private Button mStartCalendarButton;
    private Button mEndCalendarButton;

    private Calendar mStartCalendar;
    private Calendar mEndCalendar;

    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_and_end_date);

        mToolbar = findViewById(R.id.toolbar_event_date);
        setSupportActionBar(mToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById
                (R.id.floatingActionButtonCalendar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(view.getContext()  ,
                        Location.class));
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
            }
        });
    }


    private String calendarToString(Calendar calendar) {
//        java.text.DateFormat deviceDateFormat = android.text.format.DateFormat.getDateFormat(this);
//        return deviceDateFormat.format(calendar.getTime());


//
            SimpleDateFormat dateFormatter = new SimpleDateFormat(
                    "EEE, MMM d, yyyy   h:mm a");
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
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
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

