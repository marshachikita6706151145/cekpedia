package me.cekpedia;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.cekpedia.models.ImageSlider;
import me.cekpedia.models.ImageUpload;


public class HomeFragment extends Fragment implements ImageAdapter.ClickListener{
//    GridView gridView;
//    private FirebaseUser mFirebaseUser;
//    DatabaseReference mDb;
//    private ImageView imgViewProf;
//    private TextView usernama, useremail;
//    private SliderLayout sliderLayout;
//    private EditText searchView;
//    private FirebaseStorage firebaseStorage;
//    private String userID;
//    private StorageReference storageReference;
//    String[] aPhotoAds;
//    String[] aNameAds;
//    int imageSize;
//    FirebaseAuth mAuth;
//    public static final String FB_DATABASE_PATH = "slider";
//    private BottomNavigationView bottomNavigationView;

//    ViewPager viewPager;
//    CirclePageIndicator circlePage;
//    ImageSlideAdapter slideAdapter;
//    private static final String ARG_PARAM1 = "params";
//    private String imageUrls;
//
    int[] gambar = {
            R.drawable.icon_hotel,
            R.drawable.ic_masjid,
            R.drawable.ic_wisata,
            R.drawable.ic_penginapan,
            R.drawable.ic_rumah_sakit,
            R.drawable.ic_restoran,
            R.drawable.ic_supermarket,
            R.drawable.ic_sekolah,
            R.drawable.ic_transportasi,
            R.drawable.ic_input_lokasi,
            R.drawable.ic_spbu,
            R.drawable.ic_apotek,
            R.drawable.ic_bidan
    };
    String [] namaMenu = {
            "    Hotel",
            "    Masjid",
            "    Wisata",
            "Penginapan",
            "Rumah Sakit",
            "  Restoran",
            "Supermarket",
            "   Sekolah",
            "Transportasi",
            "Input Lokasi",
            "     SPBU",
            "    Apotek",
            "    Bidan"
    };
//    private Handler handler;
//    private Runnable Update;
//    static int currentPage = 0;
//    static int NUM_PAGES = 0;
//    private Timer swipeTimer;
//    private HomeFragment homeFragment;

    private SliderLayout sliderLayout;
    private RecyclerView recyclerView;
    private List<Thumbnail> listThumbnail;
    GridLayoutManager layoutManager;
    View view;
    TextView st;
    Typeface tf;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listThumbnail = new ArrayList<>();
        listThumbnail.add(new Thumbnail("Transportasi", R.drawable.ic_transport));
//        listThumbnail.add(new Thumbnail("Hotel", R.drawable.icon_hotel));
//        listThumbnail.add(new Thumbnail("Masjid", R.drawable.ic_masjid));
//        listThumbnail.add(new Thumbnail("Wisata", R.drawable.ic_wisata));
//        listThumbnail.add(new Thumbnail("Penginapan", R.drawable.ic_penginapan));
//        listThumbnail.add(new Thumbnail("Rumah Sakit", R.drawable.ic_rumah_sakit));
//        listThumbnail.add(new Thumbnail("Restoran", R.drawable.ic_restoran));
//        listThumbnail.add(new Thumbnail("Supermarket", R.drawable.ic_supermarket));
//        listThumbnail.add(new Thumbnail("Sekolah", R.drawable.ic_sekolah));
//        listThumbnail.add(new Thumbnail("Transportasi", R.drawable.ic_transportasi));
//        listThumbnail.add(new Thumbnail("Input Lokasi", R.drawable.ic_input_lokasi));
//        listThumbnail.add(new Thumbnail("SPBU", R.drawable.ic_spbu));
//        listThumbnail.add(new Thumbnail("Apotik", R.drawable.ic_apotek));
//        listThumbnail.add(new Thumbnail("Bidan", R.drawable.ic_bidan));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        imageUrls = getArguments().getString(ARG_PARAM1);


        view = inflater.inflate(R.layout.fragment_home, container, false);

        //Pengaturan Font
        st = (TextView) view.findViewById(R.id.toolbar_text);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "FRSCRIPT.TTF");
        st.setTypeface(tf);

        // Pengaturan Ukuran Icon DrawableLeft
        final EditText search = (EditText) view.findViewById(R.id.cari);
        final float density = getResources().getDisplayMetrics().density;
        final Drawable drawable_search = getResources().getDrawable(R.drawable.ic_search);

        final int width = Math.round(24 * density);
        final int height = Math.round(24 * density);

        drawable_search.setBounds(0, 0, width, height);
        search.setCompoundDrawables(drawable_search, null, null, null);

        // Pengaturan Recycler View
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        ImageAdapter myAdapter = new ImageAdapter(getActivity(), listThumbnail);
        layoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

        // Slider Layout
        sliderLayout = view.findViewById(R.id.slider);
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Apotek K24 Gerlong", R.drawable.apotekk24gerlong);
        file_maps.put("RM Bakul Daun", R.drawable.rmbakuldaun);
        file_maps.put("Bandara Husein Sastranegara", R.drawable.bandarahusein);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);

//        homeFragment = this;

