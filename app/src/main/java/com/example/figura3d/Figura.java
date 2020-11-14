package com.example.figura3d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Figura extends View {
        Paint paint = new Paint();
        Paint p;
        int s = -1;
        int centerX, centerY, maxX, maxY, minMaxXY;
        Obj obj;
        double X,Y;
        String figura;
        int n_lados;
        public Figura(Context c, String fig, int n_lad){
                super(c);
                figura = fig;
                n_lados = n_lad;
                obj = new Obj(figura, n_lados);
                paint = new Paint();
                paint.setColor(Color.BLACK);
                centerY = 500;
                centerX = 700;

        }
        int iX (double x){ return (int) (centerX + x); }
        int iY(double y){ return (int) (centerY - y);}
        void line(Canvas c, int i, int j){
                Point2D p = obj.vScr[i], q = obj.vScr[j];
                c.drawLine(iX(p.x), iY(p.y),iX(q.x), iY(q.y), paint);
        }
        protected void onDraw(Canvas can){
                can.drawColor(Color.WHITE);
                maxX = getWidth()-1;
                maxY = getHeight()-1;
                minMaxXY = Math.min(maxX, maxY);
                obj.d = obj.rho*minMaxXY/obj.objSize;
                obj.eyeAndScreen();

                if(figura.equals("Cubo")){
                        line(can, 0, 1); line(can, 1, 2); line(can, 2, 3); line(can, 3, 0); // aristas horizontales inferiores
                        line(can, 4, 5); line(can, 5, 6); line(can, 6, 7); line(can, 7, 4); // aristas horizontales superiores
                        line(can, 0, 4); line(can, 1, 5); line(can, 2, 6); line(can, 3, 7); // aristas verticales
                }else if(figura.equals("Cono")){
                        for ( int i = 0; i< n_lados -1; i++ ){
                                line(can, i , i+1);
                                line(can, i , n_lados);
                        }
                        line(can, n_lados-1, 0);
                        line(can, n_lados-1 , n_lados);
                }else if(figura.equals("Cilindro")){
                        for ( int i = 0; i< n_lados -1; i++ ){
                                line(can, i , i+1);
                                line(can, i+n_lados , i+n_lados+1);
                                line(can, i , n_lados+i);

                        }
                        line(can, n_lados-1 , 0);
                        line(can, n_lados*2-1 , n_lados);
                        line(can, n_lados-1 , n_lados*2-1);
                }else if(figura.equals("Esfera")){
                        for(int j=0; j<n_lados; j++){
                                for(int i =0; i<n_lados-1; i++){
                                        line(can, i+j*n_lados, i+j*n_lados+1);
                                }
                                line(can, n_lados*(j+1)-1, n_lados*j);
                        }
                }
        }
        public boolean onTouchEvent(MotionEvent me){
                if(me.getAction() == MotionEvent.ACTION_DOWN){
                        s = -1;
                        X = me.getX();
                        Y = me.getY();

                        for(int i = 0; i < 2; i++){
                                double dx = X-centerX, dy = Y - centerY;
                                double d = (double) Math.sqrt(dx*dx + dy*dy);

                                if(d <= 75){
                                        s = i;
                                        invalidate();
                                }
                        }
                }
                if(me.getAction() == MotionEvent.ACTION_MOVE){
                        if(s > -1){
                                centerX = (int) me.getX();
                                centerY = (int) me.getY();
                                invalidate();
                        }
                }
                if(me.getX() < X) obj.theta = (double) (obj.theta - 2*Math.PI/300);
                if(me.getX() > X) obj.theta = (double) (obj.theta + 2*Math.PI/300);
                if(me.getX() < Y) obj.phi = (double) (obj.phi - 2*Math.PI/400);
                if(me.getX() > Y) obj.phi = (double) (obj.phi + 2*Math.PI/400);
                invalidate();
                return true;
        }
}
