package io.ingodo.ikirori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.ingodo.ikirori.data.Event;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

  Context mContext;
  List<Event>mEvents;

  public RecyclerViewAdapter(Context context, List<Event> events) {
    mContext = context;
    mEvents = events;
  }



  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View v;
    v = LayoutInflater.from(mContext).inflate(R.layout.brouillon,parent,false);
    MyViewHolder vHolder= new MyViewHolder(v);
    return vHolder;
  }

  private String datestampToStr (long timestamp) {
    Date date = new Date(timestamp);
    return getDateString(date);

  }


  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.mImage.setImageURI(mEvents.get(position).getImage());
    holder.mTitle.setText(mEvents.get(position).getTitle());
    holder.mBeginningDate.setText(mEvents.get(position).getBeginningDate().toString());
    holder.mTitle.setText(mEvents.get(position).getTitle());
    holder.mVenue.setText(mEvents.get(position).getVenue());
    holder.mCity.setText(mEvents.get(position).getCity());
    holder.mPlace.setText(mEvents.get(position).getCity());
  }

  @Override
  public int getItemCount() {
    return mEvents.size();
  }

  public static class MyViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImage;
    private TextView mTitle;
    private TextView mBeginningDate;
    private TextView mVenue;
    private TextView mCity;
    private TextView mPlace;

    public MyViewHolder(View itemView) {

      super(itemView);
      mImage =(ImageView)itemView.findViewById(R.id.card_image);
      mTitle=(TextView)itemView.findViewById(R.id.title_id);
      mBeginningDate=(TextView)itemView.findViewById(R.id.calendar_id);
      mVenue=(TextView)itemView.findViewById(R.id.venue_view_id);
      mCity=(TextView)itemView.findViewById(R.id.city_view_id);
      mPlace=(TextView)itemView.findViewById(R.id.place_view_id);


    }
  }
  private String getDateString(Date date) {
    SimpleDateFormat dateFormatter = new SimpleDateFormat(
        "MMM d, yyyy");
    return dateFormatter.format(date);
  }
}
