package com.ticka.application.models;

import android.support.v4.app.Fragment;

import com.ticka.application.R;
import com.ticka.application.fragments.CapacityFragment;
import com.ticka.application.fragments.GeneralDetailsFragment;
import com.ticka.application.fragments.MinorDetailsFragment;
import com.ticka.application.fragments.PossibilitiesFragment;
import com.ticka.application.fragments.PriceFragment;
import com.ticka.application.fragments.RulesFragment;
import com.ticka.application.fragments.SettingsFragment;
import com.ticka.application.fragments.UploadPhotoFragment;
import com.ticka.application.fragments.UserRulesFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SideList implements Serializable {

    public static int[] ICONS = {
            R.drawable.icon_shield_check,
            R.drawable.icon_information_variant,
            R.drawable.icon_image,
            R.drawable.icon_clipboard_text,
            R.drawable.icon_account_supervisor,
            R.drawable.icon_seat_flat,
            R.drawable.icon_mace,
            R.drawable.icon_credit_card
    };

    public static String[] TITLES = {
            "قوانین همکاری",
            "اطلاعات کلی",
            "بارگیری تصاویر",
            "اطلاعات جزعی",
            "ظرفیت",
            "امکانات",
            "قوانین و مقررات",
            "قیمت گذاری"
    };

    public static List<Fragment> getFragments(){

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new RulesFragment());
        fragments.add(new GeneralDetailsFragment());
        fragments.add(new UploadPhotoFragment());
        fragments.add(new MinorDetailsFragment());
        fragments.add(new CapacityFragment());
        fragments.add(new PossibilitiesFragment());
        fragments.add(new UserRulesFragment());
        fragments.add(new PriceFragment());

        return fragments;
    }

}
