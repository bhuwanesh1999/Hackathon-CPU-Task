package com.Hackathon.CPU;

import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class Main {
    public static void main(String args[]) {
        try{
            JSONArray json = new JSONArray();
            JSONObject transactionObject = new JSONObject();
            JSONObject valuesObject = new JSONObject();
            JSONObject values = new JSONObject();
            double maxCpu = Double.MIN_VALUE, avgCpu = 0.0, total = 0.0;

            BufferedReader br = new BufferedReader(new FileReader(new File("./CPU.txt")));

            String s;
            int count=0;
            while((s = br.readLine()) != null) {
                String[] arr = split(s);
                double current = Double.parseDouble (  String.format (  "%.2f", Double.parseDouble ( arr[8] )));
                if(current > maxCpu ) maxCpu = current;
                total+=current;
                values.put ( (++count)+"s",arr[9] );
            }
            valuesObject.put("values",values);
            valuesObject.put ( "maxcpu",String.valueOf ( maxCpu ) );
            valuesObject.put ( "avgcpu",String.valueOf ( (maxCpu/count) ) );
            transactionObject.put("transaction",valuesObject);
            json.add(transactionObject);
            System.out.println(json);
            writeIntoFile ( json.toString () );

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] split(String s){
        ArrayList<String> values = new ArrayList<String>();
        String temp = "";
        for(char c:s.toCharArray()) {
            if(c==' ' && temp.length()>0) {
                values.add(temp);
//                System.out.println(temp);
                temp = "";
            }
            if(c!=' ') temp += c;
        }
        String[] result = new String[values.size()];
        for(int i=0; i<values.size(); i++) {
            result[i] = values.get(i);
        }
        return result;
    }

    public static void writeIntoFile(String s) {
        try {
            FileWriter writer = new FileWriter("CPU.json");
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
