package cartelera.um.cartelera.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by Christian on 17/01/2019.
 */

public class Movie implements Parcelable{
    private String id;
    private String name;
    private String synopsis;
    private String img;
    private String thumb_img;
    private Genre genre;
    private Date premiereDate;
    private List<String> actors;
    private int score;
    private int duration;
    private boolean premiere;

    public Movie(){}

    protected Movie(Parcel in) {
        id = in.readString();
        name = in.readString();
        synopsis = in.readString();
        img = in.readString();
        thumb_img = in.readString();
        genre = in.readParcelable(Genre.class.getClassLoader());
        actors = in.createStringArrayList();
        score = in.readInt();
        duration = in.readInt();
        premiere = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(synopsis);
        dest.writeString(img);
        dest.writeString(thumb_img);
        dest.writeParcelable(genre, flags);
        dest.writeStringList(actors);
        dest.writeInt(score);
        dest.writeInt(duration);
        dest.writeByte((byte) (premiere ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThumb_img() {
        return thumb_img;
    }

    public void setThumb_img(String thumb_img) {
        this.thumb_img = thumb_img;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isPremiere() {
        return premiere;
    }

    public void setPremiere(boolean premiere) {
        this.premiere = premiere;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getScore() != movie.getScore()) return false;
        if (isPremiere() != movie.isPremiere()) return false;
        if (getId() != null ? !getId().equals(movie.getId()) : movie.getId() != null) return false;
        if (getName() != null ? !getName().equals(movie.getName()) : movie.getName() != null)
            return false;
        if (getSynopsis() != null ? !getSynopsis().equals(movie.getSynopsis()) : movie.getSynopsis() != null)
            return false;
        if (getImg() != null ? !getImg().equals(movie.getImg()) : movie.getImg() != null)
            return false;
        if (getThumb_img() != null ? !getThumb_img().equals(movie.getThumb_img()) : movie.getThumb_img() != null)
            return false;
        if (getGenre() != null ? !getGenre().equals(movie.getGenre()) : movie.getGenre() != null)
            return false;
        if (getPremiereDate() != null ? !getPremiereDate().equals(movie.getPremiereDate()) : movie.getPremiereDate() != null)
            return false;
        return getActors() != null ? getActors().equals(movie.getActors()) : movie.getActors() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSynopsis() != null ? getSynopsis().hashCode() : 0);
        result = 31 * result + (getImg() != null ? getImg().hashCode() : 0);
        result = 31 * result + (getThumb_img() != null ? getThumb_img().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        result = 31 * result + (getPremiereDate() != null ? getPremiereDate().hashCode() : 0);
        result = 31 * result + (getActors() != null ? getActors().hashCode() : 0);
        result = 31 * result + getScore();
        result = 31 * result + (isPremiere() ? 1 : 0);
        return result;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

}
