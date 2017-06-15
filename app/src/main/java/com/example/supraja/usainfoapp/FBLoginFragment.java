package com.example.supraja.usainfoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.w3c.dom.Text;

import java.io.InputStream;

/**
 * Created by Supraja on 6/8/2017.
 */

public class FBLoginFragment extends Fragment{

    private TextView textView;
    Button getInfoButton;
    ImageView profilepicImageView;
    String name;

    private CallbackManager mCallbackManager;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;

    private FacebookCallback<LoginResult> mFacebookCallback  = new  FacebookCallback<LoginResult>(){

        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d("####", "onSuccess");
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
           // updateUI();
            textView.setText(constructWelcomeMessage(profile));
            getInfoButton.setVisibility(View.VISIBLE);

            getInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),SecondActivity.class);
                   // intent.putExtra("profileName",name);
                    startActivity(intent);

                }
            });

        }

        @Override
        public void onCancel() {
            Log.d("####","onCancel");
        }

        @Override
        public void onError(FacebookException error) {
            Log.d("####","onError : " +error);

        }
    };

    public FBLoginFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCallbackManager = CallbackManager.Factory.create();
        setupTokenTracker();
        setupProfileTracker();

        mTokenTracker.startTracking();
        mProfileTracker.startTracking();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v=  inflater.inflate(R.layout.fblogin_fragment,container,false);
        getInfoButton = (Button)v.findViewById(R.id.getInfo);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        textView = (TextView)view.findViewById(R.id.textView);
        setupLoginButton(view);


   }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Profile profile = Profile.getCurrentProfile();
//        textView.setText(constructWelcomeMessage(profile));
//
//    }

    @Override
    public void onStop() {
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
        LoginManager.getInstance().logOut();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void setupTokenTracker() {
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.d("####", "" + currentAccessToken);
            }
        };
    }

    private void setupProfileTracker() {
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.d("####", "" + currentProfile);
                textView.setText(constructWelcomeMessage(currentProfile));
            }
        };
    }

    private void setupLoginButton(View view) {
        LoginButton mButtonLogin = (LoginButton) view.findViewById(R.id.login_button);
        mButtonLogin.setFragment(this);
        mButtonLogin.setReadPermissions("user_friends");
        mButtonLogin.registerCallback(mCallbackManager, mFacebookCallback);
    }

    private String constructWelcomeMessage(Profile profile) {
        StringBuffer stringBuffer = new StringBuffer();
        if (profile != null) {
            stringBuffer.append("Welcome " + profile.getName());
        }
        return stringBuffer.toString();
    }

//    private void updateUI() {
//
//        Profile profile = Profile.getCurrentProfile();
//        if (profile != null) {
//            new LoadProfileImage(profilepicImageView).execute(profile.getProfilePictureUri(200, 200).toString());
//            //greeting.setText(getString(R.string.hello_user, profile.getFirstName()));
//
//        } else {
//            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.user_default);
//            profilepicImageView.setImageBitmap(icon);
//                    //setImageBitmap(ImageHelper.getRoundedCornerBitmap(getContext(), icon, 200, 200, 200, false, false, false, false));
//            textView.setText(null);
//
//        }
//    }
//
//    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public LoadProfileImage(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... uri) {
//            String url = uri[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(url).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//
//            if (result != null) {
//
//
//                Bitmap resized = Bitmap.createScaledBitmap(result,200,200, true);
//                bmImage.setImageBitmap(resized);
//                //ImageHelper.getRoundedCornerBitmap(getContext(),resized,250,200,200, false, false, false, false));
//
//            }
//        }
//    }


}
