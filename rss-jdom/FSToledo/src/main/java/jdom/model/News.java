package jdom.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

public class News {
    public static int idCounter = 0;
    private int id;
    private String title;
    private String description;
    private String creator;
    private String link;
    private String mediaContent;
    private String mediaThumbnail;
    private String guid;
    private String pubdate;
    private ArrayList<String> categories;

    public void setCategory(String category){
        if(this.categories == null) {
            this.categories = new ArrayList<>();
        }
        if(category != null)this.categories.add(category);
        else this.categories.add("");
    }

    public News() {
        this.id=idCounter;
        idCounter++;
    }

    public News(String title, String description, String creator, String link,
                String mediaContent, String mediaThumbnail, String guid,
                String pubdate, ArrayList<String> categories) {
        this.id = idCounter;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.link = link;
        this.mediaContent = mediaContent;
        this.mediaThumbnail = mediaThumbnail;
        this.guid = guid;
        this.pubdate = pubdate;
        this.categories = categories;
        idCounter++;
    }

    @Override
    public String toString() {
        return "News --> ID"+ getId() +"\n"+
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Creator: " + creator + "\n" +
                "Link: " + link + "\n" +
                "Media Content: " + mediaContent + "\n" +
                "Media Thumbnail: " + mediaThumbnail + "\n" +
                "Guid: " + guid + "\n" +
                "PubDate: " + pubdate + "\n" +
                "Categories: " + categories + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent;
    }

    public String getMediaThumbnail() {
        return mediaThumbnail;
    }

    public void setMediaThumbnail(String mediaThumbnail) {
        this.mediaThumbnail = mediaThumbnail;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
}
