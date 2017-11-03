package br.com.gearsoft.doarsangue.activities.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import br.com.gearsoft.doarsangue.BuildConfig;
import br.com.gearsoft.doarsangue.MainActivity;
import br.com.gearsoft.doarsangue.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String UNCHANGED_CONFIG_VALUE = "CHANGE-ME";
    private static final String GOOGLE_TOS_URL = "https://www.google.com/policies/terms/";
    private static final String FIREBASE_TOS_URL = "https://firebase.google.com/terms/";
    private static final String GOOGLE_PRIVACY_POLICY_URL = "https://www.google.com/policies/privacy/";
    private static final String FIREBASE_PRIVACY_POLICY_URL = "https://firebase.google.com/terms/analytics/#7_privacy";

    private static final int RC_SIGN_IN = 8907; // max value 65.535

    private FirebaseAuth mAuth;
    private boolean mIsSigningIn;

    @BindView(R.id.login_root)
    View mRootView;

    @MainThread
    private void showSnackbar(String errorMessageRes) {
        Snackbar.make(mRootView, errorMessageRes, Snackbar.LENGTH_LONG).show();
    }

    public static Intent createIntent(Context context){
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        if (!shouldStartSignIn()) {
            startMainActivity(null);
            finish();
            return;
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data);
            return;
        }

        showSnackbar("unknown_response");
    }

    @MainThread
    private void handleSignInResponse(int resultCode, Intent data) {

        IdpResponse response = IdpResponse.fromResultIntent(data);
        mIsSigningIn = false;
        // Successfully signed in
        if (resultCode == RESULT_OK) {
            startMainActivity(response);
            finish();
            return;
        } else {
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

    private void startMainActivity(IdpResponse response) {
        startActivity(MainActivity.createIntent(this, response));
    }

    private boolean shouldStartSignIn() {
        return (!mIsSigningIn && mAuth.getCurrentUser() == null);
    }

    private void startSignIn() {
        Intent intent =
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setLogo(AuthUI.NO_LOGO)
                        .setTheme(AuthUI.getDefaultTheme())
                        .setTosUrl(GOOGLE_TOS_URL)
                        .setPrivacyPolicyUrl(GOOGLE_PRIVACY_POLICY_URL)
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())
                        )
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG)// desabilita em dev habilita em prod
                        .build();
        mIsSigningIn = true;
        startActivityForResult(intent, RC_SIGN_IN);
    }

    public void onLoginButtonPressed(View view) {
        startSignIn();
    }
}
