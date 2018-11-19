package by.makhon.cataloger.logger;

import by.makhon.cataloger.bean.Mp3Bean;
import by.makhon.cataloger.bean.Mp3Duplicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class LogFileAppender creates logs for Mp3 files duplicates
 */
public class LogFileAppender {
    private static final Logger CHECKSUM_LOGGER = LogManager.getLogger("duplicates-checksum-log");
    private static final Logger DUPLICATES_LOGGER = LogManager.getLogger("duplicates-log");

    public void log(List<Mp3Bean> mp3Files) {
        System.setProperty("log4j2.configurationFile", ".src\\main\\resources\\log4j2.xml");
        DuplicatesFinder duplicatesFinder = new DuplicatesFinder();

        List<Mp3Duplicate> mp3ChecksumDuplicates = duplicatesFinder.getChecksumDuplicates(mp3Files);
        List<Mp3Duplicate> mp3Duplicates = duplicatesFinder.getDuplicates(mp3Files);

        StringBuilder checksumLogData = new StringBuilder();
        if (mp3ChecksumDuplicates != null) {
            int i = 0;
            for (Mp3Duplicate mp3Duplicate: mp3ChecksumDuplicates) {
                i++;
                checksumLogData.append("\nDuplicate ").append(i).append("\n");
                for (String link : mp3Duplicate.getLocalLinks()) {
                    checksumLogData.append(link).append("\n");
                }
            }
            CHECKSUM_LOGGER.info(checksumLogData);
        }

        StringBuilder duplicatesLogData = new StringBuilder();
        if (mp3Duplicates != null) {
            for (Mp3Duplicate mp3Duplicate : mp3Duplicates) {
                duplicatesLogData.append("\nArtist: ")
                        .append(mp3Duplicate.getArtist())
                        .append("| Album: ")
                        .append(mp3Duplicate.getAlbum())
                        .append("| Song: ")
                        .append(mp3Duplicate.getSong())
                        .append("\n");
                for (String link : mp3Duplicate.getLocalLinks()) {
                    duplicatesLogData.append(link).append("\n");
                }
            }
            DUPLICATES_LOGGER.info(duplicatesLogData);
        }
    }
}
