package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.EducacaoApps.InstantFormulas.ConvertStringtoData;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private AppCompatActivity app;
    private List<String> listDataGroup;
    private HashMap<String, List<String>> listDataChild;

    public ExpandableListViewAdapter(AppCompatActivity app, List<String> listDataGroup,
                                     HashMap<String, List<String>> listDataChild){
        this.app = app;
        this.listDataGroup = listDataGroup;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this.listDataGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.listDataChild.get(this.listDataGroup.get(i))
                .size();
    }

    @Override
    public Object getGroup(int i) {
        return this.listDataGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.listDataChild.get(this.listDataGroup.get(i))
                .get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);

        GroupButton button = new GroupButton(app);
        button.setText(headerTitle);

        view = button;

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String childText = (String) getChild(i, i1);

        String[] data = ConvertStringtoData.SplitString(childText);

        CheckItem checkItem = new CheckItem(app);
        checkItem.setTitleEx(data[0]);

        if (!data[1].isEmpty())
            checkItem.setFormula(data[1]);
        else
            checkItem.setFormula(null);

        checkItem.setDescription(data[2]);

        view = checkItem;

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
