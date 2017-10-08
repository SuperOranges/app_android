package com.sun.ckpt.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sun.ckpt.R;

/**
 * Created by 孙毅 on 2017/10/6.
 * 自定义TOPBAR
 */

public class TopBar extends RelativeLayout {
    //左右按钮
    private Button mLeftButton,mRightButton;
    //中间标题
    private TextView mTitleView;
    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mLeftParams, mTitlepParams, mRightParams;

    // 左按钮的属性值，即我们在attr_top_bar.xml文件中定义的属性

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    // 右按钮的属性值，即我们在attr_top_bar.xml文件中定义的属性

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    // 标题的属性值，即我们在attr_top_bar.xml文件中定义的属性

    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

// 映射传入的接口对象

    private topbarClickListener mListener;

    public TopBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置topbar的背景
        setBackgroundColor(0xFFC0C0C0);
        //0x后的前两位表示透明度，剩下6位是颜色代码

        // 通过这个方法，将你在attr_top_bar.xml中定义的declare-styleable
        // 的所有属性的值存储到TypedArray中

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        // 从TypedArray中取出对应的值来为要设置的属性赋值
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);

        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);

        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        //设置左按钮的文字颜色，文字背景和显示文字
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);

        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);

        mRightText = ta.getString(R.styleable.TopBar_rightText);
        //设置右按钮的文字颜色，文字背景和显示文字
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);

        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);

        mTitle = ta.getString(R.styleable.TopBar_title);
        //设置标题文字大小，标题文字颜色，标题文字


        // 获取完TypedArray的值后，一般要调用

        // recyle方法来避免重新创建的时候的错误

        ta.recycle();



        mLeftButton = new Button(context);

        mRightButton = new Button(context);

        mTitleView = new TextView(context);



        // 为创建的组件元素赋值

        // 值就来源于我们在引用的xml文件中给对应属性的赋值

        mLeftButton.setTextColor(mLeftTextColor);

        mLeftButton.setBackground(mLeftBackground);

        mLeftButton.setText(mLeftText);



        mRightButton.setTextColor(mRightTextColor);

        mRightButton.setBackground(mRightBackground);

        mRightButton.setText(mRightText);



        mTitleView.setText(mTitle);

        mTitleView.setTextColor(mTitleTextColor);

        mTitleView.setTextSize(mTitleTextSize);

        mTitleView.setGravity(Gravity.CENTER);



        // 为组件元素设置相应的布局元素

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 左按钮添加到ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        // 右按钮添加到ViewGroup
        addView(mRightButton, mRightParams);



        mTitlepParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

        mTitlepParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        // 标题添加到ViewGroup
        addView(mTitleView, mTitlepParams);



        // 按钮的点击事件，不需要具体的实现，

        // 只需调用接口的方法，回调的时候，会有具体的实现

        mRightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                mListener.rightClick();

            }

        });

        mLeftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                mListener.leftClick();

            }

        });

    }



    // 暴露一个方法给调用者来注册接口回调

    // 通过接口来获得回调者对接口方法的实现

    public void setOnTopbarClickListener(topbarClickListener mListener) {

        this.mListener = mListener;

    }



    /**

     * 设置按钮的显示与否 通过id区分按钮，flag区分是否显示

     *

     * @param id   id

     * @param flag 是否显示

     */

    public void setButtonVisable(int id, boolean flag) {

        if (flag) {

            if (id == 0) {

                mLeftButton.setVisibility(View.VISIBLE);

            } else {

                mRightButton.setVisibility(View.VISIBLE);

            }

        } else {

            if (id == 0) {

                mLeftButton.setVisibility(View.GONE);

            } else {

                mRightButton.setVisibility(View.GONE);

            }

        }

    }



    // 接口对象，实现回调机制，在回调方法中

    // 通过映射的接口对象调用接口中的方法

    // 而不用去考虑如何实现，具体的实现由调用者去创建

    public interface topbarClickListener {

        // 左按钮点击事件

        void leftClick();

        // 右按钮点击事件

        void rightClick();

    }
}
