package com.jlwarren1.courseplayer;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ExpandableListView mListView;
    private List mModel;

    public ExpandableListAdapter(Context pContext, ExpandableListView pListView, List pModel){
        this.mContext = pContext;
        this.mListView = pListView;
        this.mModel = pModel;
    }

    /*public void addItem(DetailsModel item, SampleModel groupData){
        if(!mModel.contains(groupData)){
            mModel.add(groupData);
        }

        int ind = mModel.indexOf(groupData);
        List lstItems =  mModel.get(ind).getItems();
        lstItems.add(item);
        mModel.get(ind).setItems(lstItems);
    }*/

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List item = ((ExpandableTreeItem) mModel.get(groupPosition)).children;
        return item.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
                             ViewGroup parent) {
        ExpandableTreeItem item = (ExpandableTreeItem)getChild(groupPosition, childPosition);
        if(view == null){
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.list_submenu, null);
        }

        TextView txtCountry = (TextView)view.findViewById(R.id.submenu);
        txtCountry.setText(item.title);
        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.i("getChildrenCount:", ((ExpandableTreeItem) mModel.get(groupPosition)).size()+"");
        return ((ExpandableTreeItem) mModel.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mModel.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        Log.i("getGroupCount:", mModel.size()+ "");
        return mModel.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent) {
        ExpandableTreeItem model =  (ExpandableTreeItem)getGroup(groupPosition);
        if(view == null){
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.listheader, null);
        }

        TextView txtContinent = (TextView)view.findViewById(R.id.submenu);
        txtContinent.setText(model.title);
        return view;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return true;
    }

}