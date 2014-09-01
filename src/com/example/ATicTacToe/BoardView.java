package com.example.ATicTacToe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yngvi on 1.9.2014.
 */
public class BoardView extends View {

    private Paint m_paint = new Paint();
    private ShapeDrawable m_shape = new ShapeDrawable( new OvalShape() );
    private Rect m_rect = new Rect();

    private int m_cellSize;

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure( int widthMS, int heightMS ) {
        super.onMeasure( widthMS, heightMS );
        int size = Math.min( getMeasuredWidth(), getMeasuredHeight() );
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onSizeChanged( int xNew, int yNew, int xOld, int yOld) {
        m_cellSize =  xNew / 3;
    }

    @Override
    protected void onDraw( Canvas canvas ) {

        for ( int r=0; r<3; ++r ) {
            for (int c = 0; c < 3; ++c) {
                int x = c * m_cellSize;
                int y = r * m_cellSize;
                m_rect.set(x, y, x + m_cellSize, y + m_cellSize);
                m_shape.setBounds(m_rect);
                m_shape.getPaint().setColor(Color.RED);
                m_shape.draw(canvas);
            }
        }
    }

}
