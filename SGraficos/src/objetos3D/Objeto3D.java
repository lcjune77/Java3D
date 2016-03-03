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

package objetos3D;

import com.microcrowd.loader.java3d.max3ds.Loader3DS;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import java.io.FileNotFoundException;
import java.util.logging.*;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
public  abstract class Objeto3D extends BranchGroup {

    String file;
    int tipo;
    
    Interpolator translator;
    RotationInterpolator rotator;
    private Alpha transAlpha;
    private Alpha rotationAlpha;
    public Objeto3D(){};


    /**
     * Contructor de la clase Objeto3D
     * @param f String Ruta del fichero .obj
     * @param t tipo de objeto 0 ->.obj 1 ->.3DS
     *
     * aclaración la carga de los objetos .3DS no funciona bien
     */
    public Objeto3D(String f,int t) {
        Scene s;
        tipo =t;
        file = f;
        if(tipo ==0){
            ObjectFile objeto;
            objeto = new ObjectFile();
            objeto.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE );
            try {
                s = objeto.load(file);           
                this.addChild(s.getSceneGroup());
            } catch (FileNotFoundException | IncorrectFormatException | ParsingErrorException ex) {
                Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tipo ==1){
            try {
                 Loader3DS loader= new Loader3DS();
                s = loader.load(file);
                 this.addChild(s.getSceneGroup());
            } catch (IncorrectFormatException | ParsingErrorException | FileNotFoundException ex) {
                Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


/**
 *
 * @param d Vector3d - valores para el desplazamiento  (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 *
 * @return TransformGroup contiene el objeto al que se aplica las
 * transformaciones con los valores que se pasa a la función
     */


    public TransformGroup getObjeto(Vector3d d, Vector3d e, double rx, double ry, double rz) {
        TransformGroup t = getTransformGroup(d, e, rx, ry, rz);
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        t.addChild(this);
        return t;
    }


    /**
 *
 * @param d Vector3d - valores para el desplazamiento (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 *
 * @return TransformGroup contiene la matriz de transformación
     */
    public  TransformGroup getTransformGroup(Vector3d d, Vector3d e, double rx, double ry, double rz) {
        return new TransformGroup(getTransform3D(d, e, rx, ry, rz));
    }

/**
 *
 * @param d Vector3d - valores para generar el desplazamiento (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 *
 * @return t Transform3D contiene las transformaciones aplicadas
 */
    public Transform3D getTransform3D(Vector3d d, Vector3d e, double rx, double ry, double rz) {

        Transform3D t = new Transform3D();
        Transform3D taux = new Transform3D();


         //escala
        if (e != null) { taux.setScale(e); }        t.mul(taux);
         //traslación
        if (d != null) { taux.setTranslation(d); }  t.mul(taux);
        //rotación en X
        taux.rotX(rx);        t.mul(taux);
        //rotación en Y
        taux.rotY(ry);        t.mul(taux);
        //rotación en Z
        taux.rotZ(rz);        t.mul(taux);

        return t;
    }

 /**
 *
 * @param d Vector3d - valores para el desplazamiento (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 * @param i - periodo ejemplo(60000)1 min
 * @param Axis - Transform3D eje de giro
 * @param ini  -  float posición inicial
 * @param fin  - float posicion final
 *
 * @return TransformGroup
     */
    public TransformGroup getObjetoMovil_Traslacion(Vector3d d, Vector3d e, double rx, double ry, double rz,
                                                    long i,Transform3D Axis ,float ini,float fin)
    {
        // Transform3D Axis = new Transform3D();
        TransformGroup obTrans = new TransformGroup();
        obTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        obTrans.addChild( getObjeto(d,e, rx,ry,rz));
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);
	transAlpha = new Alpha(-1,
                                            Alpha.INCREASING_ENABLE |
                                            Alpha.DECREASING_ENABLE,
                                            0, 0,
                                            i, 0, 0,
                                            i, 0, 0);
       // Axis.rotZ(-Math.PI/2);//
	translator =   new PositionInterpolator(transAlpha,
                                                                obTrans,
                                                                Axis,
                                                                ini,fin);
	translator.setSchedulingBounds(bounds);
        obTrans.addChild(translator);

        return obTrans;
    }

    /**
 *
 * @param d Vector3d - valores para el desplazamiento (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 * @param i long - periodo ejemplo(60000)1 min
 * @param Axis - Transform3D eje de giro
 * @param ini  -  float posición inicial
 * @param fin  - float posicion final
 *
 * @return TransformGroup
     */
    public TransformGroup getObjetoMovil_Rotacion(Vector3d d, Vector3d e, double rx, double ry, double rz ,
                                                  long i,Transform3D Axis,float ini,float fin) {
        TransformGroup objTrans= new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        objTrans.addChild(getObjeto(d,  e, rx,ry,rz));
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);


	rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE,
					0, 0,
					i, 0, 0,
					0, 0, 0);
	rotator = new RotationInterpolator(rotationAlpha,  objTrans, Axis, ini,fin);
	rotator.setSchedulingBounds(bounds);
	objTrans.addChild(rotator);

        return objTrans;
    }
/**
 * funciones utiles para iniciar y parar los movimientos tanto rotación como traslación
 * ya que los atributos que generan el movimiento son privados
 * por lo que son necesarios estos métodos
 * @return
 */
    public boolean enable(){
        return rotator.getEnable();
    }
    public void pararTraslacion(){
        translator.setEnable(false);
        transAlpha.pause();
    }
     public void mueveTraslacion(){
        translator.setEnable(true);
        transAlpha.resume();
    }
     public void pararRotacion(){
        rotator.setEnable(false);
        rotationAlpha.pause();
    }
     public void mueveRotacion(){
        rotator.setEnable(true);
        rotationAlpha.resume();
    }
}

