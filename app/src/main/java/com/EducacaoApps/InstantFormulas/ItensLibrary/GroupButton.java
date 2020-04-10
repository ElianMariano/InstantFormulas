package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import com.EducacaoApps.InstantFormulas.formulas.R;

/*
    Esta activity é uma activity que implementa o botão estilizado para grupo de formulas,
    com o objectivo de facilitar a utilização do mesmo dentro dos layouts de activity.
 */
public class GroupButton extends AppCompatButton {
    public GroupButton(Context context){
        super(context);
        init();
    }

    public GroupButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        // String que armazena o texto obtido do atributo XML
        String text;

        text = attributeSet.getAttributeValue("http://www.EducacaoApps.com", "text");

        if (text != null){
            if (text.contains("@"))
                text = getResources().getString(Integer.parseInt(text.substring(1)));

            // Define o texto do botão
            setText(text);
        }

        init();
    }

    void init(){
        // Define a altura do botão em DP
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                getResources().getDisplayMetrics());

        // Define os atributos do botão
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight((int) height);
        setTextColor(Color.parseColor("#FFFFFF"));
        setBackground(getResources().getDrawable(R.drawable.button_round));
    }
}
