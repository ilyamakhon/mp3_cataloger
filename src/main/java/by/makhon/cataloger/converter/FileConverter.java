package by.makhon.cataloger.converter;

import by.makhon.cataloger.bean.*;
import com.mpatric.mp3agic.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class FileConverter converts list of all files from all
 * directories to Mp3Bean format
 */
public class FileConverter {

    public List<Mp3Bean> convertFilesToMp3Beans(List<File> files) {
        List<Mp3Bean> mp3Beans = new ArrayList<>();
        try {
            for (File file : files) {
                String fileExtension = getFileExtension(file);
                if (fileExtension.equals("mp3")) {
                    Mp3File mp3File = new Mp3File(file.getAbsolutePath());
                    if (mp3File.hasId3v2Tag()) {
                        ID3v2 tag = mp3File.getId3v2Tag();
                        Mp3Bean mp3Bean = new Mp3Bean();
                        mp3Bean.setArtist(tag.getArtist() == null || tag.getArtist().equals("") ? "Unknown Artist" : tag.getArtist());
                        mp3Bean.setAlbum(tag.getAlbum() == null || tag.getAlbum().equals("") ? "Unknown Album" : tag.getAlbum());
                        mp3Bean.setSong(tag.getTitle() == null || tag.getTitle().equals("") ? "Unknown Song" : tag.getTitle());
                        mp3Bean.setDuration(convertSongDurationFromSeconds(mp3File.getLengthInSeconds()));
                        mp3Bean.setLocalLink(mp3File.getFilename());
                        mp3Bean.setChecksum(checksumFile(file));
                        mp3Beans.add(mp3Bean);
                    } else {
                        Mp3Bean mp3Bean = new Mp3Bean();
                        mp3Bean.setArtist("Unknown Artist");
                        mp3Bean.setAlbum("Unknown Album");
                        mp3Bean.setSong("Unknown Song");
                        mp3Bean.setDuration(convertSongDurationFromSeconds(mp3File.getLengthInSeconds()));
                        mp3Bean.setLocalLink(mp3File.getFilename());
                        mp3Bean.setChecksum(checksumFile(file));
                        mp3Beans.add(mp3Bean);
                    }
                }
            }
        } catch (UnsupportedTagException | IOException | InvalidDataException | NullPointerException e) {
            e.printStackTrace();
        }
        return mp3Beans;
    }

    private String convertSongDurationFromSeconds(long duration) {
        long minutes = ((duration % 3600) / 60);
        long seconds = (duration % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    private String checksumFile(File file) {
        try{
            return DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
