package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.androidhive.materialdesign.DatabaseHelper2;
import info.androidhive.materialdesign.Expression;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.Speaker;

/**
 * Created by condor on 03/05/2016.
 */
public class TtsFragment extends android.support.v4.app.Fragment {

    private Button button1;
    private Button button2;
    private Button button3;
    private EditText editText;
    private Speaker speaker;
    public TtsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        speaker = new Speaker(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tts, container, false);

        button1 = (Button) rootView.findViewById(R.id.bt1);
        button2 = (Button) rootView.findViewById(R.id.bt2);
        button3 = (Button) rootView.findViewById(R.id.bt1M);
        editText = (EditText) rootView.findViewById(R.id.edit);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               speaker.speaksimple(editText.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speaker.speak(editText.getText().toString());
                if (speaker.testcon==0) Toast.makeText(getActivity(), getResources().getString(R.string.conex), Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (speaker.tts != null) {
                    speaker.tts.stop();
                    //   speaker.tts.shutdown();
                }
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
