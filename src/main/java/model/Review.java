package model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

@Entity
@Table(name = "imgofday")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Có nhiều comment (review): Mỗi comment (review) có 1 id
    private Long id;

    // Từ 1 đến 5 sao
    private int rate;

    // Tác giả ảnh: Nhập vào
    private String author;

    // Bình luận nhập vào qua feedback
    private String feedBack;

    // Phải nhận ngày theo ngày hiện tại: Current time
    private String dates;

    // Like mối comment: Chỉ có ở dưới comment

    @Column(columnDefinition = "integer default 0")
    private int likes;

    private int number;

    public Review() {

    }

    public Review(Long id, int rate, String author, String feedBack, String dates, int likes, int number) {
        this.id = id;
        this.rate = rate;
        this.author = author;
        this.feedBack = feedBack;
        this.dates = dates;
        this.likes = likes;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
