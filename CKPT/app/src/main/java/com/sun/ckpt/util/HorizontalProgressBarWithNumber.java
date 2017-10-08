package com.sun.ckpt.util;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.content.res.TypedArray;

import com.sun.ckpt.R;
import com.sun.ckpt.util.DensityUtils;

/**
 * Created by 孙毅 on 2017/10/6.
 */

public class HorizontalProgressBarWithNumber extends ProgressBar {
    private static  final  int DEFAULT_PROGRESS_TEXT_SIZE =10;
    private static  final  int DEFAULT_PROGRESS_TEXT_COLOR=0XFFFC00D1;
    private static  final  int DEFAULT_PROGRESS_UNREACHED_COLOR=0xFFd3d6da;
    private static  final  int DEFAULT_PROGRESS_UNREACHED_BAR_HEIGHT=2;
    private static  final  int DEFAULT_PROGRESS_REACHED_BAR_HEIGHT=2;
    private static  final  int DEFAULT_PROGRESS_OFFSET=10;

    Context context = getContext();
    //paint 来个画笔
    protected Paint mPaint = new Paint();
    // ProgressBar text color 进度条文字颜色
    protected int mTextColor = DEFAULT_PROGRESS_TEXT_COLOR;
    //text size(sp) 进度条文字大小
    protected  int mTextSize = DensityUtils.sp2px(context,DEFAULT_PROGRESS_TEXT_SIZE);
    //offset of text 进度条文字偏移量
    protected int mTextOffset = DensityUtils.sp2px(context,DEFAULT_PROGRESS_OFFSET);
    /**
     * height of reached progress bar
     */
    protected int mProgressReachedBarHeight = DensityUtils.dp2px(context,DEFAULT_PROGRESS_REACHED_BAR_HEIGHT);

    /**
     * color of reached bar
     */
    protected int mProgressReachedBarColor = DEFAULT_PROGRESS_TEXT_COLOR;
    /**
     * color of unreached bar
     */
    protected int mProgressUnReachedBarColor = DEFAULT_PROGRESS_UNREACHED_COLOR;
    /**
     * height of unreached progress bar
     */
    protected int mProgressUnReachedBarHeight = DensityUtils.dp2px(context,DEFAULT_PROGRESS_UNREACHED_BAR_HEIGHT);
    /**
     * view width except padding
     */
    protected int mRealWidth;

    protected boolean mIfDrawText = true;

    protected static final int VISIBLE = 0;


    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs,
                                           int defStyle)
    {
        super(context, attrs, defStyle);

        setHorizontalScrollBarEnabled(true);

        obtainStyledAttributes(attrs);

        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);

    }
    /**
     * get the styled attributes
     *
     * @param attrs
     */
    private void obtainStyledAttributes(AttributeSet attrs)
    {
        // init values from custom attributes
        final TypedArray attributes = getContext().obtainStyledAttributes(
                attrs, R.styleable.HorizontalProgressBarWithNumber);

        mTextColor = attributes
                .getColor(
                        R.styleable.HorizontalProgressBarWithNumber_progress_text_color,
                        DEFAULT_PROGRESS_TEXT_COLOR);
        mTextSize = (int) attributes.getDimension(
                R.styleable.HorizontalProgressBarWithNumber_progress_text_size,
                mTextSize);

        mProgressReachedBarColor = attributes
                .getColor(
                        R.styleable.HorizontalProgressBarWithNumber_progress_reached_color,
                        mTextColor);
        mProgressUnReachedBarColor = attributes
                .getColor(
                        R.styleable.HorizontalProgressBarWithNumber_progress_unreached_color,
                        DEFAULT_PROGRESS_UNREACHED_COLOR);
        mProgressReachedBarHeight = (int) attributes
                .getDimension(
                        R.styleable.HorizontalProgressBarWithNumber_progress_reached_bar_height,
                        mProgressReachedBarHeight);
        mProgressUnReachedBarHeight = (int) attributes
                .getDimension(
                        R.styleable.HorizontalProgressBarWithNumber_progress_unreached_bar_height,
                        mProgressUnReachedBarHeight);
        mTextOffset = (int) attributes
                .getDimension(
                        R.styleable.HorizontalProgressBarWithNumber_progress_text_offset,
                        mTextOffset);

        int textVisible = attributes
                .getInt(R.styleable.HorizontalProgressBarWithNumber_progress_text_visibility,
                        VISIBLE);
        if (textVisible != VISIBLE)
        {
            mIfDrawText = false;
        }
        attributes.recycle();
    }
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec,
                                          int heightMeasureSpec)
    {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode != MeasureSpec.EXACTLY)
        {

            float textHeight = (mPaint.descent() + mPaint.ascent());
            int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() + Math
                    .max(Math.max(mProgressReachedBarHeight,
                            mProgressUnReachedBarHeight), Math.abs(textHeight)));

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,
                    MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
    @Override
    protected synchronized void onDraw(Canvas canvas)
    {
        canvas.save();
        //画笔平移到指定paddingLeft， getHeight() / 2位置，注意以后坐标都为以此为0，0
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        boolean noNeedBg = false;
        //当前进度和总值的比例
        float radio = getProgress() * 1.0f / getMax();
        //已到达的宽度
        float progressPosX = (int) (mRealWidth * radio);
        //绘制的文本
        String text = getProgress() + "%";

        //拿到字体的宽度和高度
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

        //如果到达最后，则未到达的进度条不需要绘制
        if (progressPosX + textWidth > mRealWidth)
        {
            progressPosX = mRealWidth - textWidth;
            noNeedBg = true;
        }

        // 绘制已到达的进度
        float endX = progressPosX - mTextOffset / 2;
        if (endX > 0)
        {
            mPaint.setColor(mProgressReachedBarColor);
            mPaint.setStrokeWidth(mProgressReachedBarHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        // 绘制文本
        if (mIfDrawText)
        {
            mPaint.setColor(mTextColor);
            canvas.drawText(text, progressPosX, -textHeight, mPaint);
        }

        // 绘制未到达的进度条
        if (!noNeedBg)
        {
            float start = progressPosX + mTextOffset / 2 + textWidth;
            mPaint.setColor(mProgressUnReachedBarColor);
            mPaint.setStrokeWidth(mProgressUnReachedBarHeight);
            canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
        }

        canvas.restore();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mRealWidth = w - getPaddingRight() - getPaddingLeft();

    }
}
