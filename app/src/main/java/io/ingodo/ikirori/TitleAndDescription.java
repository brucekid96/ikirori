package io.ingodo.ikirori;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import io.ingodo.ikirori.data.Event;


public class TitleAndDescription extends AppCompatActivity {
  private int GALLERY_REQUEST_CODE = 1;
  private int CAMERA_REQUEST_CODE = 2;
  private static final String TAG = TitleAndDescription.class.getSimpleName();

  Toolbar mToolbar;
  private Button mUploadImageButton;
  private ImageView mEventImageView;
  private ImageView mCameraIconView;
  private TextInputLayout mTitleView;
  private TextInputLayout mDescriptionView;
  private TextView mTitleErrorTextview;
  private TextView mDescriptionErrorTextview;
  private TextView mEventImageUriErrorTextView;

  Event mEvent;

  private Uri mEventImageURI;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    setContentView(R.layout.title_and_description_layout);

    mToolbar = findViewById(R.id.toolbar_create_event);
    setSupportActionBar(mToolbar);


    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);






    FloatingActionButton fab = findViewById(R.id.floatingActionButton);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        mEvent = new Event();
        if (mEventImageURI==null) {
        mEventImageUriErrorTextView.setVisibility(View.VISIBLE);
        } else

        if (mTitleView.getEditText().length()==0) {
          mTitleErrorTextview.setVisibility(View.VISIBLE);

        }
        else
          if( mDescriptionView.getEditText().length()==0  ) {
          mDescriptionErrorTextview.setVisibility(View.VISIBLE);
        }
          else if(mEventImageURI!=null & mTitleView.getEditText().length()!=0 & mDescriptionView.getEditText().length()!=0 )
          {

            mEvent.setEventImageUri(mEventImageURI);
            mEvent.setDescription(mDescriptionView.getEditText().getText().toString());
            mEvent.setTitle(mTitleView.getEditText().getText().toString());
            Log.d(TAG, mEvent.toString());
            Intent intent = new Intent(TitleAndDescription.this, StartAndEndDate.class);
            intent.putExtra(Event.EVENT_EXTRA, mEvent);
            startActivity(intent);
          }

       }
    });




    mUploadImageButton = (Button) findViewById(R.id.uploadbutton);
    mCameraIconView = (ImageView) findViewById(R.id.iconview);
    mEventImageView = (ImageView) findViewById(R.id.imageviewsplash);

    mUploadImageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        choosePhotoFromGallary();
        mEventImageUriErrorTextView.setVisibility(View.INVISIBLE);
      }
    });

    mTitleView =findViewById(R.id.event_title_label_id);
    mTitleView.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {

      @Override
      public void onFocusChange(View v, boolean hasFocus) {

        if(!hasFocus){
          mTitleErrorTextview.setVisibility(View.INVISIBLE);
        }
      }
    });

    mDescriptionView =findViewById (R.id.event_description_label_id);
    mDescriptionView.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {

      @Override
      public void onFocusChange(View v, boolean hasFocus) {

        if(!hasFocus){
          mDescriptionErrorTextview.setVisibility(View.INVISIBLE);
        }
      }
    });

    mTitleErrorTextview = findViewById(R.id.title_error_textView);
    mDescriptionErrorTextview = findViewById(R.id.Description_error_textview);
    mEventImageUriErrorTextView = findViewById(R.id.EventImageUriErrorTextview);


  }
  //dialogInterfaces
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


