package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class ExpandView extends ExpandableListView {
    public ExpandView(Context context){
        super(context);
    }

    public ExpandView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