//        loadSlider();
//        viewPager = view.findViewById(R.id.viewPagerImageAds);
//        circlePage = view.findViewById(R.id.circlePageImageAds);
//        recyclerView = view.findViewById(R.id.recycler_view);
//        layoutManager = new GridLayoutManager(getActivity(), 4);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
//        ImageAdapter myAdapter = new ImageAdapter(getActivity(), gambar, namaMenu);
//        recyclerView.setAdapter(myAdapter);
//        myAdapter.setClickListener(this);
//        searchView = (EditText) view.findViewById(R.id.cari);
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), ListCariActivity.class));
//                setTitle("Trafinder");
//                HomeFragment fragment = new HomeFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frameLayout, fragment);
//                fragmentTransaction.commit();
//            }
//        });
//        mDb = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
//        sliderLayout = view.findViewById(R.id.slider);
//        mDb.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                final Map<String, Object> detailMenu = (Map<String, Object>) dataSnapshot.getValue();
//                Map<String, ImageSlider> td = new HashMap<String, ImageSlider>();
//                HashMap<String,String> url_maps = new HashMap<String, String>();
//                ArrayList<ImageSlider> value = new ArrayList<>(td.values());
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    ImageUpload img = ds.getValue(ImageUpload.class);
//                    String text = ds.child("name").getValue(String.class);
//                    String image = ds.child("url").getValue(String.class);
//                    url_maps.put(text.toString(), image.toString());
//                    for (ImageSlider ad : value) {
//
//                    }
//                    for (String name : url_maps.keySet()) {
//                        TextSliderView textSliderView = new TextSliderView(getActivity());
//                        // initialize a SliderLayout
//                        textSliderView
//                                .description(name)
//                                .image(url_maps.get(name))
//                                .setScaleType(BaseSliderView.ScaleType.Fit);
//                        //add your extra information
//                        textSliderView.bundle(new Bundle());
//                        textSliderView.getBundle()
//                                .putString("extra", name);
//                        sliderLayout.addSlider(textSliderView);
//                    }
//                }
//                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
//                sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//                sliderLayout.setCustomAnimation(new DescriptionAnimation());
//                sliderLayout.setDuration(4000);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Apotek K24 Gerlong", R.drawable.apotekk24gerlong);
//        file_maps.put("RM Bakul Daun", R.drawable.rmbakuldaun);
//        file_maps.put("Bandara Husein Sastranegara", R.drawable.bandarahusein);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    //    @Override
 /*   public void loadSlider(){

        aPhotoAds = new String[8];
        aNameAds = new String[8];
        imageSize = 0;
        FirebaseDatabase.getInstance().getReference()
                .child("slider")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) child.getValue();

                            aPhotoAds[imageSize] = map.get("url").toString();
                            aNameAds[imageSize] = map.get("name").toString();

                            imageSize++;
                        }
                        viewPager.setAdapter(new ImageSlideAdapter(homeFragment.getContext(), aPhotoAds, imageSize, homeFragment));
                        circlePage.setViewPager(viewPager);

                        //final float density = homeFragment.getActivity().getResources().getDisplayMetrics().density;

                        //Set circle indicator radius
                        circlePage.setRadius(5 * 1);

                        NUM_PAGES = imageSize-1;

                        handler = new Handler();
                        Update = new Runnable() {
                            public void run() {
                                if (currentPage == NUM_PAGES) {
                                    currentPage = 0;
                                }
                                viewPager.setCurrentItem(currentPage++, true);
                            }
                        };

                        swipeTimer = new Timer();
                        swipeTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(Update);
                            }
                        }, 3000, 3000);

                        circlePage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                currentPage = position;
                            }
                            @Override
                            public void onPageSelected(int position) {}
                            @Override
                            public void onPageScrollStateChanged(int state) {}
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }*/
    @Override
    public void itemClicked(View view, int position) {
        switch (position) {
            case 0:
                Intent intentmasjid = new Intent(getActivity(), MasjidActivity.class);
                startActivity(intentmasjid);
                break;
            case 1:
                Intent intentwisata = new Intent(getActivity(), WisataActivity.class);
                startActivity(intentwisata);
                break;
            case 2:
                Intent intenthotel = new Intent(getActivity(), HotelActivity.class);
                startActivity(intenthotel);
                break;
            case 3:
                Intent intentrs = new Intent(getActivity(), RumahsakitActivity.class);
                startActivity(intentrs);
                break;
            case 4:
                Intent intentrestoran = new Intent(getActivity(), RestoranActivity.class);
                startActivity(intentrestoran);
                break;
            case 5:
                Intent intentsupermarket = new Intent(getActivity(), SupermarketActivity.class);
                startActivity(intentsupermarket);
                break;
            case 6:
                Intent intentsekolah = new Intent(getActivity(), SekolahActivity.class);
                startActivity(intentsekolah);
                break;
            case 7:
                Intent intenttransport = new Intent(getActivity(), TransportasiActivity.class);
                startActivity(intenttransport);
                break;
            case 8:
                Intent intentinput = new Intent(getActivity(), InputLokasiActivity.class);
                startActivity(intentinput);
                break;
            case 9:
                Intent intentspbu = new Intent(getActivity(), SPBUActivity.class);
                startActivity(intentspbu);
                break;
            case 10:
                Intent intentapotek = new Intent(getActivity(), ApotekActivity.class);
                startActivity(intentapotek);
                break;
            case 11:
                Intent intentbidan = new Intent(getActivity(), BidanActivity.class);
                startActivity(intentbidan);
                break;

        }
    }

    public void viewPagerOnClick(int position) {
        Toast.makeText(getContext(), "posisi "+position, Toast.LENGTH_SHORT).show();
    }
}
