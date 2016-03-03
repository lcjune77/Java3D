/*******************************************************************************
 *
 * Asignatura:  Sistemas Gráficos -Prácticas
 * Tema:        Parque de atracciones (Noria, Tiovivo, Coches de choque,La Barca)
 * Curso :      2013 - 2014
 * Universidad: UGR
 * @author      Alicia Lara
 * @author      Marlene Vásquez
 * @version     1.0
 *
 ******************************************************************************/

package CochesDeChoque;

import Utiles.PATH;
import static java.lang.Math.PI;
import static java.lang.Math.random;
import java.util.ArrayList;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import objetos3D.Objeto3D;

public final class C_EstructuraMovil extends Objeto3D {

    C_Coche coche;
    String file,file2 ;
    C_Colisiones dC;
    public ArrayList <C_Coche> coches;
    private static final int NUM=9;
    private static final int NUMCOCHES=4;
    private final double[] x = new double[NUM];
    private final double[] z = new double[NUM];
    private final double[] dir = new double[NUM];
    BoundingSphere bounds;
    //private final TransformGroup tg[]= new TransformGroup[NUM];
    private Point3f[] positions = new Point3f[9];
    private Quat4f[] quats = new Quat4f[9];
    Alpha alpha;
    RotPosPathInterpolator rotPosPath;
    private int counter;
    BoundingSphere [] _bounds;

    public C_EstructuraMovil(){
        this._bounds = new BoundingSphere[NUMCOCHES];
        
        this.counter = 0;
        this.setName("movil");
        this.bounds = new BoundingSphere(new Point3d(0.0, 0, 0.0),2);
          
        this.coches = new ArrayList<>();

        file  = PATH.OBJ + "coche.obj";
        file2 = PATH.OBJ + "coche_1.obj";
     //   getEstructura_Movil(c);
        
        addCoche();
        compile();

    }

    public void addCoche()  {
        for(int i=0; i < NUMCOCHES; i++){

            if(counter%2==0)    coche = new C_Coche( file2,0);
            else                coche = new C_Coche( file,0);
              _bounds[i]= coche.Bounds();
              
            inicializar();
            moverobj();
            this.addChild( coche.movil(positions, quats));
            coches.add(coche);
            counter++;
          }
    }  // end of addCoche()


       
        
    
public void redraw(){
     for(C_Coche ps: coches){
         inicializar();
         moverobj();
      ps.redraw(positions,quats);
     }
}

     public void inicializar(){
           //System.out.println("--iniciar----");
        for(int i=0 ;i<9;i++){
            x[i]=random()*4-2;
            z[i]=random()*4-2;
            dir[i]=random()*PI;
            positions[i]= new Point3f(  (float)x[i] , 0.15f, (float)z[i]);
//            System.out.println("x: "+x[i]+"  z: "+z[i] + " dir: "+dir[i]);

        }
//         System.out.println();
    }
/**
 *
 */
    public void moverobj(){
        //System.out.println("--mover----");
        int lim= 2;
        for(int i=0 ;i<9;i++){
            x[i]+=Math.cos(dir[i]*0.3);

            if(x[i]<lim){
              x[i]+= - lim;
              dir[i]=PI/4;
            }else if(x[i]>lim){
                x[i]=lim;
              dir[i]+=PI/4;
            }
            z[i]=Math.sin(dir[i]*0.3);

            if(z[i]<lim){
              z[i]+= - lim;
              dir[i]=PI/4;
            }else if(z[i]>lim){
                z[i]=lim;
              dir[i]+=PI/4;
            }
                dir[i]+=PI/4;
            dir[i]=Math.random()*0.3-0.1;
             quats[i] = new Quat4f((float)x[i], 0.0f,(float)z[i], 0);
//               System.out.println("x: "+x[i]+"  z: "+z[i] + " dir: "+dir[i]);
        }

    }
    /**
     * Para el movimiento de los coches
     */
    public void parar(){
            for(int i=0; i < NUMCOCHES; i++){
                coches.get(i).parar();
            }
      
    }
    /**
     * habilita el mivimiento de los coches
     */
    public void mover(){
         for(int i=0; i < NUMCOCHES; i++){
                coches.get(i).mover();
            }
    }
    @Override
    public boolean enable(){
        boolean rs = false;
         for(int i=0; i < NUMCOCHES; i++){
               rs= coches.get(i).enable();
            }
         return rs;
    }
}