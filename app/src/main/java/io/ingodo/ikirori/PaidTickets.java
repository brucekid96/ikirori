package io.ingodo.ikirori;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.ingodo.ikirori.data.Event;

public class  PaidTickets extends AppCompatActivity {
  Event mEvent;
    Spinner mSpinnerTarif;
    Toolbar mToolbar;
    FloatingActionButton FloatingPaidButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.paid_tickets);


          Intent intent = getIntent();
          mEvent = intent.getParcelableExtra(Event.EVENT_EXTRA);

                mToolbar = findViewById(R.id.paid_toolbar_event);
                setSupportActionBar(mToolbar);

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);


            mSpinnerTarif = findViewById(R.id.tarif);
            final ArrayAdapter<String> adapter_tarif = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.frais));
            adapter_tarif.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinnerTarif.setAdapter(adapter_tarif);
                FloatingPaidButton = findViewById(R.id.floatingActionButtonPaidTickets);
                FloatingPaidButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(PaidTickets.this, Category.class);
                                intent.putExtra(Event.EVENT_EXTRA, mEvent);
                                startActivity(intent);

                        }
                });
        }
}

