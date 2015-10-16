package com.eduardodev.gameoflife;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * This view represents one item of the population.
 */
public class ItemView extends View {

    public ItemView(ViewGroup root) {
        super(root.getContext());
        setBackgroundResource(android.R.color.black);
    }

    public void setPosition(Position position) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        int itemSize = Math.round(getResources().getDimension(R.dimen.item_size));
        params.height = itemSize;
        params.width = itemSize;
        params.leftMargin = position.getX() * itemSize;
        params.topMargin = position.getY() * itemSize;
        setLayoutParams(params);
    }
}
