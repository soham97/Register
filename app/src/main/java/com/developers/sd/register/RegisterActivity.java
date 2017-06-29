package com.developers.sd.register;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by sohamdeshmukh on 22/06/17.
 */

public class RegisterActivity extends SingleFragmentActivity {

    private static final String EXTRA_REGISTER_ID =
            "com.developers.sd.register.register_id";

    public static Intent newIntent(Context packageContext, UUID registerId) {
        Intent intent = new Intent(packageContext, RegisterActivity.class);
        intent.putExtra(EXTRA_REGISTER_ID, registerId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID registerId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_REGISTER_ID);
        return RegisterFragment.newInstance(registerId);
    }
}
