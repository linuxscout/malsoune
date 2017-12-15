package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialdesign.DatabaseHelper1;
import info.androidhive.materialdesign.Expression;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.Speaker;

/**
 * Created by Ravi on 29/07/15.
 */
public class NeedFragment extends Fragment {

    private List<Expression> items1;
    private ArrayAdapter<Expression> adapter;
    private ListView listView1;
    private Speaker speaker;
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private int cpt;
    private DatabaseHelper1 db;

    public NeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

       /* boolean mboolean = false;

        SharedPreferences settings = getActivity().getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) {
            // do the thing for the first time
            db = new DatabaseHelper1(getActivity());
            Expression expression = new Expression();
            expression.name = getResources().getString(R.string.not);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.del);
            db.addStudentDetail(expression);
            settings = getActivity().getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();
        } else {
            // other time your app loads
        }*/

        db = new DatabaseHelper1(getActivity());

        if (db.getAllStudentsList().isEmpty()){
            Expression expression = new Expression();
            expression.name = getResources().getString(R.string.D11);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D21);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D31);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D41);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D51);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D61);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D71);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D81);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D91);
            db.addStudentDetail(expression);
            expression.name = getResources().getString(R.string.D101);
            db.addStudentDetail(expression);
        }

        items1 = new ArrayList<Expression>();
        // items1.add(new Expression(1, "Milk"));
        speaker = new Speaker(getActivity());
        //   Expression expression = new Expression();
        // expression.name = "moumou";
        db = new DatabaseHelper1(getActivity());
        // db.addStudentDetail(expression);
        items1 = db.getAllStudentsList();


        //  items1 = new ArrayList<Expression>();
        //  items1.add(new Expression(1, "Milk", 21.50));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_need, container, false);
        listView1 = (ListView) rootView.findViewById(R.id.listView11);
        button = (Button) rootView.findViewById(R.id.b51);
        button1 = (Button) rootView.findViewById(R.id.b61);
        button2 = (Button) rootView.findViewById(R.id.b71);
        button3 = (Button) rootView.findViewById(R.id.b77);
        editText = (EditText) rootView.findViewById(R.id.E11);
        editText2 = (EditText) rootView.findViewById(R.id.E21);
        editText3 = (EditText) rootView.findViewById(R.id.E3);
        editText4 = (EditText) rootView.findViewById(R.id.E4);
        adapter = new ArrayAdapter<Expression>(rootView.getContext(),
                android.R.layout.simple_list_item_1 , items1);

        cpt=2;





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expression expression = new Expression();
                expression.name = editText.getText().toString();
                db = new DatabaseHelper1(getActivity());
                db.addStudentDetail(expression);
                items1.add(db.getAllStudentsList().get(db.getAllStudentsList().size() - 1));
                // items1 = db.getAllStudentsList();
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), R.string.add2, Toast.LENGTH_LONG).show();
                //  listView1.setAdapter(adapter);

              /*  items1.add(new Expression(cpt, editText.getText().toString()));
                adapter.notifyDataSetChanged();
                cpt = cpt+1;*/

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = new DatabaseHelper1(getActivity());
                //  db.deleteEntry(Integer.parseInt(expression_id));
                // items1 = db.getAllStudentsList();
                //  if ((items1.isEmpty()) || (items1.size()-1<Integer.parseInt(expression_id))) {

                //   }else {
                //    items1.remove(Integer.parseInt(expression_id)-1);


                //   items1.remove(db.getAllStudentsList().get(db.getAllStudentsList().size() - 1));

                // int a = items1.indexOf(db.getStudent(Integer.parseInt(expression_id)));
                //  items1.remove(items1.indexOf(db.getStudent(Integer.parseInt(expression_id)))+1);

                //   items1.remove(items1.lastIndexOf(db.getStudent(Integer.parseInt(expression_id))));
                //  String expression_id = editText2.getText().toString();
                Dialog d = new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.confirme)
                        .setMessage(R.string.msgconf)
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // msg.delete();
                                String expression_id = editText2.getText().toString();
                                if (estUnEntier(expression_id)){
                                    Boolean bool = false;
                                    for (int i = 0; i < db.getAllStudentsList().size(); i++){
                                        if (db.getAllStudentsList().get(i).id == Integer.parseInt(expression_id)){
                                            bool = true;
                                        }
                                    }

                                    int a = -1;

                                    if (bool == true)   {
                                        for (int i = 0; i < items1.size(); i++)
                                            if (db.getStudent(Integer.parseInt(expression_id)).id == items1.get(i).id)
                                                a = i;


                                        items1.remove(a);
                                        adapter.notifyDataSetChanged();
                                        db.deleteEntry(Integer.parseInt(expression_id));
                                        Toast.makeText(getActivity(), R.string.del2, Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getActivity(), R.string.not, Toast.LENGTH_LONG).show();
                                    }

                                }else {
                                    Toast.makeText(getActivity(), R.string.notnumber, Toast.LENGTH_LONG).show();
                                }




                            }
                        })
                        .create();
                d.setOwnerActivity(getActivity()); // why can't the builder do this?
                d.show();


                //  items1.add(db.getAllStudentsList().get(db.getAllStudentsList().size()-1));
                // items1 = db.getAllStudentsList();

            }
            // }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = new DatabaseHelper1(getActivity());
                //  db.deleteEntry(Integer.parseInt(expression_id));
                // items1 = db.getAllStudentsList();
                //  if ((items1.isEmpty()) || (items1.size()-1<Integer.parseInt(expression_id))) {

                //   }else {
                //    items1.remove(Integer.parseInt(expression_id)-1);


                //   items1.remove(db.getAllStudentsList().get(db.getAllStudentsList().size() - 1));

                // int a = items1.indexOf(db.getStudent(Integer.parseInt(expression_id)));
                //  items1.remove(items1.indexOf(db.getStudent(Integer.parseInt(expression_id)))+1);

                //   items1.remove(items1.lastIndexOf(db.getStudent(Integer.parseInt(expression_id))));
                //  String expression_id = editText2.getText().toString();
                Dialog d = new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.confirme)
                        .setMessage(R.string.msgconf)
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // msg.delete();
                                String expression_id = editText3.getText().toString();
                                if (estUnEntier(expression_id)){
                                    Boolean bool = false;
                                    for (int i = 0; i < db.getAllStudentsList().size(); i++){
                                        if (db.getAllStudentsList().get(i).id == Integer.parseInt(expression_id)){
                                            bool = true;
                                        }
                                    }

                                    int a = -1;

                                    if (bool == true)   {
                                        for (int i = 0; i < items1.size(); i++)
                                            if (db.getStudent(Integer.parseInt(expression_id)).id == items1.get(i).id)
                                                a = i;


                                        items1.remove(a);
                                        Expression exp = new Expression();
                                        exp.name = editText4.getText().toString();
                                        exp.id = a+1;
                                        items1.add(a, exp);
                                        adapter.notifyDataSetChanged();
                                        db.updateEntry(db.getAllStudentsList().get(a), editText4.getText().toString());
                                        // db.deleteEntry(Integer.parseInt(expression_id));
                                        Toast.makeText(getActivity(), R.string.Mod2, Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getActivity(), R.string.notnumber, Toast.LENGTH_LONG).show();
                                    }

                                }else {
                                    Toast.makeText(getActivity(), R.string.notnumber, Toast.LENGTH_LONG).show();
                                }




                            }
                        })
                        .create();
                d.setOwnerActivity(getActivity()); // why can't the builder do this?
                d.show();


                //  items1.add(db.getAllStudentsList().get(db.getAllStudentsList().size()-1));
                // items1 = db.getAllStudentsList();

            }
            // }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (speaker.tts != null) {
                    speaker.tts.stop();
                    //   speaker.tts.shutdown();
                }
            }
            // }
        });


        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {

                final String item = ((TextView) view).getText().toString();

                //  Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();
                //TTS.speak(item, TextToSpeech.QUEUE_FLUSH, null);

                Dialog d = new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.speakexp)
                        .setMessage(R.string.speakexpQ)
                        .setNegativeButton(R.string.QSpeech, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                speaker.speaksimple(items1.get(position).name);
                                Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton(R.string.QSpeechM, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                speaker.speak(items1.get(position).name);
                                if (speaker.testcon==0) Toast.makeText(getActivity(), getResources().getString(R.string.conex), Toast.LENGTH_SHORT).show();
                                else Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();
                            }
                        })
                        .create();
                d.setOwnerActivity(getActivity()); // why can't the builder do this?
                d.show();

                //   speaker.speak(items1.get(position).name);


                //    tts.speak(item, TextToSpeech.QUEUE_FLUSH, null);

            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }



    private class LongOperation  extends AsyncTask<String, Void, Void> {

        // Required initialization

        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        private String OutputData = "";
        private String Error = null;
        // private ProgressDialog Dialog = new ProgressDialog(eSpeakActivity.this);
        // private TextToSpeech mTts;
        String data ="";
        String data1 ="";
        // TextView uiUpdate = (TextView) findViewById(R.id.output);
        // TextView jsonParsed = (TextView) findViewById(R.id.jsonParsed);
        int sizeData = 0;
        // EditText serverText = (EditText) findViewById(R.id.serverText);



        protected void onPreExecute(int position) {
            // NOTE: You can call UI Element here.

            //Start Progress Dialog (Message)

            //  Dialog.setMessage("Please wait..");
            //  Dialog.show();

            try{
                // Set Request parameter
                //  data +="&" + URLEncoder.encode("data", "UTF-8") + "="/*+serverText.getText()*/;
                data += "&"+ URLEncoder.encode("text", "UTF-8") + "=" +items1.get(position).name+ "&" + URLEncoder.encode("action", "UTF-8") + "="+ "Tashkeel2";
                data1 +="&" + URLEncoder.encode("action", "UTF-8") + "="+ "TashkeelText";
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {

            /************ Make Post Call To Web Server ***********/
            BufferedReader reader=null;

            // Send data
            try
            {

                // Defined URL  where to send data
                URL url = new URL(urls[0]);

                // Send POST data request

                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                // wr.write( data1 );
                wr.write(data);
                wr.flush();
                  /*  data = wr.toString()+ URLEncoder.encode("action","UTF-8") + "="+ "TashkeelText";
                    wr.write( data );
                    wr.flush();*/

                   /* URL url1 = new URL(urls[1]);
                    URLConnection conn1 = url1.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr1 = new OutputStreamWriter(conn1.getOutputStream());
                    wr1.write( data1 );
                    wr.flush();*/

                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "\n");
                }

                // Append Server Response To Content String
                Content = sb.toString();
            }
            catch(Exception ex)
            {
                Error = ex.getMessage();
                //        speaker.speak(items1.get(position).name);
            }
            finally
            {
                try
                {

                    reader.close();
                }

                catch(Exception ex) {}
            }

            /*****************************************************/
            return null;
        }

        protected void onPostExecute(Void unused) {
            // NOTE: You can call UI Element here.

            // Close progress dialog
            // Dialog.dismiss();

            if (Error != null) {

                //   uiUpdate.setText("Output : "+Error);

            } else {

                // Show Response Json On Screen (activity)
                //   uiUpdate.setText( Content );

                /****************** Start Parse Response JSON Data *************/


                JSONObject jsonResponse;

                try {

                    /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                    jsonResponse = new JSONObject(Content);

                    /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                    /*******  Returns null otherwise.  *******/
                    JSONArray jsonMainNode = jsonResponse.optJSONArray("result");

                    /*********** Process each JSON Node ************/

                    int lengthJsonArr = jsonMainNode.length();

                    for(int i=0; i < lengthJsonArr; i++)
                    {
                        /****** Get Object for each JSON node.***********/
                        JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                        //String result = jsonResponse.optString("result").toString();
                        //String order = jsonResponse.optString("order").toString();
                        /******* Fetch node values **********/
                        String chosen       = jsonChildNode.optString("chosen").toString();
                        //  String number     = jsonChildNode.optString("number").toString();
                        //  String date_added = jsonChildNode.optString("date_added").toString();



                       /*  OutputData += " Name 		    : "+ name +" \n "
                                     + "Number 		: "+ number +" \n "
                                     + "Time 				: "+ date_added +" \n "
                                     +"--------------------------------------------------\n";*/
                        OutputData += chosen + " " ;

                        //Log.i("JSON parse", song_name);
                    }
                    // OutputData += "0";

                    /****************** End Parse Response JSON Data *************/

                    //Show Parsed Output on screen (activity)
                    // jsonParsed.setText( OutputData );
                    speaker.speak(OutputData);


                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }


        }



        public String getOutputData (){
            return OutputData;
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (speaker.tts != null) {
            speaker.tts.stop();
            speaker.tts.shutdown();
        }
        super.onDestroy();
    }

    public void onStop() {
        // Don't forget to shutdown tts!
        if (speaker.tts != null) {
            speaker.tts.stop();
            speaker.tts.shutdown();
        }
        super.onStop();
    }

    public boolean estUnEntier(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }
}
