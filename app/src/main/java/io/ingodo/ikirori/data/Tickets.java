package io.ingodo.ikirori.data;


import android.accounts.Account;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tickets_table",foreignKeys = @ForeignKey(entity = Event.class,parentColumns ="event_id" ,childColumns = "event_foreign_id"))
public class Tickets {
  @ColumnInfo(name = "ticket_type")
  private String mTicketType;
  @ColumnInfo(name = "ticket_name")
  private String mTicketName;
  @ColumnInfo(name = "ticket_description")
  private String mTicketDescription;
  @ColumnInfo(name = "ticket_quantity")
  private int mTicketQuantity;
  @ColumnInfo(name = "ticket_price")
  private int mTicketPrice;

  @ColumnInfo(name = "event_foreign_id")
  private int mId;

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "ticket_id")
  private int TicketId;

  public int getTicketId() {
    return TicketId;
  }

  public void setTicketId(int ticketId) {
    TicketId = ticketId;
  }

  public Tickets(int id,int ticketId, String ticketType, String ticketName, String ticketDescription,
      int ticketQuantity, int ticketPrice) {
    mId =id;
    TicketId = ticketId;
    mTicketType = ticketType;
    mTicketName = ticketName;
    mTicketDescription = ticketDescription;
    mTicketQuantity = ticketQuantity;
    mTicketPrice = ticketPrice;

  }

  public int getId() {
    return mId;
  }

  public void setId(int id) {
    this.mId = id;
  }

  public String getTicketType() {
    return mTicketType;
  }

  public void setTicketType(String ticketType) {
    mTicketType = ticketType;
  }

  public String getTicketName() {
    return mTicketName;
  }

  public void setTicketName(String ticketName) {
    mTicketName = ticketName;
  }

  public String getTicketDescription() {
    return mTicketDescription;
  }

  public void setTicketDescription(String ticketDescription) {
    mTicketDescription = ticketDescription;
  }

  public int getTicketQuantity() {
    return mTicketQuantity;
  }

  public void setTicketQuantity(int ticketQuantity) {
    mTicketQuantity = ticketQuantity;
  }

  public int getTicketPrice() {
    return mTicketPrice;
  }

  public void setTicketPrice(int ticketPrice) {
    mTicketPrice = ticketPrice;
  }


}
