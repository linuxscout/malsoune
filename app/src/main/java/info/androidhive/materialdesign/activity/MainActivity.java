package info.androidhive.materialdesign.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import info.androidhive.materialdesign.R;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
   // ListView lv;
    //String temp[] = {"Gplus","Facebook","Instagram","Linkdin","Pintrest","Twitter","Snapchat","Skype"};
  //  Fragment fragment = new StudyFragment();

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
      /*  lv = (ListView) findViewById(R.id.listtest);
        lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, temp));
        lv.setTextFilterEnabled(true);*/


        // display the first navigation drawer view on app launch
        displayView(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.menu_main, menu);
       /* SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/
      //  return true;
     //  SearchManager searchManager = (SearchManager) getSystemService( Context.SEARCH_SERVICE );
     // android.widget.SearchView searchView = (android.widget.SearchView) menu.findItem(R.id.action_search).getActionView();

       // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
       // searchView.setSubmitButtonEnabled(true);
     //  searchView.setOnQueryTextListener(this);


        return super.onCreateOptionsMenu(menu);
    }

  /*  public boolean onQueryTextChange(String newText)
    {
        // Fragment fragment = new StudyFragment();
        // this is your adapter that will be filtered
        if (TextUtils.isEmpty(newText))
        {
            lv.clearTextFilter();
        }
        else
        {
            lv.setFilterText(newText.toString());
        }

        return true;
    }*/

   /* @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        return false;
    }*/

    //@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new StudyFragment();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new NeedFragment();
                title = getString(R.string.title_messages);
                break;
            case 3:
                fragment = new HellowFragment();
                title = getString(R.string.title_hellow);
                break;
            case 4:
                fragment = new TtsFragment();
                title = getString(R.string.title_tts);
                break;
            case 5:
                fragment = new TashkeelFragment();
                title = getString(R.string.title_tashkeel);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


}