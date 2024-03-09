package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.PathToFolder;
import com.teachmeskills.final_assignment.session.Session;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileProcessingService {
   /* public static void processFile(Session session, String pathToFolder){
        if(session != null){
            if(session.isSessionAlive()){

            }
        }
    }*/

    public static List<File[]> getFileNames(){
        File dir = new File(PathToFolder.PATH_TO_FOLDER);
        File[] filesInData = dir.listFiles();
        List<File[]> allFiles = new ArrayList<>();
        for (File file : filesInData){
            if (file.isDirectory()){
                allFiles.add(file.listFiles());
            } /*else {
                allFiles.add(file);
            }*/
        }
        return allFiles;
    }

}
