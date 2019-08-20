package io.ingodo.ikirori.data;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Date;
import java.util.UUID;


@Entity(tableName = "event_table")
public class Event implements Parcelable {

  public static final String EVENT_EXTRA = "event_code";

  @NonNull
  @PrimaryKey
  @ColumnInfo(name = "id")
  private UUID mId;
  @ColumnInfo(name = "event_image_uri")
  private Uri mEventImageUri;
  @NonNull
  @ColumnInfo(name = "title")
  private String mTitle;
  @NonNull
  @ColumnInfo(name = "description")
  private String mDescription;
  @NonNull
  @ColumnInfo(name = "start_date_table")
  private Date mStartDate;
  @NonNull
  @ColumnInfo(name = "end_date_table")
  private Date mEndDate;
  @NonNull
  @ColumnInfo(name = "place")
  private String mPlace;
  @NonNull
  @ColumnInfo(name = "address")
  private String mAddress;
  @NonNull
  @ColumnInfo(name = "city")
  private String mCity;
  @ColumnInfo(name = "category")
  private String mCategory;
  @ColumnInfo(name = "privacy")
  private String mPrivacy;

  @Ignore
  public Event() {
    mId = UUID.randomUUID();
  }

  public Event(UUID id, Uri eventImageUri, String title, String description, Date startDate,
      Date endDate, String city
      , String address, String place, String category,String privacy) {
    mId = id;
    mEventImageUri = eventImageUri;
    mTitle = title;
    mDescription = description;
    mStartDate = startDate;
    mEndDate = endDate;
    mCity = city;
    mPlace = place;
    mAddress = address;
    mCategory = category;
    mPrivacy = privacy;

  }


  public void setEventImageUri(Uri eventImageUri) {
    mEventImageUri = eventImageUri;
  }

  public Uri getEventImageUri() {
    return mEventImageUri;
  }


  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }


  public String getDescription() {
    return mDescription;
  }

  public void setDescription(String description) {
    mDescription = description;
  }

  public UUID getId() {
    return mId;
  }

  public void setId(UUID id) {
    this.mId = id;
  }


  public Date getStartDate() {
    return mStartDate;
  }

  public void setStartDate(Date startDate) {
    mStartDate = startDate;
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


  public String getAddress() {
    return mAddress;
  }

  public void setAddress(String address) {
    mAddress = address;
  }

  public String getCategory() {
    return mCategory;
  }

  public void setCategory(String category) {
    mCategory = category;
  }



  public String getPrivacy() {
    return mPrivacy;
  }

  public void setPrivacy(String privacy) {
    mPrivacy = privacy;
  }


  public String toString() {
    return Event.class.getSimpleName()
        + "["
        + "mId="
        + mId.toString()
        +"mEventImageUri="
        + (mEventImageUri == null ? "null" : mEventImageUri.toString())
        + ","
        +"mTitle="
        + mTitle
        + ","
        +"mDescription="
        +mDescription
        + ","
        +"mStartDate="
        + (mStartDate == null ? "null" : mStartDate)
        + ","
        +"mEndDate="
        +(mEndDate == null ? "null" : mEndDate)
        + ","
        +"mPlace="
        + mPlace
        + ","
        +"mAddress="
        + mAddress
        + ","
        +"mCity="
        + mCity
        + ","
        +"mCategory="
        +mCategory
        + "]";
  }





  // PARCELABLE IMPLEMENTATION METHODS
  @Ignore
  public Event(Parcel in) {
    this.mId = UUID.fromString(in.readString());
    this.mEventImageUri = in.readParcelable(Uri.class.getClassLoader());
    this.mTitle = in.readString();
    this.mDescription = in.readString();
    this.mStartDate = (java.util.Date) in.readSerializable();
    this.mEndDate = (java.util.Date) in.readSerializable();
    this.mPlace = in.readString();
    this.mAddress = in.readString();
    this.mCity = in.readString();
    this.mCategory = in.readString();
    this.mPrivacy = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.mId.toString());
    dest.writeParcelable(this.mEventImageUri, flags);
    dest.writeString(this.mTitle);
    dest.writeString(this.mDescription);
    dest.writeSerializable(this.mStartDate);
    dest.writeSerializable(this.mEndDate);
    dest.writeString(this.mPlace);
    dest.writeString(this.mAddress);
    dest.writeString(this.mCity);
    dest.writeString(this.mCategory);
    dest.writeString(this.mPrivacy);
  }

  public static final Parcelable.Creator<Event> CREATOR =
      new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
          return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
          return new Event[size];
        }
      };
}

