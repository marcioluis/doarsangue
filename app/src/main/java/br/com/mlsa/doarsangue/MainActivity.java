package br.com.mlsa.doarsangue;

import android.net.Uri;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

import br.com.mlsa.doarsangue.fragments.DoacaoFragment;
import br.com.mlsa.doarsangue.fragments.DoacaoFragment.OnDoacaoFragmentInteractionListener;
import br.com.mlsa.doarsangue.fragments.PerfilFragment;
import br.com.mlsa.doarsangue.fragments.PerfilFragment.OnPerfilFragmentInteractionListener;
import br.com.mlsa.doarsangue.fragments.SolicitacaoFragment;
import br.com.mlsa.doarsangue.fragments.SolicitacaoFragment.OnSolicitacaoFragmentInteractionListener;
import br.com.mlsa.doarsangue.fragments.dummydoa.DummyDoacao;
import br.com.mlsa.doarsangue.fragments.dummysol.DummySolicitacao;

public class MainActivity extends AppCompatActivity implements OnDoacaoFragmentInteractionListener, OnSolicitacaoFragmentInteractionListener, OnPerfilFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // O Adapter que retorna os fragments de cada seção do pager
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up ViewPager com o adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //Set up as tabs com o nosso pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Deve ir para uma atividade de criação", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
    public void onClickDoacaoItem(DummyDoacao.DummyDoacaoItem item) {
        Log.d("CLICK", "onClickDoacaoItem: " +item.toString());
    }

    @Override
    public void onClickSolicitacaoItem(DummySolicitacao.DummySolicitacaoItem item) {
        Log.d("CLICK", "onClickSolicitacaoItem: " +item.toString());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("CLICK", "onClickPerfil");
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

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
                    return SolicitacaoFragment.newInstance(1);
                case 1:
                    return DoacaoFragment.newInstance(1);
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
                    return null;
            }
        }
    }
}
