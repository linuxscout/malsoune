package info.androidhive.materialdesign;

import android.content.Context;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;

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

/**
 * Created by condor on 25/04/2016.
 */
public class Speaker implements TextToSpeech.OnInitListener {

    public TextToSpeech tts;
    public int testcon =1;


    public  Speaker(Context context){

        tts = new TextToSpeech(context, this);
        //  return tts;
    }
    public void speaksimple(String text){
        tts.speak(text, TextToSpeech.QUEUE_ADD, null);
    }

    public void speak(final String text){

     //   tts.speak(text, TextToSpeech.QUEUE_ADD, null);



         class LongOperation  extends AsyncTask<String, Void, Void> {

            // Required initialization

            private final HttpClient Client = new DefaultHttpClient();
            private String Content;
            private String OutputData = "";
            private String Error = null;
            //  private ProgressDialog Dialog = new ProgressDialog(eSpeakActivity.this);
            // private TextToSpeech mTts;
            String data ="";
            String data1 ="";
            // TextView uiUpdate = (TextView) findViewById(R.id.output);
            // TextView jsonParsed = (TextView) findViewById(R.id.jsonParsed);
            int sizeData = 0;
            // EditText serverText = (EditText) findViewById(R.id.serverText);



            protected void onPreExecute() {
                // NOTE: You can call UI Element here.

                //Start Progress Dialog (Message)

                //    Dialog.setMessage("Please wait..");
                //   Dialog.show();

                try{
                    // Set Request parameter
                    //  data +="&" + URLEncoder.encode("data", "UTF-8") + "="/*+serverText.getText()*/;
                    data += "&"+ URLEncoder.encode("text","UTF-8") + "=" +text+ "&" + URLEncoder.encode("action", "UTF-8") + "="+ "Tashkeel2";
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
                    testcon =0;
                   // tts.speak(text, TextToSpeech.QUEUE_ADD, null);
                    //  speaker.speak(items1.get(i).name);
                }
                finally
                {
                    try
                    {

                        reader.close();
                    }

                    catch(Exception ex) {

                    }
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
                        tts.speak(OutputData, TextToSpeech.QUEUE_ADD, null);
                        testcon =1;
                        // OutputData += "0";

                        /****************** End Parse Response JSON Data *************/

                        //Show Parsed Output on screen (activity)
                        // jsonParsed.setText( OutputData );



                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                }


            }



            public String getOutputData (){
                return OutputData;
            }

        }

       String serverURL = "http://tahadz.com/mishkal/ajaxGet";
       new LongOperation().execute(serverURL);

    }
    @Override
    public void onInit(int status) {

    }
}
