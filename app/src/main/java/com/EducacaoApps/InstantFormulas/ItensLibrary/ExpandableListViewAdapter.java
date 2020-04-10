package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.EducacaoApps.InstantFormulas.ConvertStringtoData;
import com.EducacaoApps.InstantFormulas.formulas.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataGroup;
    private HashMap<String, List<String>> listDataChild;
    private LayoutInflater inflater;

    public ExpandableListViewAdapter(Context context, List<String> listDataGroup,
                                     HashMap<String, List<String>> listDataChild){
        this.context = context;
        this.listDataGroup = listDataGroup;
        this.listDataChild = listDataChild;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return this.listDataGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listDataChild.get(listDataGroup.get(i)).size();
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
        GroupButton group;

        if (view == null) {
            view = inflater.inflate(R.layout.expandable_group, null);

            group = view.findViewById(R.id.group);
            view.setTag(group);
        }
        else{
            group = view.findViewById(R.id.group);
        }

        group.setText(headerTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String childText = (String) getChild(i, i1);
        CheckItem checkItem;

        String[] data = ConvertStringtoData.SplitString(childText);

        if (view == null){
            view = inflater.inflate(R.layout.expandable_child, null);

            checkItem = view.findViewById(R.id.checkitem);
            view.findViewById(R.id.checkitem);
        }
        else{
            checkItem = view.findViewById(R.id.checkitem);
        }

        checkItem.setTitleEx(data[0]);

        if (!data[1].isEmpty())
            checkItem.setFormula(data[1]);
        else
            checkItem.setFormula("");

        checkItem.setDescription(data[2]);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
