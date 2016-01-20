package com.jlwarren1.courseplayer;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * Created by jaredwarren on 1/20/2016.
 */
public class SecondLevelAdapter extends BaseExpandableListAdapter {

    public ExpandableTreeItem child;
    Context mContext;
    LayoutInflater inflater;

    public SecondLevelAdapter(ExpandableTreeItem child,Context context) {
        this.child = child;
        this.mContext=context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ExpandableTreeItem getChild(int groupPosition, int childPosition) {
        return child.children.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    // third level
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        final ExpandableTreeItem item =  this.getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_submenu, parent, false);
        }

        TextView txtCountry = (TextView)convertView.findViewById(R.id.submenu);
        txtCountry.setText(item.title);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.children.get(groupPosition).children.size();
    }

    @Override
    public ExpandableTreeItem getGroup(int groupPosition) {
        return child.children.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return child.children.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // Second level
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ExpandableTreeItem item = this.getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listheader, parent, false);
        }

        TextView txtContinent = (TextView)convertView.findViewById(R.id.submenu);
        txtContinent.setText(item.title);

        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        Log.d("SecondLevelAdapter", "Unregistering observer");
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
