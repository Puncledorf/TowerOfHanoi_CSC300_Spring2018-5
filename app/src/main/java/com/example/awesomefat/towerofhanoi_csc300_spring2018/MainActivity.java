package com.example.awesomefat.towerofhanoi_csc300_spring2018;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Tower[] towers = new Tower[3];
    private Disk placeholder = null;
    private ViewGroup placeholderVG;
    private Button[] towerButtons = new Button[3];
    private boolean shouldPop = true;
    private int numMoves = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.towerButtons[0] = (Button)this.findViewById(R.id.tower0Button);
        this.towerButtons[1] = (Button)this.findViewById(R.id.tower1Button);
        this.towerButtons[2] = (Button)this.findViewById(R.id.tower2Button);

        this.placeholderVG = (ViewGroup)this.findViewById(R.id.placeholderVG);

        Button disk0 = (Button)this.findViewById(R.id.disk0);
        Button disk1 = (Button)this.findViewById(R.id.disk1);
        Button disk2 = (Button)this.findViewById(R.id.disk2);

        Disk d0 = new Disk(0, disk0);
        Disk d1 = new Disk(1, disk1);
        Disk d2 = new Disk(2, disk2);

        ViewGroup tower0 = (ViewGroup)this.findViewById(R.id.tower0);
        ViewGroup tower1 = (ViewGroup)this.findViewById(R.id.tower1);
        ViewGroup tower2 = (ViewGroup)this.findViewById(R.id.tower2);

        Tower t0 = new Tower(tower0);
        Tower t1 = new Tower(tower1);
        Tower t2 = new Tower(tower2);

        this.towers[0] = t0;
        this.towers[1] = t1;
        this.towers[2] = t2;

        //initialize our game
        tower0.removeAllViews();
        this.towers[0].push(d2);
        this.towers[0].push(d1);
        this.towers[0].push(d0);
    }

    private void tryToPopFromTower(int towerIndex)
    {
        Disk temp = this.towers[towerIndex].pop();
        if(temp != null)
        {
            this.placeholder = temp;
            this.placeholderVG.addView(temp.getDiskVisual());
            this.shouldPop = false;
            for(Button b : this.towerButtons)
            {
                b.setText("PUSH");
            }
            this.numMoves++;
            TextView t = this.findViewById(R.id.moves);
            t.setText(numMoves+"");
        }
    }

    private void tryToPushToTower(int towerIndex)
    {
        Disk temp = this.towers[towerIndex].peek();
        if(temp == null || this.placeholder.getSize() < temp.getSize())
        {
            this.placeholderVG.removeAllViews();
            this.towers[towerIndex].push(this.placeholder);
            this.placeholder = null;
            this.shouldPop = true;
            for(Button b : this.towerButtons)
            {
                b.setText("POP");
            }
            this.numMoves++;
            TextView t = this.findViewById(R.id.moves);
            t.setText(numMoves+"");
            if(towers[2].check()=="y"){
                for(Button b: this.towerButtons) {
                    b.setText("DISABLED");
                }
                Toast.makeText(this, "Conglaturations! A Winner Is You!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Disk size is greater than that already on the tower", Toast.LENGTH_SHORT).show();

        }
    }

    public void tower0ButtonPressed(View v)
    {
        if(towerButtons[0].getText().equals("DISABLED")){

        }else if(this.shouldPop)
        {
            this.tryToPopFromTower(0);
        }
        else
        {
            this.tryToPushToTower(0);
        }
    }

    public void tower1ButtonPressed(View v)
    {
        if(towerButtons[0].getText().equals("DISABLED")) {

        } else if(this.shouldPop)
        {
            this.tryToPopFromTower(1);
        }
        else
        {
            this.tryToPushToTower(1);
        }
    }

    public void tower2ButtonPressed(View v)
    {
        if(towerButtons[0].getText().equals("DISABLED")) {

        } else if(this.shouldPop)
        {
            this.tryToPopFromTower(2);
        }
        else
        {
            this.tryToPushToTower(2);
        }
    }
}
