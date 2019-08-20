package io.ingodo.ikirori;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.ingodo.ikirori.data.Event;
import io.ingodo.ikirori.data.EventRepository;

public class Category  extends AppCompatActivity {
 Toolbar mToolbar;
 Spinner mSpinnerTicket;
 Spinner mSpinnerCategory;
  String SpinnerTicketSelected;
 Button mSaveEvent;
 RadioGroup mRadioGroup;
 RadioButton mPublicRadioButton;
 RadioButton mPrivateRadioButton;

  Event mEvent;
  EventRepository mRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.category);


    Intent intent = getIntent();
    mEvent = intent.getParcelableExtra(Event.EVENT_EXTRA);

    mToolbar = findViewById(R.id.toolbar_location);

    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);



    mSpinnerTicket= findViewById(R.id.spinner_ticket);
    final ArrayAdapter<String> adapter_ticket = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner_ticket));
    adapter_ticket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mSpinnerTicket.setAdapter(adapter_ticket);

    mSpinnerCategory = findViewById(R.id.spinner_category);
    final ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner_category));
    adapter_ticket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mSpinnerCategory.setAdapter(adapter_category);

       mSpinnerTicket.setOnItemSelectedListener(new OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
           SpinnerTicketSelected = mSpinnerTicket.getSelectedItem().toString();
           if(SpinnerTicketSelected.contains("Paid Ticket")) {
             Intent intent = new Intent(Category.this, PaidTickets.class);
             intent.putExtra(Event.EVENT_EXTRA, mEvent);
             startActivity(intent);
           }
         }
         @Override
         public void onNothingSelected(AdapterView<?> arg0) {
           // TODO Auto-generated method stub

         }
       });





    mRadioGroup = findViewById(R.id.radioGroup);
    mPrivateRadioButton = findViewById(R.id.private_radioButton);
    mPublicRadioButton = findViewById(R.id.public_radioButton);




    mSaveEvent = findViewById(R.id.save_event_button);
    mSaveEvent.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(mPublicRadioButton.isChecked()) {
          mEvent.setPrivacy("Public");
        } else {
          mEvent.setPrivacy("Private");
        }

       mEvent.setCategory(mSpinnerTicket.getSelectedItem().toString());
        mRepository = new EventRepository(getApplication());
        mRepository.insert(mEvent);
        Intent intent = new Intent(Category.this, HomeActivity.class);
        intent.putExtra(Event.EVENT_EXTRA, mEvent);
        startActivity(intent);
      }
    });



  }
}
