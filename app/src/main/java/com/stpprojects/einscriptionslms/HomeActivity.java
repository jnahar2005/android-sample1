package com.stpprojects.einscriptionslms;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.stpprojects.einscriptionslms.fragments.AllCoursesFragment;
import com.stpprojects.einscriptionslms.fragments.CourseStatusFragment;
import com.stpprojects.einscriptionslms.fragments.HomeFragment;
import com.stpprojects.einscriptionslms.fragments.MyCoursesFragment;
import com.stpprojects.einscriptionslms.utils.SessionManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/* Created by Smita Pathak on 29/1/2020 */

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    SessionManager sessionManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(HomeActivity.this);
        setSupportActionBar(toolbar);
        //String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(this);
        insertFragment(HomeFragment.class);
        setTitle(getResources().getString(R.string.WelcometoeinscriptionLMSs));
        View headerView = nav_view.getHeaderView(0);
        TextView userName = headerView.findViewById(R.id.userName);
        if (!sessionManager.getuserFirstName().equalsIgnoreCase(""))
            userName.setText(sessionManager.getuserFirstName() + " " + sessionManager.getuserLastName());

        CircleImageView profileimage = headerView.findViewById(R.id.profileimage);

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else if (backStackEntryCount == 0 || backStackEntryCount == 1) {
            new AlertDialog.Builder(this)
                    .setMessage(getString(R.string.want_to_exit))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        } else if (backStackEntryCount > 1) {
            removeFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSearch) {
            searchDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* ----------------Custom Search Dialog---------------- */

    public void searchDialog() {
        Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.dialog_search);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        recyclerView = (RecyclerView) dialog.findViewById(R.id.recyclerView);

        EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 2) {
                } else {
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        dialog.show();
    }
//----------------------------------------------------

    public void removeFragment() {
        clearStack();
        MenuItem menuItem = nav_view.getMenu().getItem(0);
        nav_view.getMenu().getItem(0).setChecked(true);
        setTitle(getResources().getString(R.string.WelcometoeinscriptionLMSs));
    }

    public void clearStack() {
        int backStackEntry = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 1; i < backStackEntry; i++) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }
        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            for (int i = 1; i < getSupportFragmentManager().getFragments().size(); i++) {
                Fragment mFragment = getSupportFragmentManager().getFragments().get(i);
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                }
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class fragmentClass = null;
        int id = item.getItemId();

        if (id == R.id.nav_LMSAcademy) {
            Fragment homeFragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getName());
            fragmentClass = HomeFragment.class;
            insertFragment(fragmentClass);
            item.setChecked(true);
            setTitle(getResources().getString(R.string.WelcometoeinscriptionLMSs));
            if (homeFragment != null && homeFragment.isVisible()) {
                drawer_layout.closeDrawer(GravityCompat.START);
                return true;
            }
        } else if (id == R.id.nav_mycourses) {
            Fragment myCoursesFragment = getSupportFragmentManager().findFragmentByTag(MyCoursesFragment.class.getName());
            fragmentClass = MyCoursesFragment.class;
            insertFragment(fragmentClass);
            item.setChecked(true);
            setTitle(item.getTitle());
            if (myCoursesFragment != null && myCoursesFragment.isVisible()) {
                drawer_layout.closeDrawer(GravityCompat.START);
                return true;
            }
        } else if (id == R.id.nav_coursesStatus) {
            fragmentClass = CourseStatusFragment.class;
            insertFragment(fragmentClass);
            item.setChecked(true);
            setTitle(item.getTitle());
        } else if (id == R.id.nav_allcourses) {
            fragmentClass = AllCoursesFragment.class;
            insertFragment(fragmentClass);
            item.setChecked(true);
            setTitle(item.getTitle());
        } else if (id == R.id.nav_ShareApp) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "LMS Academy");
                String shareMessage = "\nShare this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch (Exception e) {
            }
        } else if (id == R.id.nav_logout) {
            sessionManager.logoutUser(HomeActivity.this);
            finish();
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void insertFragment(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.flContent, fragment)
                    .addToBackStack(fragmentClass.getName())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
