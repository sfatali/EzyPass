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
                .page(new TitlePage(R.drawable.ic_info_black_24dp, getResources().getString(R.string.Welcome_Title_Text_Weclome)))
                .page(new BasicPage(R.drawable.ic_info_black_24dp, getResources().getString(R.string.Welcome_Title_Text_NoRegistration), getResources().getString(R.string.Welcome_Text_NoRegistration)))
                .page(new BasicPage(R.drawable.ic_info_black_24dp, getResources().getString(R.string.Welcome_Title_Text_Secure), getResources().getString(R.string.Welcome_Text_Secure)))
                .page(new BasicPage(R.drawable.ic_info_black_24dp, getResources().getString(R.string.Welcome_Title_Text_EzyToUse), getResources().getString(R.string.Welcome_Text_EzyToUse)))
                .build();
    }
}
