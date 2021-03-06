package tigerstyle.social.com.banggiaxe.view.fragments;

import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tigerstyle.social.com.banggiaxe.BaseFragment;
import tigerstyle.social.com.banggiaxe.R;
import tigerstyle.social.com.banggiaxe.customize.CustomSpinner;
import tigerstyle.social.com.banggiaxe.model.CarBrand;
import tigerstyle.social.com.banggiaxe.model.MotobikeBrand;
import tigerstyle.social.com.banggiaxe.service.CarDataRequest;
import tigerstyle.social.com.banggiaxe.service.MotoDataRequest;
import tigerstyle.social.com.banggiaxe.utils.ConnectivityReceiver;
import tigerstyle.social.com.banggiaxe.utils.NumberFormater;
import tigerstyle.social.com.banggiaxe.utils.PicassoLoader;
import tigerstyle.social.com.banggiaxe.view.adapters.BaseSpinerAdapter;

import static tigerstyle.social.com.banggiaxe.config.Contants.IMAGE_URL;

/**
 * Created by billymobile on 2/3/17.
 */

public class ComparisonFragment extends BaseFragment implements ConnectivityReceiver.ConnectivityReceiverListener{
    private Button mBtnMotoComparison;
    private View mViewMoto;
    private Button mBtnOtoComparison;
    private View mViewOto;
    private CustomSpinner mSpinerBrand1;
    private CustomSpinner mSpinerType1;
    private CustomSpinner mSpinerBrand2;
    private CustomSpinner mSpinerType2;
    private Button mButtonComparison;
    private ImageView mImgVehical1;
    private ImageView mImgVehical2;

    // Information Detail
    private TextView mTxtVehicalPrice1;
    private TextView mTxtVehicalPrice2;
    private TextView mTxtVehicalSize1;
    private TextView mTxtVehicalSize2;
    private TextView mTxtFuelCapacity1;
    private TextView mTxtFuelCapacity2;
    private TextView mTxtDisplacement1;
    private TextView mTxtDisplacement2;
    private TextView mTxtOutputCapacity1;
    private TextView mTxtOutputCapacity2;
    private TextView mTxtTorquePower1;
    private TextView mTxtTorquePower2;
    private TextView mTxtGroundClearance1;
    private TextView mTxtGroundClearance2;
    private TextView mTxtGrossWeight1;
    private TextView mTxtGrossWeight2;
    private TextView mTxtTurningCircle1;
    private TextView mTxtTurningCircle2;
    private TextView mTxtTypeOfVehical1;
    private TextView mTxtTypeOfVehical2;
    private TextView mTxtNumberOfGears1;
    private TextView mTxtNumberOfGears2;
    private PercentRelativeLayout mLayoutTurningCircle;
    private PercentRelativeLayout mLayoutGrossWeight;
    private PercentRelativeLayout mLayoutGroundClearance;

    private ArrayList<MotobikeBrand> listMoto1;
    private ArrayList<MotobikeBrand> listMoto2;
    private ArrayList<String> listMotoName1 = new ArrayList<>();
    private ArrayList<String> listMotoName2 = new ArrayList<>();
    private MotobikeBrand moto1,moto2;

    private ArrayList<CarBrand> listCar1;
    private ArrayList<CarBrand> listCar2;
    private ArrayList<String> listCarName1 = new ArrayList<>();
    private ArrayList<String> listCarName2 = new ArrayList<>();
    private CarBrand car1,car2;

    private List<String> listMotoBrand;
    private List<String> listCarBrand;

    private LoaderManager.LoaderCallbacks<List<CarBrand>> carDataListener = new LoaderManager.LoaderCallbacks<List<CarBrand>>() {
        @Override
        public Loader<List<CarBrand>> onCreateLoader(int id, Bundle args) {
            return new CarDataRequest(context);
        }

        @Override
        public void onLoadFinished(Loader<List<CarBrand>> loader, List<CarBrand> data) {
            context.hideLoading();
            context.setListCar((ArrayList<CarBrand>)data);
        }

        @Override
        public void onLoaderReset(Loader<List<CarBrand>> loader) {
            context.hideLoading();
        }
    };
    private LoaderManager.LoaderCallbacks<List<MotobikeBrand>> motoDataListener = new LoaderManager.LoaderCallbacks<List<MotobikeBrand>>() {
        @Override
        public Loader<List<MotobikeBrand>> onCreateLoader(int id, Bundle args) {
            return new MotoDataRequest(context);
        }

        @Override
        public void onLoadFinished(Loader<List<MotobikeBrand>> loader, List<MotobikeBrand> data) {
            context.hideLoading();
            context.setListMoto((ArrayList<MotobikeBrand>)data);
        }

        @Override
        public void onLoaderReset(Loader<List<MotobikeBrand>> loader) {
            context.hideLoading();
        }
    };

