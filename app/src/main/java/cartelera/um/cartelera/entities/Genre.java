package cartelera.um.cartelera.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Christian on 12/01/2019.
 */

public class Genre implements Parcelable {
    private String id;
    private String name;
    private String description;
    private String img;
    private String thumb_img;

    public Genre(){}

    public Genre(String name, String description){
        this.name = name;
        this.description = description;
    }

    protected Genre(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        img = in.readString();
        thumb_img = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(img);
        dest.writeString(thumb_img);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;

        Genre genre = (Genre) o;

        if (!getId().equals(genre.getId())) return false;
        if (!getName().equals(genre.getName())) return false;
        if (!getDescription().equals(genre.getDescription())) return false;
        if (!getImg().equals(genre.getImg())) return false;
        return getThumb_img().equals(genre.getThumb_img());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getImg().hashCode();
        result = 31 * result + getThumb_img().hashCode();
        return result;
    }
}
