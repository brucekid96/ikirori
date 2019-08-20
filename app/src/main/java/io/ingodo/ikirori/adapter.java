package io.ingodo.ikirori;


import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import io.ingodo.ikirori.data.Event;
import java.util.List;


public class adapter
    extends RecyclerView.Adapter<adapter.EventCardHolder> {

  public interface OnDeleteClickListener {
    void OnDeleteClickListener(Event myEvent);
  }


  private static final String TAG = adapter.class.getSimpleName();

  Context mContext;
  private List<Event> mEvents;
 private OnDeleteClickListener onDeleteClickListener;
 public int mPosition;



  public adapter(Context context, List<Event> events,OnDeleteClickListener listener) {
    mContext = context;
    mEvents = events;
    onDeleteClickListener = listener;

  }

  void setEvents(List<Event> events) {
    mEvents = events;
    notifyDataSetChanged();
  }

  public class EventCardHolder extends RecyclerView.ViewHolder {
    public EventCardHolder(EventCard eventcard) {
      super(eventcard);
    }

    public EventCard getEventCard() {
      return (EventCard) itemView;
    }
  }





  @Override
  public EventCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    EventCard vHolder = new EventCard(parent.getContext());
    return new EventCardHolder(vHolder);
  }

  @Override
  public void onBindViewHolder(@NonNull EventCardHolder eventCardHolder,int  position) {
    eventCardHolder.getEventCard().bindEvent(mEvents.get(position));
  }


  private void showCardDialog() {
    AlertDialog.Builder cardDialog = new AlertDialog.Builder(mContext);
    cardDialog.setTitle("Select Action");
    String[] cardDialogItems = {
        "delete all events",
    };

    cardDialog.setItems(cardDialogItems,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            switch (which) {
              case 0:
                if(onDeleteClickListener!=null) {
                  onDeleteClickListener.OnDeleteClickListener(mEvents.get(mPosition));
                }
//              mEventViewModel.deleteEvent(mEvent);
                break;
            }
          }
        });
    cardDialog.show();
  }




  @Override
  public int getItemCount() {
    if (mEvents != null)
      return mEvents.size();
    else {
      return 0;
    }
  }
}
