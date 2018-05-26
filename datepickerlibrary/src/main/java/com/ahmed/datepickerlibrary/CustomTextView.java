package com.ahmed.datepickerlibrary;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Ahmed Said on 26,05,2018
 */
public class CustomTextView extends AppCompatTextView {

    private Context mContext;

    public CustomTextView(Context context) {
        super(context);
        initViews(context, null, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(context, attrs, defStyle);
    }

    private void initViews(Context context, AttributeSet attrs, int defStyle) {

        mContext = context;

        if (isInEditMode()) {
            return;
        }
        setFonts(attrs, defStyle);

    }

    private void setFonts(AttributeSet attrs, int defStyle) {
//        Typeface robotoTypeface = CustomTypefaceManager.obtainTypeface(mContext, attrs, defStyle);
//        setTypeface(robotoTypeface);
    }
    private void setFont(String fontPath) {
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), fontPath);
        setTypeface(typeface);
    }
}
