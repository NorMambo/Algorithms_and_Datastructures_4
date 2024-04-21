package HelperClasses;

import java.io.*;
import java.util.ArrayList;

public class CSVReaderWriter {
    private BufferedReader reader;

    public CSVReaderWriter() {}

    public void assignCSVFile(String filePath) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(filePath));
    }

//    public void printFileContent() throws IOException {
//        String line;
//        while((line = this.reader.readLine()) != null) {
//            System.out.println(this.reader.readLine());
//        }
//    }

    public InfoList getInfoArrays() throws IOException {
        ArrayList<String> sources = new ArrayList<>();
        ArrayList<String> destinations = new ArrayList<>();

        String line;
        while((line = this.reader.readLine()) != null) {

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) == ';') {
                    String subs = line.substring(0, i);
                    sources.add(subs);
                    subs = line.substring(i+1);
                    destinations.add(subs);
                    break;
                }
            }
        }
        return new InfoList(sources, destinations);
    }

    public void writeToFile(ArrayList<Double> info1, ArrayList<Integer> info2, String filepath) throws IOException {
         BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));

        for (int i = 0; i < info1.size(); i++) {
            double one = info1.get(i);
            int two = info2.get(i);
            writer.write(one + ";" + two + "\n");
        }

        writer.close();
    }

    public static void main(String[] args) throws IOException {
        CSVReaderWriter readerWriter = new CSVReaderWriter();
        readerWriter.assignCSVFile("src/data.txt");
        InfoList il = readerWriter.getInfoArrays();
        ArrayList<String> sources = il.get1stInfo();
        ArrayList<String> destincations = il.get2ndInfo();

        for (int i = 0; i < sources.size(); i++) {
            System.out.print(Integer.valueOf(sources.get(i)) + " - ");
            System.out.println(Double.valueOf(destincations.get(i)));
        }
    }
}