    private int mComparisonType = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_comparison, null);
        mBtnMotoComparison = (Button) rootView.findViewById(R.id.btn_moto_comparison);
        mViewMoto = rootView.findViewById(R.id.view_moto);
        mBtnOtoComparison = (Button) rootView.findViewById(R.id.btn_oto_comparison);
        mViewOto = rootView.findViewById(R.id.view_oto);

        mTxtVehicalPrice1 = (TextView) rootView.findViewById(R.id.txt_vehical_price1);
        mTxtVehicalPrice2 = (TextView) rootView.findViewById(R.id.txt_vehical_price2);
        mButtonComparison = (Button) rootView.findViewById(R.id.btn_comparison);
        mTxtVehicalSize1 = (TextView) rootView.findViewById(R.id.txt_vehical_size1);
        mTxtVehicalSize2 = (TextView) rootView.findViewById(R.id.txt_vehical_size2);
        mTxtFuelCapacity1 = (TextView) rootView.findViewById(R.id.txt_fuel_capacity_value1);
        mTxtFuelCapacity2 = (TextView) rootView.findViewById(R.id.txt_fuel_capacity_value2);
        mTxtDisplacement1 = (TextView) rootView.findViewById(R.id.txt_displacement_value1);
        mTxtDisplacement2 = (TextView) rootView.findViewById(R.id.txt_displacement_value2);
        mTxtOutputCapacity1 = (TextView) rootView.findViewById(R.id.txt_output_capacity_value1);
        mTxtOutputCapacity2 = (TextView) rootView.findViewById(R.id.txt_output_capacity_value2);
        mTxtTorquePower1 = (TextView) rootView.findViewById(R.id.txt_torque_power_value1);
        mTxtTorquePower2 = (TextView) rootView.findViewById(R.id.txt_torque_power_value2);
        mTxtGroundClearance1 = (TextView) rootView.findViewById(R.id.txt_ground_clearance_value1);
        mTxtGroundClearance2 = (TextView) rootView.findViewById(R.id.txt_ground_clearance_value2);
        mTxtGrossWeight1 = (TextView) rootView.findViewById(R.id.txt_gross_weight_value1);
        mTxtGrossWeight2 = (TextView) rootView.findViewById(R.id.txt_gross_weight_value2);
        mTxtTurningCircle1 = (TextView) rootView.findViewById(R.id.txt_turning_circle_value1);
        mTxtTurningCircle2 = (TextView) rootView.findViewById(R.id.txt_turning_circle_value2);
        mTxtTypeOfVehical1 = (TextView) rootView.findViewById(R.id.txt_type_of_vehical1);
        mTxtTypeOfVehical2 = (TextView) rootView.findViewById(R.id.txt_type_of_vehical2);
        mTxtNumberOfGears1 = (TextView) rootView.findViewById(R.id.txt_number_of_gears1);
        mTxtNumberOfGears2 = (TextView) rootView.findViewById(R.id.txt_number_of_gears2);
        mLayoutTurningCircle = (PercentRelativeLayout) rootView.findViewById(R.id.layout_turning_circle);
        mLayoutGrossWeight = (PercentRelativeLayout) rootView.findViewById(R.id.layout_gross_weight);
        mLayoutGroundClearance = (PercentRelativeLayout) rootView.findViewById(R.id.layout_ground_clearance);

        // Spiner
        mSpinerBrand1 = (CustomSpinner) rootView.findViewById(R.id.spiner_brand);
        mSpinerType1 = (CustomSpinner) rootView.findViewById(R.id.spiner_type);
        mSpinerBrand2 = (CustomSpinner) rootView.findViewById(R.id.spiner_brand2);
        mSpinerType2 = (CustomSpinner) rootView.findViewById(R.id.spiner_type2);

        mImgVehical1 = (ImageView) rootView.findViewById(R.id.img_vehical1);
        mImgVehical2 = (ImageView) rootView.findViewById(R.id.img_vehical2);

        listMotoBrand = Arrays.asList(getResources().getStringArray(R.array.brand_array2));
        listCarBrand = Arrays.asList(getResources().getStringArray(R.array.car_brand_array2));
        listMoto1 = new ArrayList<>();
        listMoto2 = new ArrayList<>();
        listCar1 = new ArrayList<>();
        listCar2 = new ArrayList<>();

        if(context.getListCar() != null && context.getListCar().size() > 0){
        }else{

        }

        mBtnMotoComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinerBrand1.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listMotoBrand));
                mSpinerBrand2.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listMotoBrand));
                mComparisonType = 0;
                initBrandSpiner();
                resetUI();
                mLayoutGrossWeight.setVisibility(View.VISIBLE);
                mLayoutTurningCircle.setVisibility(View.GONE);
                mLayoutGroundClearance.setVisibility(View.GONE);
                mImgVehical1.setVisibility(View.INVISIBLE);
                mImgVehical2.setVisibility(View.INVISIBLE);
                resetStatus();
                mBtnMotoComparison.setBackgroundColor(ContextCompat.getColor(context, R.color.color_menu_click));
                mViewMoto.setVisibility(View.VISIBLE);
                mBtnMotoComparison.setTextColor(ContextCompat.getColor(context, R.color.cmn_white));
            }
        });

        mBtnOtoComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinerBrand1.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listCarBrand));
                mSpinerBrand2.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listCarBrand));
                initSpiner2();
                mComparisonType = 1;
                resetUI();
                mLayoutGrossWeight.setVisibility(View.GONE);
                mLayoutTurningCircle.setVisibility(View.VISIBLE);
                mLayoutGroundClearance.setVisibility(View.VISIBLE);
                mImgVehical1.setVisibility(View.INVISIBLE);
                mImgVehical2.setVisibility(View.INVISIBLE);
                resetStatus();
                mBtnOtoComparison.setBackgroundColor(ContextCompat.getColor(context, R.color.color_menu_click));
                mViewOto.setVisibility(View.VISIBLE);
                mBtnOtoComparison.setTextColor(ContextCompat.getColor(context, R.color.cmn_white));
            }
        });
        initBrandSpiner();

        mButtonComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImgVehical1.setVisibility(View.VISIBLE);
                mImgVehical2.setVisibility(View.VISIBLE);
                if(mComparisonType == 0){
                    if(moto1 != null && moto2 != null){
                        String urlImage1 = (moto1.getCarImage().contains("importxls"))?
                                (IMAGE_URL + moto1.getCarImage()) : moto1.getCarImage();
                        PicassoLoader.getInstance(context).load(urlImage1).placeholder(R.drawable.bg_captcha).
                                error(R.drawable.bg_captcha).into(mImgVehical1);
                        String urlImage2 = (moto2.getCarImage().contains("importxls"))?
                                (IMAGE_URL + moto2.getCarImage()) : moto2.getCarImage();
                        PicassoLoader.getInstance(context).load(urlImage2).placeholder(R.drawable.bg_captcha).
                                error(R.drawable.bg_captcha).into(mImgVehical2);
                        fillMotoData();
                    }
                }else{
                    if(car1 != null && car2 != null){
                        String urlImage1 = IMAGE_URL + car1.getCarImage();
                        PicassoLoader.getInstance(context).load(urlImage1).placeholder(R.drawable.bg_captcha).
                                error(R.drawable.bg_captcha).into(mImgVehical1);
                        String urlImage2 = IMAGE_URL + car2.getCarImage();
                        PicassoLoader.getInstance(context).load(urlImage2).placeholder(R.drawable.bg_captcha).
                                error(R.drawable.bg_captcha).into(mImgVehical2);
                        fillCarData();
                    }
                }
            }
        });
        if(context.getListCar() == null || context.getListCar().size() == 0){
            context.getSupportLoaderManager().initLoader(2, null, carDataListener).forceLoad();
        }
        return rootView;
    }

    private void fillMotoData(){
        mTxtVehicalPrice1.setText(moto1.getCarPrice());
        mTxtVehicalPrice2.setText(moto2.getCarPrice());
        mTxtVehicalSize1.setText(moto1.getCarSize());
        mTxtVehicalSize2.setText(moto2.getCarSize());
        mTxtFuelCapacity1.setText(moto1.getCarFuelTankCapacity());
        mTxtFuelCapacity2.setText(moto2.getCarFuelTankCapacity());
        mTxtDisplacement1.setText(moto1.getCarEngine());
        mTxtDisplacement2.setText(moto2.getCarEngine());
        mTxtOutputCapacity1.setText(NumberFormater.twoDecimaFormat(Double.parseDouble(moto1.getCarPower())));
        mTxtOutputCapacity2.setText(NumberFormater.twoDecimaFormat(Double.parseDouble(moto2.getCarPower())));
        mTxtTorquePower1.setText(NumberFormater.twoDecimaFormat(Double.parseDouble(moto1.getCarMoment())));
        mTxtTorquePower2.setText(NumberFormater.twoDecimaFormat(Double.parseDouble(moto2.getCarMoment())));
//        mTxtGroundClearance1 = (TextView) rootView.findViewById(R.id.txt_ground_clearance_value1);
//        mTxtGroundClearance2 = (TextView) rootView.findViewById(R.id.txt_ground_clearance_value2);
        mTxtGrossWeight1.setText(moto1.getCarTurningCirclel());
        mTxtGrossWeight2.setText(moto2.getCarTurningCirclel());

        mTxtTypeOfVehical1.setText(moto1.getCarType());
        mTxtTypeOfVehical2.setText(moto2.getCarType());
        mTxtNumberOfGears1.setText(moto1.getCarGear());
        mTxtNumberOfGears2.setText(moto2.getCarGear());
    }

    private void fillCarData(){
        mTxtVehicalPrice1.setText(car1.getCarPrice());
        mTxtVehicalPrice2.setText(car2.getCarPrice());
        mTxtVehicalSize1.setText(car1.getCarSize());
        mTxtVehicalSize2.setText(car2.getCarSize());
        mTxtFuelCapacity1.setText(car1.getCarFuelTankCapacity());
        mTxtFuelCapacity2.setText(car2.getCarFuelTankCapacity());
        mTxtDisplacement1.setText(car1.getCarEngine());
        mTxtDisplacement2.setText(car2.getCarEngine());
        mTxtOutputCapacity1.setText(NumberFormater.twoDecimaFormat(Double.parseDouble(car1.getCarPower())));
        mTxtOutputCapacity2.setText(NumberFormater.twoDecimaFormat(Double.parseDouble(car2.getCarPower())));
        mTxtTorquePower1.setText(NumberFormater.twoDecimaFormat(Double.parseDouble(car1.getCarMoment())));
        mTxtTorquePower2.setText(NumberFormater.twoDecimaFormat(Double.parseDouble(car2.getCarMoment())));
        mTxtGroundClearance1.setText(car1.getCarGroundClearance());
        mTxtGroundClearance2.setText(car2.getCarGroundClearance());
        mTxtTurningCircle1.setText(car1.getCarTurningCirclel());
        mTxtTurningCircle2.setText(car2.getCarTurningCirclel());
        mTxtGrossWeight1.setText(car1.getCarTurningCirclel());
        mTxtGrossWeight2.setText(car2.getCarTurningCirclel());

        mTxtTypeOfVehical1.setText(car1.getCarType());
        mTxtTypeOfVehical2.setText(car2.getCarType());
        mTxtNumberOfGears1.setText(car1.getCarGear());
        mTxtNumberOfGears2.setText(car2.getCarGear());
    }

    private void resetUI(){
        mTxtVehicalPrice1.setText("");
        mTxtVehicalPrice2.setText("");
        mTxtVehicalSize1.setText("");
        mTxtVehicalSize2.setText("");
        mTxtFuelCapacity1.setText("");
        mTxtFuelCapacity2.setText("");
        mTxtDisplacement1.setText("");
        mTxtDisplacement2.setText("");
        mTxtOutputCapacity1.setText("");
        mTxtOutputCapacity2.setText("");
        mTxtTorquePower1.setText("");
        mTxtTorquePower2.setText("");
        mTxtGroundClearance1.setText("");
        mTxtGroundClearance2.setText("");
        mTxtTurningCircle1.setText("");
        mTxtTurningCircle2.setText("");
        mTxtGrossWeight1.setText("");
        mTxtGrossWeight2.setText("");
        mTxtTypeOfVehical1.setText("");
        mTxtTypeOfVehical2.setText("");
        mTxtNumberOfGears1.setText("");
        mTxtNumberOfGears2.setText("");
    }

    private void resetStatus(){
        mBtnMotoComparison.setBackgroundColor(ContextCompat.getColor(context, R.color.color_menu_normal));
        mViewMoto.setVisibility(View.INVISIBLE);
        mBtnMotoComparison.setTextColor(ContextCompat.getColor(context, R.color.cmn_black));

        mBtnOtoComparison.setBackgroundColor(ContextCompat.getColor(context, R.color.color_menu_normal));
        mViewOto.setVisibility(View.INVISIBLE);
        mBtnOtoComparison.setTextColor(ContextCompat.getColor(context, R.color.cmn_black));
    }

    private void initBrandSpiner(){
        final ArrayList<MotobikeBrand> allMoto =  context.getListMoto();
        final ArrayList<CarBrand> allCar =  context.getListCar();

        if(mComparisonType == 0){
            mSpinerBrand1.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listMotoBrand));
            mSpinerBrand2.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listMotoBrand));
            mSpinerBrand1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    listMoto1.clear();
                    listMotoName1.clear();
                    String motoBrand = listMotoBrand.get(i);
                    for(MotobikeBrand brand : allMoto){
                        if(brand.getCarBrand().trim().equals(motoBrand.trim())) {
                            listMoto1.add(brand);
                            listMotoName1.add(brand.getCarName());
                        }
                    }
                    mSpinerType1.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listMotoName1));
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            mSpinerType1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(listMoto1!=null)moto1 = listMoto1.get(i);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            mSpinerBrand2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    listMoto2.clear();
                    listMotoName2.clear();
                    String motoBrand = listMotoBrand.get(i);
                    for(MotobikeBrand brand : allMoto){
                        if(brand.getCarBrand().equals(motoBrand)) {
                            listMoto2.add(brand);
                            listMotoName2.add(brand.getCarName());
                        }
                    }
                    mSpinerType2.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listMotoName2));
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            mSpinerType2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(listMoto2!=null)moto2 = listMoto2.get(i);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }else{
            mSpinerBrand1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    listCar1.clear();
                    listCarName1.clear();
                    String otoBrand = listCarBrand.get(i);
                    if(allCar != null){
                        for(CarBrand brand : allCar){
                            if(brand.getCarBrand().trim().equals(otoBrand.trim())) {
                                listCar1.add(brand);
                                listCarName1.add(brand.getCarName());
                            }
                        }
                    }
                    mSpinerType1.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listCarName1));
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            mSpinerType1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(listCar1!=null)car1 = listCar1.get(i);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            mSpinerBrand2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    listCar2.clear();
                    listCarName2.clear();
                    String carBrand = listCarBrand.get(i);
                    for(CarBrand brand : allCar){
                        if(brand.getCarBrand().equals(carBrand)) {
                            listCar2.add(brand);
                            listCarName2.add(brand.getCarName());
                        }
                    }
                    mSpinerType2.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listCarName2));
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            mSpinerType2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(listCar2!=null)car2 = listCar2.get(i);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
    }

    private void initSpiner2(){
        final ArrayList<CarBrand> allCar =  context.getListCar();
        mSpinerBrand1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listCar1.clear();
                listCarName1.clear();
                String otoBrand = listCarBrand.get(i);
                if(allCar != null){
                    for(CarBrand brand : allCar){
                        if(brand.getCarBrand().trim().equals(otoBrand.trim())) {
                            listCar1.add(brand);
                            listCarName1.add(brand.getCarName());
                        }
                    }
                    mSpinerType1.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listCarName1));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mSpinerType1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(listCar1!=null)car1 = listCar1.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mSpinerBrand2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listCar2.clear();
                listCarName2.clear();
                String carBrand = listCarBrand.get(i);
                if(allCar != null){
                    for(CarBrand brand : allCar){
                        if(brand.getCarBrand().equals(carBrand)) {
                            listCar2.add(brand);
                            listCarName2.add(brand.getCarName());
                        }
                    }
                    mSpinerType2.setAdapter(new BaseSpinerAdapter(context, R.layout.spinner_item, listCarName2));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        mSpinerType2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(listCar2!=null)car2 = listCar2.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onResume(){
        super.onResume();
        context.getSupportActionBar().setTitle(context.getResources().getString(R.string.cmn_comparision));
        setHasOptionsMenu(true);
        context.setConnectivityListener(this);
        if(!ConnectivityReceiver.isConnected()){
            Toast.makeText(context,context.getResources().getString(R.string.cmn_no_internet_access),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(!isConnected){
            Toast.makeText(context,context.getResources().getString(R.string.cmn_no_internet_access),Toast.LENGTH_LONG).show();
        }else{
            if(context.getListMoto() == null || context.getListMoto().size() == 0){
                context.getSupportLoaderManager().initLoader(1, null, motoDataListener).forceLoad();
            }
            if(context.getListCar() == null || context.getListCar().size() == 0){
                context.getSupportLoaderManager().initLoader(2, null, carDataListener).forceLoad();
            }
        }
    }
}
