package com.jlwarren1.courseplayer;

/**
 * Created by jaredwarren on 1/20/2016.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import org.json.JSONException;
import org.json.JSONObject;

public class TreeViewHolder extends TreeNode.BaseNodeViewHolder {
    public TreeViewHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode treeNode, Object o) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        JSONObject node = (JSONObject) o;
        View view = null;
        try {
            if (node.has("level")) {
                view = inflater.inflate(R.layout.chapter_title, ((MainActivity) context).treeContainer, false);
                TextView title = (TextView) view.findViewById(R.id.chapter_title);
                title.setText(node.getString("title"));
            } else {
                if (node.has("title")) {
                    view = inflater.inflate(R.layout.title, ((MainActivity) context).treeContainer, false);
                    TextView title = (TextView) view.findViewById(R.id.title);
                    title.setText(node.getString("title"));
                } else {
                    view = inflater.inflate(R.layout.text, ((MainActivity) context).treeContainer, false);
                    WebView text = (WebView) view.findViewById(R.id.text);
                    text.loadDataWithBaseURL("file:///android_asset/", node.getString("text"), "text/html; charset=UTF-8", "UTF-8", null);
                }
            }
        } catch (JSONException e) {
            Log.e("Error..........", e.getMessage());
            e.printStackTrace();
        }
        return view;
    }
}
