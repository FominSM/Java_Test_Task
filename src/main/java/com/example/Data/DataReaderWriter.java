package com.example.Data;
import java.io.*;
import java.util.*;


public class DataReaderWriter {
    private String modeSort; 
    private String typeData;
    private String nameNewFile;
    private String inputFilenames;

    public DataReaderWriter(String modeSort, String typeData, String nameNewFile, String inputFilenames) {
        this.modeSort = modeSort;
        this.typeData = typeData;
        this.nameNewFile = nameNewFile;
        this.inputFilenames = inputFilenames;
    }

    public int[] readDataFromFiles(){
        int[] resultArr = new int[0];
        switch (typeData) {
            case "-s":
                List<String> dataStr = getListData("string");
                if(dataStr != null){
                    resultArr = dataValidation(dataStr, typeData);
                }
                break; 
            case "-i":
                List<String> dataInt = getListData("integer");
                if(dataInt != null){
                    resultArr = dataValidation(dataInt, typeData);
                }
                break;        
        }
        return resultArr;
    }

    public void writeDataNewFile(int[] data){
        try {
            FileWriter writer = new FileWriter("src/main/java/com/example/" + nameNewFile, false);
            if(typeData.equals("-s")){
                for(int text: data){
                    char value = (char) text;
                    writer.write(value + "\n");
                    writer.flush();
                }
            }
            else{
                for(int text: data){
                    writer.write(Integer.toString(text) + "\n");
                    writer.flush();
                }
            }
            writer.close();
        } catch (Exception e) {         
        }    
        System.out.printf("\t+ File %s successfully created - src/main/java/com/example/", nameNewFile);
    }

    private List<String> getListData(String dataType){
        List<String> listData = new ArrayList<>();
        String[] nameFiles = inputFilenames.split(" ");
        String pathToFiles = new String();

        if(dataType.equals("string")){
            pathToFiles = "src/main/java/com/example/Data/DataSTR";
        }
        else {
            pathToFiles = "src/main/java/com/example/Data/DataINT";
        }

        for(String name: nameFiles){
            try{
                FileReader reader = new FileReader(pathToFiles + "/" + name);
                Scanner scanFR = new Scanner(reader);
                while (scanFR.hasNextLine()) {
                    listData.add(scanFR.nextLine());                   
                }
                scanFR.close();
            }
            catch(IOException ex){
                System.out.println("\tERROR - " + ex.getMessage());
            }
        }

        if(listData.size() != 0){
            System.out.println("\t+ Data from files read successfully");
            return listData;
        }
        else{
            return null;
        }
    }
    
    private int[] dataValidation(List<String> data, String mode){
        List<Integer> verifiedData = new ArrayList<>(); 

        if(mode.equals("-s")){
            for(String value: data){
                if(value.length() == 1 && value.matches("^[a-zA-Z]*$"))
                    try {
                        int i = String.valueOf(value).getBytes()[0];
                        verifiedData.add(i);
                    } catch (Exception e) { 
                    }
                
            }
        }
        else{
            for(String value: data){
                try {
                    int verifiedValue = Integer.parseInt(value);
                    verifiedData.add(verifiedValue);
                } catch (Exception e) {
                }
            }
        }

        int[] resultData = new int[verifiedData.size()];
        for(int i = 0; i < resultData.length; i++){
            resultData[i] = verifiedData.get(i);
        }
        System.out.println("\t+ The input data has been validated");
        return resultData;
    }

    public String getModeSort() {
        return modeSort;
    }

}
