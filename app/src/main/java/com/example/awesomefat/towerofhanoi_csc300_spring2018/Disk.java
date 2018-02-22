package com.example.awesomefat.towerofhanoi_csc300_spring2018;

import android.widget.Button;

/**
 * Created by awesomefat on 2/15/18.
 */

public class Disk
{
    private int size;
    private Disk nextDisk;
    private Button diskVisual;

    public Disk(int size, Button diskVisual)
    {
        this.size = size;
        this.nextDisk = null;
        this.diskVisual = diskVisual;
    }

    public int getSize() {
        return size;
    }

    public Disk getNextDisk() {
        return nextDisk;
    }

    public void setNextDisk(Disk nextDisk) {
        this.nextDisk = nextDisk;
    }

    public Button getDiskVisual() {
        return diskVisual;
    }
}
