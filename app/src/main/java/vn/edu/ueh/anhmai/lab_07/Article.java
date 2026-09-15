package vn.edu.ueh.anhmai.lab_07;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    private String title;
    private String content;
    private int img_cover;
    private int view;

    public Article(String title, String content, int img_cover, int view) {
        this.title = title;
        this.content = content;
        this.img_cover = img_cover;
        this.view = view;
    }

    protected Article(Parcel in) {
        title = in.readString();
        content = in.readString();
        img_cover = in.readInt();
        view = in.readInt();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(img_cover);
        dest.writeInt(view);
    }


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public int getImg_cover() { return img_cover; }
    public void setImg_cover(int img_cover) { this.img_cover = img_cover; }
    public int getView() { return view; }
    public void setView(int view) { this.view = view; }
}