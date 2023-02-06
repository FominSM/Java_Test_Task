package com.example;
import java.util.*;
import com.example.Data.DataReaderWriter;


public class Main {
    public static void main(String[] args) {
        Map<String, String> workParameters = cheсkMode(args);
        
        if(workParameters.size() == 4){
            System.out.println("\t+ Parameters accepted");
            DataReaderWriter newData = new DataReaderWriter(workParameters.get("sortMode"), 
                                                         workParameters.get("dataType"), 
                                                         workParameters.get("nameOutFile"), 
                                                         workParameters.get("inputFilenames"));
            int[] inputData = newData.readDataFromFiles();
            if(inputData.length != 0){
                MergeSort sortArr = new MergeSort(inputData);
                int[] resultData = sortArr.start(newData.getModeSort());
                newData.writeDataNewFile(resultData);
            }
            else{
                System.out.println("\tERROR - Check the correctness of the names of the input files and try again");
            }
        }
        else{
            System.out.println("\tERROR - Parameters not accepted. Make sure the command is correct and try again");
        }        
    }

    public static Map<String,String> cheсkMode(String[] data){
        Map<String,String> parametrs = new HashMap<>(); 
        String inputFilenames = "";

        if(data[0].equals("-d") || data[0].equals("-a")){
            parametrs.put("sortMode", data[0]);
            if(data[1].equals("-s") || data[1].equals("-i")){
                parametrs.put("dataType", data[1]);
            }
            if(cheсkName(data[2])){
                parametrs.put("nameOutFile", data[2]);
            }
            else{
                parametrs.put("nameOutFile", "defaultName.txt");
            }
            for(int i = 3; i < data.length; i++){
                inputFilenames += data[i] + " ";
            }
            parametrs.put("inputFilenames", inputFilenames);

            return parametrs;
        }
        else{
            parametrs.put("sortMode", "-a");
            if(data[0].equals("-s") || data[0].equals("-i")){
                parametrs.put("dataType", data[0]);
            }
            if(cheсkName(data[1])){
                parametrs.put("nameOutFile", data[1]);
            }
            else{
                parametrs.put("nameOutFile", "defaultName.txt");
                System.out.println("\tINFO - The target file name entered is invalid. Replaced by default with - defaultName.txt");

            }
            for(int i = 2; i < data.length; i++){
                inputFilenames += data[i] + " ";
            }
            parametrs.put("inputFilenames", inputFilenames);
            return parametrs;
        }
        
    }

    public static boolean cheсkName(String name){
        char[] arr = {'?', ':', '*', '<', '>', ',', ';', ' ', '\\', '/', '|'};
        for(int i = 0; i < name.length(); i++){
            for(char val : arr){
                if(name.charAt(i) == val){
                    return false;
                }
            } 
        }
        return true;
    }
}

