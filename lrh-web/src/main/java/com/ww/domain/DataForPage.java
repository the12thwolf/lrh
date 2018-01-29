package com.ww.domain;

/**
 * Created by Administrator on 2017/11/21.
 */
public class DataForPage {
    protected int start;
    protected int end;

    public int getStart() {
        return start;
    }

    public DataForPage(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "DataForPage{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
