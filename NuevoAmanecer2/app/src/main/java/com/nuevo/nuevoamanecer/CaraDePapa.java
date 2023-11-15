package com.nuevo.nuevoamanecer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class CaraDePapa extends AppCompatActivity {
    //ImageView papaView;
    ImageView ojosView;

    private ViewGroup mainLayout;
    private int xDelta;
    private int yDelta;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cara_de_papa);
        ojosView = (ImageView) findViewById(R.id.ojosView);

        mainLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        ojosView = (ImageView) findViewById(R.id.ojosView);
        //image2 = (ImageView) findViewById(R.id.image2);

        ojosView.setOnTouchListener(onTouchListener());
        //image2.setOnTouchListener(onTouchListener());

        /*
        ojosView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                    float x = event.getX();
                    float y = event.getY();
                }

                if (event.getAction() == DragEvent.ACTION_DROP) {
                    float dx = event.getX() - x;
                    float dy = event.getY() - y;

                    ojosView.setX(ojosView.getX() + dx);
                    ojosView.setY(ojosView.getY() + dy);

                }
                return false;
            }
        });
         */

        /*
        ojosView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ActionBar.LayoutParams layoutParams = (ActionBar.LayoutParams) ojosView.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x_cord = (int) event.getRawX();
                        int y_cord = (int) event.getRawY();

                        if (x_cord > windowwidth) {
                            x_cord = windowwidth;
                        }
                        if (y_cord > windowheight) {
                            y_cord = windowheight;
                        }

                        layoutParams.leftMargin = x_cord - 25;
                        layoutParams.topMargin = y_cord - 75;

                        balls.setLayoutParams(layoutParams);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        */

        /*
        papaView.setOnTouchListener(new View.OnTouchListener()) {
            @override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xDown = event.getX();
                        yDown = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX, movedY;
                        movedX = event.getX();
                        movedY = event.getY();

                        float distanceX = movedX - xDown;
                        float distanceY = movedY - yDown;

                        papaView.setX(papaView.getX() + distanceX);
                        papaView.setX(papaView.getY() + distanceY);

                        break;
                }

                return true;
            }
        });
        */
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

    /*
    float x, y;
    float dx, dy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            dx = event.getX() - x;
            dy = event.getY() - y;

            ojosView.setX(ojosView.getX() + dx);
            ojosView.setY(ojosView.getY() + dy);

            x = event.getX();
            y = event.getY();
        }

        return super.onTouchEvent(event);
    }
     */
}
