package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RSSItem {
	public String title;
	public String link;
	public String description;
	public LocalDateTime pubDate;
}
