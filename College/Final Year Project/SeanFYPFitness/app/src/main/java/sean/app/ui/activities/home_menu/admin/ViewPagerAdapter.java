package sean.app.ui.activities.home_menu.admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final ArrayList<Fragment> listOfFragments;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        super(fragmentActivity);
        listOfFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return listOfFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return listOfFragments.size();
    }

}

