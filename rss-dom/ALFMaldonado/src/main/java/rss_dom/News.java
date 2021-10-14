package rss_dom;

import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private String description;
    private String link;
    private String image;
    private String pubDate;
    private String Category;

    public String toString() {
        return "Title: " + this.title + "\nLink: " + this.link + "\nDescription: " + this.description + "\npubDate: " + this.pubDate + "\nCategory: " + this.Category + "\n";
    }

    public News() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLink() {
        return this.link;
    }

    public String getImage() {
        return this.image;
    }

    public String getPubDate() {
        return this.pubDate;
    }

    public String getCategory() {
        return this.Category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof News)) {
            return false;
        } else {
            News other = (News)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title != null) {
                        return false;
                    }
                } else if (!this$title.equals(other$title)) {
                    return false;
                }

                Object this$description = this.getDescription();
                Object other$description = other.getDescription();
                if (this$description == null) {
                    if (other$description != null) {
                        return false;
                    }
                } else if (!this$description.equals(other$description)) {
                    return false;
                }

                Object this$link = this.getLink();
                Object other$link = other.getLink();
                if (this$link == null) {
                    if (other$link != null) {
                        return false;
                    }
                } else if (!this$link.equals(other$link)) {
                    return false;
                }

                label62: {
                    Object this$image = this.getImage();
                    Object other$image = other.getImage();
                    if (this$image == null) {
                        if (other$image == null) {
                            break label62;
                        }
                    } else if (this$image.equals(other$image)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$pubDate = this.getPubDate();
                    Object other$pubDate = other.getPubDate();
                    if (this$pubDate == null) {
                        if (other$pubDate == null) {
                            break label55;
                        }
                    } else if (this$pubDate.equals(other$pubDate)) {
                        break label55;
                    }

                    return false;
                }

                Object this$Category = this.getCategory();
                Object other$Category = other.getCategory();
                if (this$Category == null) {
                    if (other$Category != null) {
                        return false;
                    }
                } else if (!this$Category.equals(other$Category)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof News;
    }


}
