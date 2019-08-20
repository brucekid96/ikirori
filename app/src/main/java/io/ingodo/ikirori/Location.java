package io.ingodo.ikirori;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static io.ingodo.ikirori.data.Event.EVENT_EXTRA;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.google.android.material.textfield.TextInputLayout;
import io.ingodo.ikirori.data.Event;
import io.ingodo.ikirori.data.EventRepository;


public class Location extends AppCompatActivity {

  private static final String TAG = Location.class.getSimpleName();

  Toolbar mToolbar;
  private TextInputLayout mPlace;
  private TextInputLayout mCity;
  private TextInputLayout mAddress;
  private TextView mPlaceErrorTextview;
  private TextView mCityErrorTextview;
  private TextView mAddressErrorTextview;

  Event mEvent;
  EventRepository mRepository;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.location);



    Intent intent = getIntent();
    mEvent = intent.getParcelableExtra(Event.EVENT_EXTRA);

    mToolbar = findViewById(R.id.toolbar_event_date);

    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);



    FloatingActionButton fab = (FloatingActionButton) findViewById
        (R.id.floatingActionButtonLocation);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (mPlace.getEditText().length() == 0) {
          mPlaceErrorTextview.setVisibility(View.VISIBLE);
        } else if (mAddress.getEditText().length() == 0) {
          mAddressErrorTextview.setVisibility(View.VISIBLE);
        } else if (mCity.getEditText().length() == 0) {
          mCityErrorTextview.setVisibility(View.VISIBLE);
        } else
          if (mPlace.getEditText().length() != 0 & mAddress.getEditText().length() != 0
            & mCity.getEditText().length() != 0)
          {
          mEvent.setPlace(mPlace.getEditText().getText().toString());
          mEvent.setAddress(mAddress.getEditText().getText().toString());
          mEvent.setCity(mCity.getEditText().getText().toString());
          Log.d(TAG, mEvent.toString());
          Intent intent = new Intent(Location.this, Category.class);
          intent.putExtra(Event.EVENT_EXTRA, mEvent);
          startActivity(intent);

        }
      }
    });



    mPlace = (TextInputLayout) findViewById(R.id.place_label);
    mPlace.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {

      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
          mPlaceErrorTextview.setVisibility(View.INVISIBLE);
        }
      }
    });
    mAddress = (TextInputLayout) findViewById(R.id.venue_label);
    mAddress.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {

      @Override
      public void onFocusChange(View v, boolean hasFocus) {

        if(!hasFocus){
          mAddressErrorTextview.setVisibility(View.INVISIBLE);
        }
      }
    });
    mCity = (TextInputLayout) findViewById(R.id.city_label);
    mCity.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {

      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
          mCityErrorTextview.setVisibility(View.INVISIBLE);
        }
      }
    });
    mPlaceErrorTextview = findViewById(R.id.placeErrorTextview);
     mCityErrorTextview = findViewById(R.id.cityErrorTextview);
     mAddressErrorTextview = findViewById(R.id.adressErrorTextview);


  }


}
