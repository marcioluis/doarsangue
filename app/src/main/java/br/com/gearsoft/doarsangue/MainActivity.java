package br.com.gearsoft.doarsangue;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import br.com.gearsoft.doarsangue.domain.Doacao;
import br.com.gearsoft.doarsangue.domain.Solicitacao;
import br.com.gearsoft.doarsangue.fragments.DoacaoFragment;
import br.com.gearsoft.doarsangue.fragments.DoacaoFragment.OnDoacaoInteractionListener;
import br.com.gearsoft.doarsangue.fragments.PerfilFragment;
import br.com.gearsoft.doarsangue.fragments.SolicitacaoFragment;
import br.com.gearsoft.doarsangue.fragments.SolicitacaoFragment.OnSolicitacaoInteractionListener;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnSolicitacaoInteractionListener, OnDoacaoInteractionListener, PerfilFragment.OnPerfilInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 8907; // max value 65.535
    private FirebaseAuth mFirebaseAuth;
    private boolean mIsSigningIn;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.main_content)
    View mRootView;

    private boolean shouldStartSignIn() {
        return (!mIsSigningIn && mFirebaseAuth.getCurrentUser() == null);
    }

    private void startSignIn() {
        Intent intent =
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())
                        )
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG)// desabilita em dev habilita em prod
                        .build();
        startActivityForResult(intent, RC_SIGN_IN);
        mIsSigningIn = true;
    }

    private void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        showSnackbar("Logouted");
                    }
                });

    }

    @MainThread
    private void showSnackbar(String errorMessageRes) {
        Snackbar.make(mRootView, errorMessageRes, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Toolbar
        setSupportActionBar(toolbar);

        // O Adapter que retorna os fragments de cada seção do pager
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up ViewPager com o adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //Set up as tabs com o nosso pager
        tabLayout.setupWithViewPager(mViewPager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (shouldStartSignIn()) {
            startSignIn();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            mIsSigningIn = false;
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Sign in failed
            if (resultCode != RESULT_OK && shouldStartSignIn()) {
                if (response == null) {
                    // User pressed back button
                    showSnackbar("sign_in_cancelled");
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackbar("no_internet_connection");
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackbar("unknown_error");
                    return;
                }

                showSnackbar("unknown_sign_in_response");
            }
        }
    }

    @Override
    public void onClickDoacaoItem(Doacao doacao) {
        Log.d(TAG, "onClickDoacaoItem: " + doacao.toString());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "onClickPerfil");
    }

    @Override
    public void onClickSolicitacaoItem(Solicitacao solicitacao) {
        Log.d(TAG, "onClickSolicitacaoItem: " +solicitacao.toString());

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    static class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Map<Integer, String> secoes = new HashMap<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

            secoes.put(0, "Solicitações");
            secoes.put(1, "Doações");
            secoes.put(2, "Perfil");
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return SolicitacaoFragment.newInstance();
                case 1:
                    return DoacaoFragment.newInstance();
                case 2:
                    return PerfilFragment.newInstance(null, null);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return secoes.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return secoes.get(position);
                case 1:
                    return secoes.get(position);
                case 2:
                    return secoes.get(position);
                default:
                    return "";
            }
        }
    }
}
