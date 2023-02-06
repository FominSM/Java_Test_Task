package com.example;
import java.util.Arrays;

public class MergeSort {
    private int[] dataArray;
    
    public MergeSort(int[] dataArray) {
        this.dataArray = dataArray;
    }

    private int[] mergeSort(int[] sortArr){
        int[] buffer1 = Arrays.copyOf(sortArr, sortArr.length);
        int[] buffer2 = new int[sortArr.length];
        int[] result = mergeSortInner(buffer1, buffer2, 0, sortArr.length);
        return result;
    }

    private int[] mergeSortInner(int[] buffer1, int[] buffer2, int startIndex, int endIndex){
        if (startIndex >= endIndex - 1){
            return buffer1;
        }

        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);

        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;

        while (index1 < middle && index2 < endIndex){
            result[destIndex++] = sorted1[index1] < sorted2[index2] ? sorted1[index1++] : sorted2[index2++];
        }

        while (index1 < middle){
            result[destIndex++] = sorted1[index1++];
        }

        while (index2 < endIndex){
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }
    
    public int[] start(String mode) {
        int[] descendingArray = new int[dataArray.length];

        if(mode.equals("-a")){
            System.out.println("\t+ Data sorting was successful");
            return mergeSort(dataArray);
        }
        else{
            int[] data = mergeSort(dataArray);
            for (int i = data.length-1; i > 0;) {
                for (int j = 0; j < data.length; j++) {
                    descendingArray[j] = data[i];
                    i--;
                }
            }
            System.out.println("\t+ Data sorting was successful");
            return descendingArray;
        }
    }   
}
