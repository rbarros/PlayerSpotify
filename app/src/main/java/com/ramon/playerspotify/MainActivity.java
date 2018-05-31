package com.ramon.playerspotify;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ramon.playerspotify.adapter.ListaAlbunsAdapter;
import com.ramon.playerspotify.fragment.AlbunsFragment;
import com.ramon.playerspotify.fragment.ArtistasFragment;
import com.ramon.playerspotify.fragment.MusicasFragment;
import com.ramon.playerspotify.fragment.PlaylistFragment;
import com.ramon.playerspotify.model.AlbumModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ## Buscas (https://beta.developer.spotify.com/documentation/web-api/reference/search/search/)
 *    Busca por álbum
 *      Buscar e listar álbuns
 *          Dados mínimos: Nome, foto (somente 1) e ano de publicação do álbum
 *    Busca por artista
 *      Buscar e listar artistas
 *          Dados mínimos: Nome e foto do artista (somente 1)
 *    Busca por música
 *      Buscar e listar músicas
 *          Dados mínimos: Nome e duração da música, foto do album e nome do artista(s)
 */
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

//    private List<AlbumModel> dataSource;
//    private List<AlbumModel> adapterDataSource;
    private ListaAlbunsAdapter adapter;

    private Fragment playlistFragment;
    private Fragment musicasFragment;
    private Fragment artistasFragment;
    private Fragment albunsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //geraDadosTeste();

        playlistFragment = new PlaylistFragment();
        musicasFragment = new MusicasFragment();
        artistasFragment = new ArtistasFragment();
        albunsFragment = new AlbunsFragment();

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        // Informa qual toolbar estamos utizando
        // pois a Activity espera uma Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    /*private void geraDadosTeste()
    {
        dataSource = new ArrayList<>();
        dataSource.add(new AlbumModel("Black Label Society", "album_0", new Date(Long.parseLong("1343805819061"))));
        dataSource.add(new AlbumModel("Rampage - Destruição Total", "album_1", new Date(Long.parseLong("1343805819061"))));
        dataSource.add(new AlbumModel("Nada a Perder - Contra Tudo. Por Todos", "album_2", new Date(Long.parseLong("1343805819061"))));
        dataSource.add(new AlbumModel("Um Lugar Silencioso", "album_3",new Date(Long.parseLong("1343805819061"))));
        dataSource.add(new AlbumModel("Exorcismos e Demônios", "album_4", new Date(Long.parseLong("1343805819061"))));

        adapterDataSource = new ArrayList<>();
        adapterDataSource.addAll(dataSource);
    }*/

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(playlistFragment, "Playlist");
        adapter.addFragment(musicasFragment, "Músicas");
        adapter.addFragment(artistasFragment, "Artistas");
        adapter.addFragment(albunsFragment, "Albuns");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // User pressed the search button
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        int index = viewPager.getCurrentItem();
        ViewPagerAdapter adapter = ((ViewPagerAdapter)viewPager.getAdapter());
        Fragment activeFragment = adapter.getItem(index);
        switch (index) {
            case 0:
                ((PlaylistFragment)activeFragment).refreshString(newText);
                break;
            case 1:
                ((MusicasFragment)musicasFragment).refreshString(newText);
                break;
            case 2:
                ((ArtistasFragment)activeFragment).refreshString(newText);
                break;
            case 3:
                ((AlbunsFragment)activeFragment).refreshString(newText);
                break;
        }

        return true;
    }
}
