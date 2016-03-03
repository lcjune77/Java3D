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
package Escena;

import static Utiles.Luces.getLuzPuntual;
import java.util.Enumeration;
import java.util.Random;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public final class Agua extends BranchGroup{

        protected GeometryUpdater geometryUpdater = new WaterUpdater();
        protected float baseElevation = 0.1f;
        protected LineArray waterLines = null;
       

        class UpdateWaterBehavior extends Behavior{

          WakeupOnElapsedFrames w = null;

          public UpdateWaterBehavior(){
            w = new WakeupOnElapsedFrames(0);
          }

          @Override
          public void initialize(){
            wakeupOn(w);
          }

          @Override
          public void processStimulus(Enumeration critiria){
            waterLines.updateData(geometryUpdater);
            wakeupOn(w);
          } // end processStimulus

        } // end class UpdateWaterBehavior

        public class WaterUpdater implements GeometryUpdater{

          Random random;
          GeometryArray geometryArray;
          float[] vertices;
          int N, i;

          public WaterUpdater(){
            random = new Random();
          }

          @Override
          public void updateData(Geometry geometry){

            geometryArray = (GeometryArray)geometry;
            vertices = geometryArray.getCoordRefFloat();
            N = geometryArray.getValidVertexCount();

            for(i = 0; i < N; i+=2){        // for each particle
              if(vertices[i*3+1] > baseElevation){  // if y = 0
                  vertices[i*3+0] += vertices[i*3+0] - vertices[i*3+3];      //x1=2x1-x2
                  vertices[i*3+1] += vertices[i*3+1] - vertices[i*3+4]-0.01f; //y1=2y1-y2-c
                  vertices[i*3+2] += vertices[i*3+2] - vertices[i*3+5];      //z1=2z1-z2
                  vertices[i*3+3] = (vertices[i*3+0]+vertices[i*3+3])/2;   //x2 = old x1
                  vertices[i*3+4] = (vertices[i*3+1]+vertices[i*3+4]+0.01f)/2;//y2 = old y2
                  vertices[i*3+5] = (vertices[i*3+2]+vertices[i*3+5])/2;   //z2 = old z1
                  if(vertices[i*3+1] < baseElevation){  // if y < 0
                    vertices[i*3+0] = 0.0f; //x1
                    vertices[i*3+1] = baseElevation; //y1
                    vertices[i*3+2] = 0.0f; //z1
                    vertices[i*3+3] = 0.0f; //x2
                    vertices[i*3+4] = baseElevation; //y2
                    vertices[i*3+5] = 0.0f; //z2
                  }
              } else { // randomly start a drop
                 if(random.nextFloat() > 0.8){
                   vertices[i*3+0] = 0.03f*(random.nextFloat()-0.5f); //x1
                   vertices[i*3+1] = 0.14f*random.nextFloat()+baseElevation; //y1
                   vertices[i*3+2] = 0.03f*(random.nextFloat()-0.5f); //z1
                } // end if
              } // end if-else
            } // end for loop
          } // end updateData(Geometry)
        } // end of class WaterUpdater

        public Behavior getWaterBehavior(){
          return new UpdateWaterBehavior();
        }

        public GeometryUpdater getWaterUpdater(){
           return geometryUpdater;
        }
        public Agua() {

            Appearance waterAppear;           
            waterAppear = createWaterAppearance();
            this.addChild(new Shape3D(createWaterGeometry(),waterAppear));
            this.addChild(getLuzPuntual(0.5f,new Color3f(0.3f,0.5f,05f),new Point3f(0,.5f,0),new Point3f(0.01f,.5f,.01f)));

        } // end constructor

        Geometry createWaterGeometry(){

            int N = 1400;                   // number of 'drops'

            waterLines = new LineArray(N*2,
              LineArray.COORDINATES | LineArray.BY_REFERENCE);

            waterLines.setCapability(GeometryArray.ALLOW_REF_DATA_READ);
            waterLines.setCapability(GeometryArray.ALLOW_REF_DATA_WRITE);
            waterLines.setCapability(GeometryArray.ALLOW_COUNT_READ);

            float[] coordinates = new float[N*3*2];

            int p;
            for(p = 0; p < N; p+=2){        // for each particle
                  coordinates[p*3+0] = 0.0f;
                  coordinates[p*3+1] = baseElevation;
                  coordinates[p*3+2] = 0.0f;
                  coordinates[p*3+3] = 0.0f;
                  coordinates[p*3+4] = baseElevation;
                  coordinates[p*3+5] = 0.0f;
            }
            waterLines.setCoordRefFloat(coordinates);

            return waterLines;

        } // end of createWaterGeometry

        Appearance createWaterAppearance(){

            Appearance appear = new Appearance();
            appear.setColoringAttributes( new ColoringAttributes(1f,1f,1f,ColoringAttributes.ALLOW_SHADE_MODEL_WRITE));

            LineAttributes lineAttributes = new LineAttributes();
            lineAttributes.setLineAntialiasingEnable(true);
            appear.setLineAttributes(lineAttributes);
            return appear;
        }

    } 
