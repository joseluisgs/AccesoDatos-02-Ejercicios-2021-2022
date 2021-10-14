package com.madirex.sax;

import com.madirex.sax.model.Post;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PostHandler extends DefaultHandler {
    private boolean hasPubDate = false;
    private boolean hasTitle = false;
    private boolean hasDescription = false;
    private boolean hasLink = false;
    private boolean hasAuthor = false;

    private List<Post> postList = null;
    private Post post = null;

    private boolean enEntry = false;

    public List<Post> getPosts() {
        return postList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            enEntry = true;
        }


        // Iniciar lista
        if (postList == null) {
            postList = new ArrayList<>();
        }

        if (enEntry) {
            if (qName.equalsIgnoreCase("item")) {
                post = new Post();
            } else if (qName.equalsIgnoreCase("pubDate")) {
                hasPubDate = true;
                //} else if (qName.equalsIgnoreCase("category")) {
                // hasCategory = true;
            } else if (qName.equalsIgnoreCase("title")) {
                hasTitle = true;
            } else if (qName.equalsIgnoreCase("description")) {
                hasDescription = true;
            } else if (qName.equalsIgnoreCase("link")) {
                hasLink = true;
            } else if (qName.equalsIgnoreCase("author")) {
                hasAuthor = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            postList.add(post);
            enEntry = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (hasPubDate) {
            post.setPubDate(new String(ch, start, length));
            hasPubDate = false;
        } else if (hasTitle) {
            post.setTitle(new String(ch, start, length));
            hasTitle = false;
        } else if (hasDescription) {
            post.setDescription(new String(ch, start, length));
            hasDescription = false;
        } else if (hasLink) {
            post.setLink(new String(ch, start, length));
            hasLink = false;
        } else if (hasAuthor) {
            post.setAuthor(new String(ch, start, length));
            hasAuthor = false;
        }
    }
}