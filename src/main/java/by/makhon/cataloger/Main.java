package by.makhon.cataloger;

import by.makhon.cataloger.bean.*;
import by.makhon.cataloger.converter.FileConverter;
import by.makhon.cataloger.logger.LogFileAppender;
import by.makhon.cataloger.modelbuilder.ModelBuilder;
import by.makhon.cataloger.scanner.DirectoryScanner;
import by.makhon.cataloger.view.HTMLView;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DirectoryScanner directoryScanner = new DirectoryScanner();
        FileConverter converter = new FileConverter();
        ModelBuilder modelBuilder = new ModelBuilder();
        HTMLView htmlView = new HTMLView();
        LogFileAppender logFileAppender = new LogFileAppender();
        
        //Scan all directories and get all files from it
        directoryScanner.scanDirectories(args);
        List<File> filesToConvert = directoryScanner.getFilesList();
        //Converting files from all directories to Mp3Bean format
        List<Mp3Bean> mp3Files = converter.convertFilesToMp3Beans(filesToConvert);
        //Build model for making view
        modelBuilder.buildModel(mp3Files);
        Model model = modelBuilder.getModel();
        //Building HTML view
        htmlView.buildHTML(model);
        //Logging Mp3 duplicates
        logFileAppender.log(mp3Files);
    }
}
