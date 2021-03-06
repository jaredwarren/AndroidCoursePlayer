package com.jlwarren1.courseplayer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener{

    private DrawerLayout mDrawerLayout;
    ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;

    RelativeLayout treeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Setup toolbar action button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();



        // TODO:figure out how to add tap style, figure out how to indent children, add icon....






        // Tree stuff
        AndroidTreeView tView = new AndroidTreeView(this, new ContentMaker(this).root);
        tView.setDefaultViewHolder(TreeViewHolder.class);
        treeContainer = (RelativeLayout) findViewById(R.id.tree_container);
        treeContainer.addView(tView.getView());
        tView.expandLevel(1);


       /* TreeItemHolder.IconTreeItem nodeItem = new TreeItemHolder.IconTreeItem();
        nodeItem.text = "TEST...";
        TreeNode parent = new TreeNode(nodeItem).setViewHolder(new TreeItemHolder(this));


        TreeNode root = TreeNode.root();
        //TreeNode parent = new TreeNode("MyParentNode");
        TreeNode child0 = new TreeNode("ChildNode0");
        TreeNode child1 = new TreeNode("ChildNode1");
        TreeNode child2 = new TreeNode("ChildNode1");
        TreeNode parent1 = new TreeNode("ChildParentNode1");
        parent1.addChildren(child2);
        parent.addChildren(parent1, child0, child1);
        root.addChild(parent);

        TreeNode childX = new TreeNode("ChildNodeX");
        root.addChild(childX);


        AndroidTreeView tView = new AndroidTreeView(this, root);
        ViewGroup navView = (ViewGroup) findViewById(R.id.tree_view);
        navView.addView(tView.getView());*/



        /*// Setup nav view
        expandableList = (ExpandableListView) findViewById(R.id.expanded_menu);



        mMenuAdapter = prepareListData();

        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);
        expandableList.setOnChildClickListener(this);*/

    }

    private ExpandableListAdapter prepareListData() {
        //listDataHeader = new ArrayList<>();
        //listDataChild = new HashMap<>();

        ExpandableTreeItem  obj = new ExpandableTreeItem();
        obj.children =  new ArrayList<>();
        for(int i = 0;i<Constant.state.length;i++)
        {
            ExpandableTreeItem root =  new ExpandableTreeItem();
            root.title = Constant.state[i];
            root.children =  new ArrayList<>();
            for(int j=0;j<Constant.parent[i].length;j++)
            {
                ExpandableTreeItem parent =  new ExpandableTreeItem();
                parent.title=Constant.parent[i][j];
                parent.children =  new ArrayList<>();
                for(int k=0;k<Constant.child[i][j].length;k++)
                {
                    ExpandableTreeItem child =  new ExpandableTreeItem();
                    child.title =Constant.child[i][j][k];
                    parent.children.add(child);
                }
                root.children.add(parent);
            }
            obj.children.add(root);
        }


        /*ExpandableTreeItem root = new ExpandableTreeItem();
        root.isLeaf = false;
        root.title = "root";
        root.children =  new ArrayList<>();

        ExpandableTreeItem p1 = new ExpandableTreeItem();
        p1.isLeaf = true;
        p1.title = "Page 1";
        root.children.add(p1);

        ExpandableTreeItem topic1 = new ExpandableTreeItem();
        topic1.isLeaf = false;
        topic1.title = "Topic 1";
        topic1.children =  new ArrayList<>();

        ExpandableTreeItem p2 = new ExpandableTreeItem();
        p2.isLeaf = true;
        p2.title = "Page 2";
        topic1.children.add(p2);

        root.children.add(topic1);

        // TODO: figure out how to make page in root not expandable, and sub-topic expandable in topic

        ExpandableTreeItem topic2 = new ExpandableTreeItem();
        topic2.isLeaf = false;
        topic2.title = "Topic 2";
        topic2.children =  new ArrayList<>();


        ExpandableTreeItem topic2a = new ExpandableTreeItem();
        topic2a.isLeaf = false;
        topic2a.title = "Topic 2a";
        topic2a.children =  new ArrayList<>();

        ExpandableTreeItem p3 = new ExpandableTreeItem();
        p3.isLeaf = true;
        p3.title = "Page 3";
        topic2a.children.add(p3);

        topic2.children.add(topic2a);
        root.children.add(topic2);*/


        /*ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName("heading1");
        item1.setIconImg(R.drawable.ic_menu_gallery);
        // Adding data header
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName("heading2");
        item2.setIconImg(R.drawable.ic_menu_camera);
        listDataHeader.add(item2);

        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName("heading3");
        item3.setIconImg(R.drawable.ic_menu_send);
        listDataHeader.add(item3);

        // Adding child data
        List<String> heading1 = new ArrayList<>();
        heading1.add("Submenu of item 1");

        List<String> heading2 = new ArrayList<>();
        heading2.add("Submenu of item 2");
        heading2.add("Submenu of item 2");
        heading2.add("Submenu of item 2");

        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);*/

        ExpandableListAdapter menuAdapter = new ExpandableListAdapter(this, expandableList, obj.children );
        return menuAdapter;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("-", "on tap:" + item.toString());
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Log.i("...", "on tap:"+v.toString());
        //item.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
    }

}
