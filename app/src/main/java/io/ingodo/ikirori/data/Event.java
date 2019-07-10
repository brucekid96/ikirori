package io.ingodo.ikirori.data;

import android.net.Uri;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;


@Entity(tableName = "event_table",foreignKeys = @ForeignKey( entity = Tickets.class,
    parentColumns = {"event_id","ticket_type"}, childColumns ={"ticket_type","event_id"}))
public class Event {

  @ColumnInfo(name = "title")
  private String mTitle;
  private Date mBeginningDate;
  private Date mEndDate;
  @ColumnInfo(name = "city")
  private String mCity;
  @ColumnInfo(name = "place")
  private String mPlace;
  private Uri mImage;
  @ColumnInfo(name = "description")
  private String mDescription;
  @ColumnInfo(name = "venue")
  private String mVenue;
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "event_id")
  private int mId;

  public int getId() {
    return mId;
  }

  public void setId(int id) {
    this.mId = id;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public Date getBeginningDate() {
    return mBeginningDate;
  }

  public void setBeginningDate(Date beginningDate) {
    mBeginningDate = beginningDate;
  }

  public Date getEndDate() {
    return mEndDate;
  }

  public void setEndDate(Date endDate) {
    mEndDate = endDate;
  }

  public String getCity() {
    return mCity;
  }

  public void setCity(String city) {
    mCity = city;
  }

  public String getPlace() {
    return mPlace;
  }

  public void setPlace(String place) {
    mPlace = place;
  }

  public Uri getImage() {
    return mImage;
  }

  public void setImage(Uri image) {
    mImage = image;
  }

  public String getDescription() {
    return mDescription;
  }

  public void setDescription(String description) {
    mDescription = description;
  }

  public String getVenue() {
    return mVenue;
  }

  public void setVenue(String venue) {
    mVenue = venue;
  }

  public Event(int id,String title, Date beginningDate, Date endDate, String city,
      Uri image, String description, String venue,String place, String ticketName,
      String descriptionTicket, int quantity) {
    this.mId =id;
    mTitle = title;
    mBeginningDate = beginningDate;
    mEndDate = endDate;
    mCity = city;
    mPlace = place;
    mImage = image;
    mDescription = description;
    mVenue = venue;

  }
}
