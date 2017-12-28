package com.qxrj.net.index;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qxrj.net.Director.OKHttp.OkHttpDataPakege;
import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.R;
import com.qxrj.net.VIEW.QXActivity;
import com.qxrj.net.VIEW.QXFragment;
import com.qxrj.net.index.mvp.IndexPresenter;

/**
 * Created by luyuan on 2017/6/4.
 */

public class IndexActivity extends QXActivity {
    private String                                      TAGLOG = "IndexActivity";
    private IndexActivity                               app = null;
    private IndexPresenter                              mPresenter = null;
    private SectionsPagerAdapter                        mSectionsPagerAdapter;
    private ViewPager                                   mViewPager = null;

    @Override
    public void QXPause() {
        super.QXPause();
    }

    @Override
    public void QXResume() {
        super.QXResume();
    }

    @Override
    public void QXDestroy() {
        super.QXDestroy();
    }

    @Override
    public int createContentViewResId() {
        return R.layout.activity_index;
    }

    @Override
    public IndexPresenter createPresenter() {
        mPresenter = new IndexPresenter(this);
        return mPresenter;
    }

    @Override
    public void initView() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);

            if (tab != null) {
                tab.setCustomView(mSectionsPagerAdapter.getTabView(i));
            }
        }
    }

    @Override
    public void onOkHttpClientSuccess(OkHttpDataPakege _dataPakege) {
        super.onOkHttpClientSuccess(_dataPakege);
        QXFragment qxFragment = mSectionsPagerAdapter.getFragment(mViewPager.getCurrentItem());
        qxFragment.getOnOkHttpSuccess(_dataPakege);
    }

    @Override
    public void onOkHttpClientFail(OkHttpDataPakege _dataPakege) {
        super.onOkHttpClientFail(_dataPakege);
        QXFragment qxFragment = mSectionsPagerAdapter.getFragment(mViewPager.getCurrentItem());
        qxFragment.getOnOkHttpFail(_dataPakege);
    }

    @Override
    public void onOkHttpFinsh(OkHttpDataPakege _dataPakege) {
        super.onOkHttpFinsh(_dataPakege);
        QXFragment qxFragment = mSectionsPagerAdapter.getFragment(mViewPager.getCurrentItem());
        qxFragment.getOkHttpFinsh(_dataPakege);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private Context                 mContext;

        private QXFragment                  mFrament[] = new QXFragment[] {
                new IndexFragment(),
                new MyDiaryFragment(),
                new MyFragment()
        };

        private int                         mFragmentButtonId[] = {
                R.drawable.fn_ico_home,
                R.drawable.fn_ico_tour,
                R.drawable.fn_ico_mine
        };

        private int                         mTabTitles[] = {
                R.string.title_fragment_index,
                R.string.title_fragment_my_diary,
                R.string.title_fragment_my
        };

        public SectionsPagerAdapter(FragmentManager fm, Context _context) {
            super(fm);
            mContext = _context;
        }

        public View getTabView(int position) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.tab_fragment, null);
            TextView tv = (TextView) v.findViewById(R.id.textView);
            tv.setText(mTabTitles[position]);

            ImageView img = (ImageView) v.findViewById(R.id.imageView);
            img.setImageResource(mFragmentButtonId[position]);

            if (position != 0) {
                img.setSelected(false);
            }

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return mFrament[position];
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return mTabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        public QXFragment getFragment(int position) {
            return mFrament[position];
        }
    }
}
