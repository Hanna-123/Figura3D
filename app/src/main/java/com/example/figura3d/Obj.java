package com.example.figura3d;

import android.renderscript.Sampler;
import android.util.Log;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Obj {
    double rho, theta=0.3F, phi=1.3F, d, objSize, v11, v12, v13, v21, v22, v23, v32, v33, v43; // elementos de la matriz V
    Point3D [] w;	// coordenadas universales
    Point2D [] vScr; // coordenadas de la pantalla
    int n_lados;
    String figura;
    double angulo;
    Obj(String fig, int num_lados){
        n_lados = num_lados;
        figura = fig;
        angulo = 360.0/n_lados;

        if(figura.equals("Cubo")){
            w	= new Point3D[8];
            vScr	= new Point2D[8];
            w[0]	= new Point3D(1, -1, -1); // desde la base
            w[1]	= new Point3D(1, 1, -1);
            w[2]	= new Point3D(-1, 1, -1);
            w[3]	= new Point3D(-1, -1, -1);
            w[4]	= new Point3D(1, -1, 1);
            w[5]	= new Point3D(1, 1, 1);
            w[6]	= new Point3D(-1, 1, 1);
            w[7]	= new Point3D(-1, -1, 1);
            objSize = (float) sqrt(12F);
        }else if(figura.equals("Cono")){
            w = new Point3D[n_lados+1];
            vScr = new Point2D[n_lados+1];
            for(int i =0; i<n_lados; i++){
                w[i] = new Point3D( cos(i*angulo*Math.PI/180), Math.sin(i*angulo*Math.PI/180),-1);
            }
            w[n_lados] = new Point3D(0,0,1 );
            objSize = (float) sqrt(8F);
        }else if(figura.equals("Cilindro")){
            w = new Point3D[n_lados*2];
            vScr = new Point2D[n_lados*2];
            for(int i =0; i<n_lados; i++){
                w[i] = new Point3D( cos(i*angulo*Math.PI/180), Math.sin(i*angulo*Math.PI/180),-1);
                w[i+n_lados] = new Point3D( cos(i*angulo*Math.PI/180), Math.sin(i*angulo*Math.PI/180),1);
            }
            objSize = (float) sqrt(8F);
        }else if(figura.equals("Esfera")){
            double separacion = 2.0/(n_lados+1);
            w = new Point3D[n_lados*n_lados];
            vScr = new Point2D[n_lados*n_lados];
            for (int j =0,k=0;j<n_lados;j++)
                for(int i=0; i<n_lados; i++,k++)
                    w[k] = new Point3D( sqrt(1-pow(1-separacion*(j+1), 2))*cos(i*angulo*Math.PI/180), sqrt(1- pow(1-separacion*(j+1),2))*Math.sin(i*angulo*Math.PI/180),j*.02);
            objSize = (float) sqrt(12F);
        }
        rho = 5*objSize;
    }
    void initPersp(){
        float costh = (float) cos(theta), sinth=(float)Math.sin(theta), cosph=(float) cos(phi), sinph=(float)Math.sin(phi);
        v11 = -sinth; v12 = -cosph*costh; v13 = sinph*costh;
        v21 = costh; v22 = -cosph*sinth; v23 = sinph*sinth;
        v32 = sinph; v33 = cosph; v43 = -rho;
    }
    void eyeAndScreen(){
        initPersp();
        int numero_vertices = 0;
        if(figura.equals("Cubo"))  numero_vertices = 8;
        else if(figura.equals("Cono")) numero_vertices = n_lados + 1;
        else if(figura.equals("Cilindro")) numero_vertices = n_lados*2;
        else if(figura.equals("Esfera")) numero_vertices = n_lados*n_lados;
        Log.i("FiguraActivity", "valor"+numero_vertices);
        for(int i =0; i < numero_vertices; i++){
            Point3D p = w[i];
            double x = v11*p.x + v21*p.y;
            double y = v12*p.x + v22*p.y + v32*p.z;
            double z = v13*p.x + v23*p.y + v33*p.z + v43;

            vScr[i] = new Point2D(-d*x/z, -d*y/z);
        }
    }
}
