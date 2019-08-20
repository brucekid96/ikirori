package io.ingodo.ikirori;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.ingodo.ikirori.data.Event;
import java.util.List;

public class FreeTickets  extends AppCompatActivity {


    Toolbar mToolbar;
    private List<Event> mEvents;
    private TextView mTicketName;
    private TextView mTicketQuantity;
    private TextView mTicketDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_tickets);

        mToolbar = findViewById(R.id.toolbar_event_free_tickets);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById
            (R.id.floatingActionButtonFreeTickets);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(),
                    HomeActivity.class));

            }
        });


        mTicketName = (TextView) findViewById(R.id.free_ticket_name_label);
        mTicketQuantity = (TextView) findViewById(R.id.free_description_label);
        mTicketDescription = (TextView) findViewById(R.id.free_quantity_label);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}