package br.com.gearsoft.doarsangue;

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

import br.com.gearsoft.doarsangue.domain.Solicitacao;
import br.com.gearsoft.doarsangue.fragments.DoacaoFragment;
import br.com.gearsoft.doarsangue.fragments.DoacaoFragment.OnDoacaoFragmentInteractionListener;
import br.com.gearsoft.doarsangue.fragments.PerfilFragment;
import br.com.gearsoft.doarsangue.fragments.PerfilFragment.OnPerfilFragmentInteractionListener;
import br.com.gearsoft.doarsangue.fragments.SolicitacaoFragment;
import br.com.gearsoft.doarsangue.fragments.SolicitacaoFragment.OnSolicitacaoFragmentInteractionListener;
import br.com.gearsoft.doarsangue.fragments.dummydoa.DummyDoacao;
import br.com.gearsoft.doarsangue.services.SolicitacaoService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnSolicitacaoFragmentInteractionListener, OnDoacaoFragmentInteractionListener, OnPerfilFragmentInteractionListener {

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
    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

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
    public void onFragmentInteraction(Uri uri) {
        Log.d("CLICK", "onClickPerfil");
    }

    @Override
    public void onClickSolicitacaoItem(Solicitacao solicitacao) {
        Log.d("CLICK", "onClickSolicitacaoItem: " +solicitacao.toString());

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
                    return "";
            }
        }
    }
}
