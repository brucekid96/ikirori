package io.ingodo.ikirori;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Tickets  extends AppCompatActivity {


    Toolbar mToolbar;
    private Button mFreeButton;
    private Button mPaidButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickets);

        mToolbar = findViewById(R.id.toolbar_event_tickets);
        setSupportActionBar(mToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mFreeButton = findViewById(R.id.free);
        mFreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tickets.this , FreeTickets.class);
                startActivity(intent);
            }
        });;

        mPaidButton = findViewById(R.id.paid);
        mPaidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tickets.this , PaidTickets.class);
                startActivity(intent);
            }
        });;






    }
}
