package me.kashyap.masking.masking;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created on 7/20/2014.
 */
public class MaskFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setAdapter(new DuffAdapter(getActivity(), false));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = DuffAdapter.getMode(position / 19).name() + ", " + DuffAdapter.getMode(position % 19).name() ;
                Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
