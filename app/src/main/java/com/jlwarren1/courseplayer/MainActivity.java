package com.jlwarren1.courseplayer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener{

    private DrawerLayout mDrawerLayout;
    RootAdapter mMenuAdapter;
    ExpandableListView expandableList;

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



        // Setup nav view
        expandableList = (ExpandableListView) findViewById(R.id.expanded_menu);



        mMenuAdapter = prepareListData();

        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);
        expandableList.setOnChildClickListener(this);

    }

    private RootAdapter prepareListData() {
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


        final ExpandableListView elv = (ExpandableListView) findViewById(R.id.expanded_menu);

        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                return true; /* or false depending on what you need */
            }
        });


        ExpandableListView.OnGroupClickListener grpLst = new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView eListView, View view, int groupPosition,
                                        long id) {

                return true; /* or false depending on what you need */
            }
        };


        ExpandableListView.OnChildClickListener childLst = new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView eListView, View view, int groupPosition,
                                        int childPosition, long id) {

                return true; /* or false depending on what you need */
            }
        };

        ExpandableListView.OnGroupExpandListener grpExpLst = new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        };
        return new RootAdapter(this, obj, grpLst, childLst, grpExpLst);
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
