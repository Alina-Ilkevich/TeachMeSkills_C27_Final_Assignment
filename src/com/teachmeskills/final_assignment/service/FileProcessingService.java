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
        List<File[]> files = new ArrayList<>();
        Iterator<File[]> itr = files.iterator();
        while (itr.hasNext()){
            files.add(itr.next());
        }
        return files;
    }
    public static void getOrderFiles(List<File[]> files){
        for (File[] file : files){
           if (file)
        }
    }
}
