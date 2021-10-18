package dam.sax.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private String title;
    private String description;
    private String creator;
    private String link;
    private String mediaDescription;
    private String mediaTitle;
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

    @Override
    public String toString() {
        return "News -->" +"\n"+
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Creator: " + creator + "\n" +
                "Link: " + link + "\n" +
                "Media Description: " + mediaDescription + "\n" +
                "Media Title :" + mediaTitle + "\n" +
                "Media Content " + mediaContent + "\n" +
                "Media Thumbnail: " + mediaThumbnail + "\n" +
                "Guid: " + guid + "\n" +
                "PubDate: " + pubdate + "\n" +
                "Categories: " + categories + "\n";
    }
}
