package io.ingodo.ikirori;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import io.ingodo.ikirori.data.Event;
import io.ingodo.ikirori.data.EventDao;
import io.ingodo.ikirori.data.EventRepository;
import io.ingodo.ikirori.data.EventViewModel;
import java.util.Calendar;
import java.util.List;

public class EditEvent  extends AppCompatActivity {
  private int GALLERY_REQUEST_CODE = 1;
  private int CAMERA_REQUEST_CODE = 2;
  private static final String TAG = EditEvent.class.getSimpleName();

  private Uri mEventImageURI;


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
    mCameraIconView =findViewById(R.id.iconview);
    mEventImageView = findViewById(R.id.imageviewsplash);
    mUploadImageButton =findViewById(R.id.uploadbutton);
    mUploadImageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        choosePhotoFromGallary();
        mEventImageUriErrorTextView.setVisibility(View.INVISIBLE);
      }
    });
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
    mRadioGroup = findViewById(R.id.radioGroup);
    mPrivateRadioButton = findViewById(R.id.private_radioButton);
    mPublicRadioButton = findViewById(R.id.public_radioButton);

    mEventImageView.setImageURI(mEvent.getEventImageUri());
   //if(mEventImageView !=null)  {
     mCameraIconView.setVisibility(View.INVISIBLE);
   //}



   mTitleView.getEditText().setText(mEvent.getTitle());
    mDescriptionView.getEditText().setText(mEvent.getDescription());
    mStartCalendarButton.setText(mEvent.getStartDate().toString());
    mEndCalendarButton.setText(mEvent.getEndDate().toString());
    mPlace.getEditText().setText(mEvent.getPlace());
    mAdress.getEditText().setText(mEvent.getAddress());
    mCity.getEditText().setText(mEvent.getCity());

    mSpinnerCategory = findViewById(R.id.spinner_category);
    final ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner_category));
    adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mSpinnerCategory.setAdapter(adapter_category);




//   if(mEvent.getPrivacy().contains("public")) {
//     mPublicRadioButton.isChecked();
//   } else {
//     mPrivateRadioButton.isChecked();
//   }








  }

  /*private void showPictureDialog() {
    AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
    pictureDialog.setTitle("Select Action");
    String[] pictureDialogItems = {
            "Select photo from gallery",
            "Capture photo from camera"};
    pictureDialog.setItems(pictureDialogItems,
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                  case 0:
                    choosePhotoFromGallary();
                    break;
                  case 1:
                    takePhotoFromCamera();
                    break;
                }
              }
            });
    pictureDialog.show();
  }*/

  public void choosePhotoFromGallary() {
    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    galleryIntent.setType("image/*");

    startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {

    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == this.RESULT_CANCELED) {
      return;
    }

    if (requestCode == GALLERY_REQUEST_CODE && data != null) {

      mEventImageURI = data.getData();

      Glide.with(this)
              .load(mEventImageURI)
              .into(mEventImageView);

      mCameraIconView.setVisibility(View.INVISIBLE);

    }
  }


}
