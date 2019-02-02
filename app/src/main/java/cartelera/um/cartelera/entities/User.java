package cartelera.um.cartelera.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Christian on 26/01/2019.
 */

public class User implements Parcelable {
    private String id;
    private String name;
    private String email;
    private String img;
    private String thumbImg;
    private String accessToken;
    private String tokenExp;
    private boolean admin;
    private Date creationDate;

    public User (){}


    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        img = in.readString();
        thumbImg = in.readString();
        accessToken = in.readString();
        tokenExp = in.readString();
        admin = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(img);
        dest.writeString(thumbImg);
        dest.writeString(accessToken);
        dest.writeString(tokenExp);
        dest.writeByte((byte) (admin ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenExp() {
        return tokenExp;
    }

    public void setTokenExp(String tokenExp) {
        this.tokenExp = tokenExp;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (isAdmin() != user.isAdmin()) return false;
        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null)
            return false;
        if (getImg() != null ? !getImg().equals(user.getImg()) : user.getImg() != null)
            return false;
        if (getThumbImg() != null ? !getThumbImg().equals(user.getThumbImg()) : user.getThumbImg() != null)
            return false;
        if (getAccessToken() != null ? !getAccessToken().equals(user.getAccessToken()) : user.getAccessToken() != null)
            return false;
        if (getTokenExp() != null ? !getTokenExp().equals(user.getTokenExp()) : user.getTokenExp() != null)
            return false;
        return getCreationDate() != null ? getCreationDate().equals(user.getCreationDate()) : user.getCreationDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getImg() != null ? getImg().hashCode() : 0);
        result = 31 * result + (getThumbImg() != null ? getThumbImg().hashCode() : 0);
        result = 31 * result + (getAccessToken() != null ? getAccessToken().hashCode() : 0);
        result = 31 * result + (getTokenExp() != null ? getTokenExp().hashCode() : 0);
        result = 31 * result + (isAdmin() ? 1 : 0);
        result = 31 * result + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
        return result;
    }
}
