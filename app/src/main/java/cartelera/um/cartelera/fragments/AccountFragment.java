package cartelera.um.cartelera.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.activities.NavigationActivity;
import cartelera.um.cartelera.adapters.SettingRecyclerAdapter;
import cartelera.um.cartelera.auth.AuthenticationManagerFactory;
import cartelera.um.cartelera.entities.Setting;
import cartelera.um.cartelera.entities.SettingAction;
import cartelera.um.cartelera.entities.SettingActivityLauncher;
import cartelera.um.cartelera.entities.User;
import cartelera.um.cartelera.flow.FlowManager;
import cartelera.um.cartelera.interfaces.SettingActions;
import cartelera.um.cartelera.interfaces.SettingType;
import cartelera.um.cartelera.listeners.AdapterClickListener;
import jp.wasabeef.picasso.transformations.BlurTransformation;

/**
 * Created by Christian on 13/01/2019.
 */

public class AccountFragment extends Fragment implements SettingType, SettingActions {
    private Context mContext;
    private User userLoged;
    private List<Setting> settings = new ArrayList<>();;
    private RecyclerView settingRecycle;
    private SettingRecyclerAdapter settingAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView backgroundImage, profileImage;
    private TextView userName, userEmail;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View accountView = inflater.inflate(R.layout.profile_layout, container, false);
        userLoged = AuthenticationManagerFactory.getIntance(mContext).getCurrentUser();
        Resources res = getResources();
        TextView toolbarTitle = accountView.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(res.getString(R.string.title_configuration));
        setUpView(accountView);
        return accountView;
    }


    private void setUpView(View accountView){
        settingRecycle =  accountView.findViewById(R.id.setting_recycler);
        layoutManager = new LinearLayoutManager(mContext);
        settingRecycle.setLayoutManager(layoutManager);
        settingRecycle.setNestedScrollingEnabled(false);
        userName = accountView.findViewById(R.id.profile_name);
        userEmail = accountView.findViewById(R.id.profile_email);
        backgroundImage = accountView.findViewById(R.id.background_image);
        profileImage = accountView.findViewById(R.id.profile_image);

        final String userProfileImage = userLoged.getImg();
        if(!TextUtils.isEmpty(userProfileImage)){
            Picasso.with(mContext).load(userProfileImage)
                    .transform(new BlurTransformation(mContext))
                    .into(backgroundImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.with(mContext)
                                    .load(userProfileImage)
                                    .into(profileImage);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        };

        loadSettings();

        settingAdapter = new SettingRecyclerAdapter(mContext, settings, new AdapterClickListener() {
            @Override
            public void onItemClick(final View v, final int position) {
                Setting settingSelected = settings.get(position);

                switch (settingSelected.getSettingType()){
                    case ACTION:
                        SettingAction settingAction = (SettingAction) settings.get(position);
                        switch (settingAction.getAction()){
                            case LOG_OUT:
                                /*Intent i = ServiceLocatorFactory.getInstance(getActivity()).getFlowManager().getIntent(mContext, FlowManager.LOGIN_ACTIVITY);
                                getActivity().startActivity(i);
                                getActivity().finish();*/
                                break;
                        }

                        break;
                    case ACTIVITY_LAUNCHER:
                        /*SettingActivityLauncher settingActivityLauncher = (SettingActivityLauncher) settings.get(position);
                        Intent i = ((NavigationActivity)getActivity()).getServicelocator().getFlowManager().getIntent(mContext,settingActivityLauncher.getNextActivity());
                        startActivity(i);*/
                        break;
                }
            }
        });

        userName.setText(userLoged.getName());
        userEmail.setText(userLoged.getEmail());
        settingRecycle.setAdapter(settingAdapter);
    }

    private void loadSettings(){
        Resources res = mContext.getResources();
        settings.add(new SettingActivityLauncher(res.getString(R.string.setting_change_password_title), R.drawable.ic_lock, ACTIVITY_LAUNCHER,
                FlowManager.CHANGE_PASSWORD));
        settings.add(new SettingActivityLauncher(res.getString(R.string.setting_notification_title), R.drawable.ic_bell_ring, ACTIVITY_LAUNCHER,
                FlowManager.NOTIFICATION_ACTIVITY));
        settings.add(new SettingAction(res.getString(R.string.setting_log_out_title), R.drawable.ic_exit_to_app, ACTION , LOG_OUT));
    }

}
