package me.kashyap.masking.masking;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created on 7/20/2014.
 */
public class AlphaFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setAdapter(new AlphaAdapter(getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = "Alpha :" + ((position * 255) / 40) ;
                Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
