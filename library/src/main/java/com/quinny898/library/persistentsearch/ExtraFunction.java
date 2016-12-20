package com.quinny898.library.persistentsearch;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by hesk on 16年12月21日.
 */

public class ExtraFunction {
    @DrawableRes
    private int icon;
    private View.OnClickListener event;
    private ImageButton iButton;

    ExtraFunction(int ic, View.OnClickListener event) {
        this.icon = ic;
        this.event = event;
    }

    private void patchBackground(ImageButton button, Context mContext) {
        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        button.setBackgroundResource(backgroundResource);
      /*  if (color != 0) {
            int h = ContextCompat.getColor(mContext, color);
            button.setColorFilter(h);
        }*/
        typedArray.recycle();
    }

    public void setVisible(boolean b) {
        if (iButton == null) return;
        if (b) {
            iButton.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                iButton.setAlpha(0f);
                iButton.animate().alpha(1f);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                iButton.animate().alpha(0f).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        iButton.setVisibility(View.INVISIBLE);
                    }
                });
            } else {
                iButton.setVisibility(View.INVISIBLE);
            }
        }
        iButton.setEnabled(b);
    }

    public ImageButton make(Context mContext) {
        ContextThemeWrapper themecontext = new ContextThemeWrapper(mContext, R.style.actionTransparentSearchExtra);
        ImageButton button = new ImageButton(themecontext);
        //   button.setMaxWidth(mContext.getResources().getDimensionPixelOffset(R.dimen.icon_width));
        button.setImageResource(icon);
        button.setClickable(true);
        button.setOnClickListener(event);
        patchBackground(button, mContext);
        iButton = button;
        return button;
    }
}
