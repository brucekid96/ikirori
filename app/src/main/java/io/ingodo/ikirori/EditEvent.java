package io.ingodo.ikirori;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import com.google.android.material.textfield.TextInputLayout;
import io.ingodo.ikirori.data.Event;
import io.ingodo.ikirori.data.EventDao;
import io.ingodo.ikirori.data.EventRepository;
import io.ingodo.ikirori.data.EventViewModel;
import java.util.Calendar;
import java.util.List;

public class EditEvent  extends AppCompatActivity {


  Toolbar mToolbar;
  private Button mStartCalendarButton;
  private Button mEndCalendarButton;
  private Button mUploadImageButton;
  private ImageView mEventImageView;
  private ImageView mCameraIconView;
  private TextInputLayout mTitleView;
  private TextInputLayout mDescriptionView;
  private TextInputLayout mPlace;
  private TextInputLayout mAdress;
  private TextInputLayout mCity;
  private TextView mTitleErrorTextview;
  private TextView mDescriptionErrorTextview;
  private TextView mEventImageUriErrorTextView;
  Spinner mSpinnerTicket;
  Spinner mSpinnerCategory;
  String SpinnerTicketSelected;

  private Calendar mStartCalendar;
  private Calendar mEndCalendar;
  private TextView pickstartdateError;
  private TextView pickenddateError;
  RadioGroup mRadioGroup;
  RadioButton mPublicRadioButton;
  RadioButton mPrivateRadioButton;
  Event mEvent;
  EventRepository mRepository;
  EventViewModel mEventViewModel;
  EventDao mEventDao;
  private boolean mHasObservers;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    setContentView(R.layout.edit_event);

    mToolbar = findViewById(R.id.toolbar_create_event);
    pickstartdateError = findViewById(R.id.pickstartdateError);
    pickenddateError = findViewById(R.id.pickendDateError);
    setSupportActionBar(mToolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);


     Intent intent = getIntent();
     mEvent = intent.getParcelableExtra(Event.EVENT_EXTRA);
//    mUploadImageButton = (Button) findViewById(R.id.uploadbutton);
    mCameraIconView = (ImageView) findViewById(R.id.iconview);
    mEventImageView = (ImageView) findViewById(R.id.imageviewsplash);
    mTitleView = findViewById(R.id.event_title_label_id);
    mDescriptionView = findViewById(R.id.event_description_label_id);
    mPlace = findViewById(R.id.place_label);
    mAdress = findViewById(R.id.venue_label);
    mCity = findViewById(R.id.city_label);
    mTitleErrorTextview = findViewById(R.id.title_error_textView);
    mDescriptionErrorTextview = findViewById(R.id.Description_error_textview);
    mEventImageUriErrorTextView = findViewById(R.id.EventImageUriErrorTextview);
    mStartCalendarButton = findViewById(R.id.create_event_startdate);
    mEndCalendarButton = findViewById(R.id.create_event_enddate);
    mSpinnerTicket= findViewById(R.id.spinner_ticket);
    mSpinnerCategory = findViewById(R.id.spinner_category);

   mEventViewModel = new EventViewModel(getApplication());


    mRadioGroup = findViewById(R.id.radioGroup);
    mPrivateRadioButton = findViewById(R.id.private_radioButton);
    mPublicRadioButton = findViewById(R.id.public_radioButton);

    mRepository = new EventRepository(getApplication());
//    mRepository.update(mEvent);
//    mEventImageView.setImageURI(mRepository.getEvent()));
//    mTitleView;
//    mDescriptionView.getEditText().setText(mEvent.getDescription());
//    mStartCalendarButton.setText(mEvent.getStartDate().toString());
//    mEndCalendarButton.setText(mEvent.getEndDate().toString());
//    mPlace.getEditText().setText(mEvent.getPlace());
//    mAdress.getEditText().setText(mEvent.getAddress());
//    mCity.getEditText().setText(mEvent.getCity());
    mSpinnerCategory.getAdapter();


//   if(mEvent.getPrivacy().contains("public")) {
//     mPublicRadioButton.isChecked();
//   } else {
//     mPrivateRadioButton.isChecked();
//   }








  }
}
