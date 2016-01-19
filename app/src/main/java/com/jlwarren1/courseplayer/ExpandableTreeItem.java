package com.jlwarren1.courseplayer;

import java.util.ArrayList;

/**
 * Created by jaredwarren on 1/19/2016.
 */
public class ExpandableTreeItem {

    public String title = "";
    public Boolean isLeaf = false;
    public ArrayList<ExpandableTreeItem> children;

    public ExpandableTreeItem(){
        this.children = new ArrayList<>();
    }

    public int size(){
        return this.children.size();
    }

}
