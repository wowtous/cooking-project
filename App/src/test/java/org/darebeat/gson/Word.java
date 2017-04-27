package org.darebeat.gson;

/**
 * Created by darebeat on 4/27/17.
 */
public class Word {
    private String name;
    private int count;

    public Word(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "name='" + name + "', count=" + count;
    }
}
