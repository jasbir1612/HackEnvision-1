package com.cereal_killers.hackenvsion;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Jasbir Singh on 2/20/2016.
 */
public class ViewPagerAdapter extends PagerAdapter {

    ImageView single_image;
    private Button next;
    int[] pager_images = {R.drawable.image2, R.drawable.image2, R.drawable.image2};
    private Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pager_images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view== (RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        LayoutInflater pager_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_image = pager_inflater.inflate(R.layout.swipe_pager_layout, container, false);
        single_image = (ImageView) item_image.findViewById(R.id.pager_image);
        single_image.setImageResource(pager_images[position]);
        next = (Button) item_image.findViewById(R.id.next);
        container.addView(item_image);
        container.getId();

        return item_image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
