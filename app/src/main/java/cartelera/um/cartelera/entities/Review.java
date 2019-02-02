package cartelera.um.cartelera.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Christian on 26/01/2019.
 */

public class Review implements Parcelable {
    private String id;
    private String message;
    private User user;
    private double score;
    private Date date;

    public Review (){}

    public Review (String message, double score, User user){
        this.message = message;
        this.score = score;
        this.user = user;
        this.date = new Date();
    }

    protected Review(Parcel in) {
        id = in.readString();
        message = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        score = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(message);
        dest.writeParcelable(user, flags);
        dest.writeDouble(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;

        Review review = (Review) o;

        if (Double.compare(review.getScore(), getScore()) != 0) return false;
        if (getId() != null ? !getId().equals(review.getId()) : review.getId() != null)
            return false;
        if (getMessage() != null ? !getMessage().equals(review.getMessage()) : review.getMessage() != null)
            return false;
        if (getUser() != null ? !getUser().equals(review.getUser()) : review.getUser() != null)
            return false;
        return getDate() != null ? getDate().equals(review.getDate()) : review.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        temp = Double.doubleToLongBits(getScore());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }
}
