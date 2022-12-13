/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import re.devboxx.smsconverter.R;
import tools.ThreadTest;
import utils.UtilsApp;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, android.app.LoaderManager.LoaderCallbacks<Cursor>
{

    private DrawerLayout mDrawerLayout;
    //private MainAdapter threadAdapter;
    private  CursorLoader cursorLoaderThread;
    public static MatrixCursor matrixCursorThread;
    private Context context;
    private static final int CONTACT_LOADER_ID = 1;
    private Boolean doubleBackToExitPressedOnce = true;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private FloatingActionButton fab ;
    private MainAdapter threadAdapter;
    private MenuItem searchItem;
    private SearchView searchView;
    private ProgressBar progressBar;
    private TextView noThread;
    private int paddingRightImageLogoToolBar  = 60 ;
    private int paddingBottomImageLogoToolBar = 40;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this;

        setContentView(R.layout.activity_main2);
        recyclerView = (RecyclerView) findViewById(R.id.threadList);

        /*----------- ProgressBar ----------------------*/
        progressBar  =  (ProgressBar) findViewById(R.id.progressBar);
        Drawable draw=context.getResources().getDrawable(R.drawable.circular_progress_bar);
        progressBar.setProgressDrawable(draw);
        progressBar.setVisibility(View.VISIBLE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNestedScrollingEnabled(false); // Empeche de cacher le toolbar

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // Adding Toolbar to Main screen

        //setSupportActionBar(toolbar);

        setInitialConfiguration();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            //TODO TRUE --> menu vertical à gauche apparait
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setDisplayShowHomeEnabled(true);
           // supportActionBar.setIcon(R.drawable.ic_launcher);
            supportActionBar.setLogo(R.drawable.ic_launcher);
        }
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);

                        int menuChoice = menuItem.getItemId();
                        menuChoiceClicked(menuChoice);

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        // Adding Floating Action Button to bottom right of main view
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Snackbar!",
                        Snackbar.LENGTH_LONG).show();
            }
        });
        fab.setBackgroundColor(getResources().getColor(R.color.PurpleSMSConverter));
        fab.setRippleColor(getResources().getColor(R.color.PurpleSMSConverter));
        fab.setVisibility(View.INVISIBLE);
        getLoaderManager().initLoader(CONTACT_LOADER_ID, null, MainActivity.this);

        for ( int i = 0 ; i < toolbar.getChildCount(); i++)
        {
            String g = toolbar.getChildAt(i).getClass().getSimpleName().toString();

            if (toolbar.getChildAt(i).getClass() == AppCompatImageView.class)
            {
                //Toolbar.LayoutParams lParam  = new Toolbar.LayoutParams(Toolbar.LayoutParams.);
                AppCompatImageView imViewLogo = (AppCompatImageView)toolbar.getChildAt(i);
                imViewLogo.setPadding(0,0,paddingRightImageLogoToolBar,paddingBottomImageLogoToolBar);

            }
        }
        noThread = (TextView) findViewById(R.id.noThread);
        noThread.setVisibility(View.INVISIBLE);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null)
        {
            searchItem.setVisible(true);
        }
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            context.startActivity(new Intent(context, AboutActivity.class));
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    /* ---------------------LOADER ------------------------------------------------------- */
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {

        final String conversationsProvider = "content://tools.ConversationSmsContactProvider/";
        Uri uri = Uri.parse(conversationsProvider);
        cursorLoaderThread = new CursorLoader(this, uri, null, null, null, "date desc");

        return cursorLoaderThread;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {


        //TODO A Supprimer --->  mettre un message "Aucune Conversation"
        if (matrixCursorThread.getCount() == 0)
        {
            ThreadTest.FillCursor();
            //matrixCursorThread = ThreadTest.CursorThread;
            noThread.setVisibility(View.VISIBLE);
        }

        threadAdapter = new MainAdapter(matrixCursorThread,context );

        progressBar.setVisibility(View.INVISIBLE);
        //linearProgress.setVisibility(View.INVISIBLE);
        //fastScroller.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(threadAdapter);
        //pullToRefreshView.setEnabled(false);
        //progressWheel.setVisibility(View.GONE);
        //TODO a voir pour la search zone
       /* if (searchItem != null)
        {
            searchItem.setVisible(true);
        }


        fastScroller.setRecyclerView(recyclerView);
        recyclerView.setOnScrollListener(fastScroller.getOnScrollListener());

        setPullToRefreshView(pullToRefreshView);
        drawer.closeDrawer();
        drawer = UtilsUI.setNavigationDrawer((Activity) context, context, toolbar,threadAdapter,  recyclerView);
        */

    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // adapterTheadListView.swapCursor(null);

    }
    /* ----------------------TEXT SEARCH ------------------------------------------------- */


    @Override
    public boolean onQueryTextChange(String search) {
        if (search.isEmpty()) {
            ((MainAdapter) recyclerView.getAdapter()).getFilter().filter("");
        } else {
            ((MainAdapter) recyclerView.getAdapter()).getFilter().filter(search.toLowerCase());
        }

        return false;
    }

    public static void setResultsMessage(Boolean result) {
        if (result) {
            //noResults.setVisibility(View.VISIBLE);
            //fastScroller.setVisibility(View.GONE);
        } else {
           // noResults.setVisibility(View.GONE);
           // fastScroller.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    @Override
    public void onResume()
    {
        super.onResume();


    }
    @Override
    public void onBackPressed() {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
                return;
            }


    }

    private void setInitialConfiguration() {

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
            //View view =  getSupportActionBar().getCustomView().
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.PurpleSMSConverter));
            toolbar.setBackgroundColor(getResources().getColor(R.color.PurpleSMSConverter));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.PurpleSMSConverter));

        }



    }


    private void menuChoiceClicked(int menuChoice) {
        switch (menuChoice) {
            case 0:
                //context.startActivity(new Intent(context, AboutActivity.class));
                //UtilsApp.goToGooglePlay(context,"com.wekex.apps.sourceviewer");//TODO A enlever
                break;
            case 2:
                context.startActivity(new Intent(context, AboutActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case 3:
                context.startActivity(new Intent(context, AboutActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case 4:
                context.startActivity(new Intent(context, AboutActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case 5:
                context.startActivity(new Intent(context, AboutActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case 6:
                context.startActivity(new Intent(context, AboutActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case 7:
                context.startActivity(new Intent(context, AboutActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            default:
                break;
        }
    }
}

