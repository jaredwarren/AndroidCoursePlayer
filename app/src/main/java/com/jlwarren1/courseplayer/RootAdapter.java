package com.jlwarren1.courseplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * Created by jaredwarren on 1/20/2016.
 */
public class RootAdapter extends BaseExpandableListAdapter {

    private ExpandableTreeItem root;

    private final LayoutInflater inflater;

    public class Entry {
        public final CustExpListview cls;
        public final SecondLevelAdapter sadpt;

        public Entry(CustExpListview cls, SecondLevelAdapter sadpt) {
            this.cls = cls;
            this.sadpt = sadpt;
        }
    }

    public Entry[] lsfirst;

    public RootAdapter(Context context, ExpandableTreeItem root, ExpandableListView.OnGroupClickListener grpLst,
                       ExpandableListView.OnChildClickListener childLst, ExpandableListView.OnGroupExpandListener grpExpLst) {
        this.root = root;
        this.inflater = LayoutInflater.from(context);

        lsfirst = new Entry[root.children.size()];

        for (int i = 0; i < root.children.size(); i++) {
            final CustExpListview celv = new CustExpListview(context);
            SecondLevelAdapter adp = new SecondLevelAdapter(root.children.get(i),context);
            celv.setAdapter(adp);
            celv.setGroupIndicator(null);
            celv.setOnChildClickListener(childLst);
            celv.setOnGroupClickListener(grpLst);
            celv.setOnGroupExpandListener(grpExpLst);

            lsfirst[i] = new Entry(celv, adp);
        }

    }

    @Override
    public ExpandableTreeItem getChild(int groupPosition, int childPosition) {
        return root.children.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        // second level list
        return lsfirst[groupPosition].cls;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public ExpandableTreeItem getGroup(int groupPosition) {
        return root.children.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return root.children.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        // first level
        View layout = convertView;
        GroupViewHolder holder;
        final ExpandableTreeItem item = this.getGroup(groupPosition);

        if (layout == null) {
            layout = inflater.inflate(R.layout.listheader, parent, false);
            holder = new GroupViewHolder();
            holder.title = (TextView) layout.findViewById(R.id.submenu);
            layout.setTag(holder);
        } else {
            holder = (GroupViewHolder) layout.getTag();
        }

        holder.title.setText(item.title.trim());

        return layout;
    }

    private static class GroupViewHolder {
        TextView title;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class CustExpListview extends ExpandableListView
    {

        int intGroupPosition, intChildPosition, intGroupid;

        public CustExpListview(Context context)
        {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}