package org.darebeat.statistic;

import org.darebeat.gson.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by darebeat on 4/27/17.
 * 词频统计top 10
 */
public class wordFreq {
    public static void main(String[] args) {
        wordFreq wf = new wordFreq();
        String filepath = wordFreq.class.getResource("/freq.txt").getPath();
        HashMap<String,Integer> hm = wf.getWordCount(wf.getStrings(filepath));
        List<Word> rs = new ArrayList<Word>();
        for (String key: hm.keySet()){
            rs.add(new Word(key,hm.get(key)));
        }
        Collections.sort(rs, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                int result = 0 ;
                if (o1.getCount() > o2.getCount() ) {
                    result = -1;
                }else if (o1.getCount() < o2.getCount()) {
                    result = 1;
                }else {
                    int strRs = o1.getName().compareToIgnoreCase(o2.getName());
                    if ( strRs > 0 ) {
                        result = 1;
                    }else {
                        result = -1 ;
                    }
                }
                return result;
            }
        });

        for (int i = 0; i < (rs.size()>10 ? 10 : rs.size()); i++) {
            System.out.println(rs.get(i).toString());
        }
    }

    public String getStrings(String filepath){
        String words = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            while (br.readLine() != null){
                words += br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public HashMap<String,Integer> getWordCount(String str){
        HashMap<String,Integer> hm = new HashMap<String, Integer>();

        String strs[] = str.trim().split("[\\s\\{\\}\\(\\)_）（,-=;。 、，：]+");

        for (String s: strs){
            if (hm.get(s) != null){
                int v = hm.get(s).intValue();
                hm.put(s,new Integer(v+1));
            }else{
                hm.put(s,new Integer(1));
            }
        }
        return hm;
    }

}
