package by.makhon.cataloger.view;

import by.makhon.cataloger.bean.Album;
import by.makhon.cataloger.bean.Artist;
import by.makhon.cataloger.bean.Model;
import by.makhon.cataloger.bean.Song;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class HTMLView builds mp3 catalogue view in HTML format
 */
public class HTMLView {
    private final String HTML_FILE_PATH = "./src/"+ this.getClass().getPackage().getName().replaceAll(".","/") + "/music_catalogue.html";

    public void buildHTML(Model model) {
        List<String> htmlList = new ArrayList<>();
        File htmlFile = new File(HTML_FILE_PATH);
        try {
            if(htmlFile.createNewFile()){
                System.out.println(HTML_FILE_PATH + " File Created");
            }else {
                System.out.println("File " + HTML_FILE_PATH + " already exists");
            }
            FileOutputStream fos = new FileOutputStream(HTML_FILE_PATH);
            htmlList.add("<!DOCTYPE html>\n" +
                            "<html>\n" +
                            " <head>\n" +
                            "  <meta charset=\"utf-8\" />\n" +
                            "  <title>HTML5</title>\n" +
                            " </head>" +
                            "<body>\n");
            for (Map.Entry<String, Artist> artist : model.getArtists().entrySet()) {
                htmlList.add("<div id=\"" + artist.getKey() + "\">");
                htmlList.add("<p style=\"margin: 10px 0 0 0;\">Artist: " + artist.getKey() + "</p>");
                for (Map.Entry<String, Album> album : artist.getValue().getAlbums().entrySet()) {
                    htmlList.add("<p style=\"margin: 5px 0 0 50px;\">Album: " + album.getKey() + "</p>");
                    for (Song song : album.getValue().getSongs()) {
                        htmlList.add("<div style=\"display: block;\")>");
                        htmlList.add("<p style=\"margin: 5px 0 0 105px; display: inline-block;\">Song title: " + song.getName() + "</p>" +
                                "<p style=\"margin: 0 20px; display: inline-block; \" >Song duration: "+ song.getDuration() + "</p>"
                                + "<a style=\"display: inline-block; \" href=\"" + song.getLocalLink() + "\">Local link</a>");
                        htmlList.add("</div>");
                    }
                }
                htmlList.add("</div>");
            }
            htmlList.add(
                    " </body>\n" +
                    "</html>");
            for (String data : htmlList) {
                fos.write(data.getBytes());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
