package com.ezypass.ezypass.welcome;

import com.ezypass.ezypass.R;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

/**
 * Manage welcome pages
 */
public class MainWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.colorPrimary)
                .page(new TitlePage(R.drawable.ic_info_black_24dp, "Welcome to EzyPass"))
                .page(new BasicPage(R.drawable.ic_info_black_24dp, "No registration", "Your own key will generate all your password extensions"))
                .page(new BasicPage(R.drawable.ic_info_black_24dp, "Secure", "No server, no account, just remember your key"))
                .page(new BasicPage(R.drawable.ic_info_black_24dp, "Ezy to use", "Just enter the name of the website or application to secure your passwords."))
                .build();
    }
}
