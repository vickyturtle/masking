package me.kashyap.masking.masking;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created on 7/20/2014.
 */
public class BaseFragment extends Fragment {
    protected GridView gridView;

    public static BaseFragment newInstance(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AlphaFragment();
                break;

            case 1:
                fragment = new MaskFragment();
                break;

            case 2:
                fragment = new ShapeFragment();
                break;

        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_list, container, false);
        gridView = (GridView) parent.findViewById(R.id.grid_view);
        return parent;
    }

}
