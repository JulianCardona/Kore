package com.pixelleafs.kore.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class KoreFragmentNavigator {

    public static void navigateTo(FragmentManager manager, Fragment fragment, int containerId){
        navigateTo(
                manager,
                fragment,
                containerId,
                fragment.getClass().getSimpleName(),
                false,
                false
        );
    }

    public static void navigateTo(FragmentManager manager, Fragment fragment, int containerId,boolean addToBackStack){
        navigateTo(
                manager,
                fragment,
                containerId,
                fragment.getClass().getSimpleName(),
                addToBackStack,
                false
        );
    }

    public static void navigateTo(FragmentManager manager,
                                  Fragment fragment,
                                  int containerId,
                                  String fragmentTag,
                                  boolean addToBackStack,
                                  boolean allowCommitStateLoss){
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(containerId, fragment,fragmentTag);

        if(addToBackStack) {
            ft.addToBackStack(fragmentTag);
        }

        if(allowCommitStateLoss){
            ft.commitAllowingStateLoss();
        }
        else {
            ft.commit();
        }
    }

    public static void cleanFragmentStack(FragmentManager fm) {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
