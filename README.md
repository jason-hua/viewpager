##高仿微信主界面

* **1，Fragment+viewpager+fragmentPagerAdapter**

* **2，badgeView**

* **3，界面切换时，指示器跟随滑动**
         
         
         @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {

                Log.v("MainActivity", "position---->" + position + "positionOffset---->" + positionOffset + "mcurrent---->" + mCurrentPageIndex);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv_line.getLayoutParams();
                if (mCurrentPageIndex == 0 && position == 0) {//1-->2
                    layoutParams.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex *mScreen1_3);
                }else  if(mCurrentPageIndex==1&&position==0)//2-->1
                {
                    layoutParams.leftMargin= (int) (mCurrentPageIndex*mScreen1_3+(positionOffset-1)*mScreen1_3);
                }
                else  if(mCurrentPageIndex==1&&position==1)//2-->3
                {
                    layoutParams.leftMargin= (int) (mCurrentPageIndex*mScreen1_3+(positionOffset)*mScreen1_3);
                }
                else  if(mCurrentPageIndex==2&&position==1)//3-->2
                {
                    layoutParams.leftMargin= (int) (mCurrentPageIndex*mScreen1_3+(positionOffset-1)*mScreen1_3);
                }

                iv_line.setLayoutParams(layoutParams);
            }

           


![image](https://github.com/jason-hua/viewpager/blob/master/weixin.gif)


