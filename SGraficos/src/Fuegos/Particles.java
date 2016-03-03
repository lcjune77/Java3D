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
package Fuegos;

import java.util.Enumeration;
import javax.media.j3d.Appearance;
import javax.media.j3d.Behavior;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.GeometryUpdater;
import javax.media.j3d.PointArray;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupOnElapsedTime;

public class Particles extends Shape3D {
    
    private final static int POINTSIZE = 1;
    private static final float GRAVITY = 5.3f;
    private static final float TIMESTEP = 0.01f;
    private float XZ_VELOCITY;
    private float Y_VELOCITY;
    private final PointArray pointParticles; // Geometry holding the coords and colors
    private final ParticlesControl partBehav; // The Behaviour triggering the updates
    private float[] coord, vels, accs, colors, age;
    private final int numPoints;
    private static final int delay = 30;
    private static final float transAlpha = 0.5f;
    private static final float life = 100f;//700f;
    
    private float colorR;
    private float colorG;
    private float colorB;

// Constructor
    public Particles(int numParticles, float r, float g, float b, float vxz, float vy) {
        numPoints = numParticles;
        XZ_VELOCITY = vxz;
        Y_VELOCITY = vy;
        colorR=r;
        colorG=g;
        colorB=b;
        // creates a PointArray with a number of points to be created and uses the BY_REFERENCE.
        // This way we only have to update the user's arrays of coordinate etc.
        // Then the Point array will fetch automatically the values from the referenced arrays.
        pointParticles = new PointArray(numPoints, PointArray.COORDINATES | PointArray.COLOR_3 | PointArray.BY_REFERENCE);
        // We also have to allow the data of the array to be read and written.
        pointParticles.setCapability(GeometryArray.ALLOW_REF_DATA_WRITE);
        pointParticles.setCapability(GeometryArray.ALLOW_REF_DATA_READ);
        PUpdater updater = new PUpdater();
        partBehav = new ParticlesControl(delay, updater); //a new frame every delay (3 ms)
        crearGeometry();
        crearAppearance();
    }

    public Behavior getParticleBeh() {
        return partBehav;
    }

    private void crearGeometry() {
        coord = new float[numPoints * 3];
        colors = new float[numPoints * 3];
        vels = new float[numPoints * 3];
        accs = new float[numPoints * 3];
        age = new float[numPoints];
        
        for (int i = 0; i < numPoints * 3; i = i + 3) {
            setParticle(i);
        }
        // store the coordinates and colours in the PointArray
        pointParticles.setCoordRefFloat(coord); // use BY_REFERENCE
        pointParticles.setColorRefFloat(colors);
        setGeometry(pointParticles);
    }//end of createGeometry()

// Every particle here gets its initial attributes. These attributes change over time as the particle is updated.
// When a particle dies, then it is reinitialized.
    public void setParticle(int i) {
        coord[i] = 0.0f; 
        coord[i+1] = 0.0f; 
        coord[i+2] = 0.0f;
        // (x,y,z) at origin
        // random velocity in XZ plane with combined vector XZ_VELOCITY
        double xvel = Math.random()*XZ_VELOCITY;
        double zvel = Math.sqrt((XZ_VELOCITY+XZ_VELOCITY) - (xvel+xvel));
        vels[i] = (float)((Math.random()<0.5) ? -xvel : xvel); // x vel
        vels[i+2] = (float)((Math.random()<0.5) ? -zvel : zvel);// z vel
        vels[i+1] = (float)(Math.random() * Y_VELOCITY); // y vel
        // unchanging accelerations, downwards in y direction
        accs[i] = 0.0f; 
        accs[i+1] = -GRAVITY; 
        accs[i+2] = 0.0f;
        // Color definido en el constructor
        colors[i] = colorR; 
        colors[i+1] = colorG; 
        colors[i+2] = colorB;
        
        age[i / 3] = (float) Math.random() * life; // age of each particle
    }//end of setParticle(int i)


    private void crearAppearance() {
        Appearance app = new Appearance();
        //set attributes to the appearance. Size of point and transparency.
        PointAttributes pa = new PointAttributes();
        pa.setPointSize(POINTSIZE);// sets the size of the particles
        pa.setPointAntialiasingEnable(true); // turns anti-aliasing on!
        app.setPointAttributes(pa);
        TransparencyAttributes ta = new TransparencyAttributes();
        ta.setTransparencyMode(TransparencyAttributes.BLENDED);
        ta.setTransparency(transAlpha);
        app.setTransparencyAttributes(ta);
        setAppearance(app);
    }

//PUpdater inner class
    public class PUpdater implements GeometryUpdater {

        @Override
        public void updateData(Geometry geometry) {
            for(int i=0; i < numPoints*3; i=i+3) {
//                if (coord[i+1] < 0.0f) // particle dropped below y-axis
                if (age[i/3]<=0)
                    setParticle(i); // re-initialise it
                else // update the particle
                {
                    updateParticles(i);
                    age[i/3]--;
                }
            }
        }//end of updateData(Geometry geometry)

        //Here we update the position of the particle according to our laws.
        //The information that is produced is fetched from the updateData().
        public void updateParticles(int i) {
            //Change of velocity and position according to accelarated motions in physics 
            //s1 = s0+v0 * t + 1 / 2(a * t * t)
            // Taken from KGP, chapter 21 example classes.
            //It calculates the position of each particle every TIMESTEP.
            
            if(i%2==0){
                coord[i] += vels[i] * TIMESTEP + 0.5 * accs[i] * TIMESTEP * TIMESTEP; // x coord
                coord[i + 1] += vels[i + 1] * TIMESTEP + 0.5 * accs[i + 1] * TIMESTEP * TIMESTEP; // y coord
                coord[i + 2] += vels[i + 2] * TIMESTEP + 0.5 * accs[i + 2] * TIMESTEP * TIMESTEP; // z coord
            } else{
                coord[i] -= vels[i] * TIMESTEP + 0.5 * accs[i] * TIMESTEP * TIMESTEP; // x coord
                coord[i + 1] -= vels[i + 1] * TIMESTEP + 0.5 * accs[i + 1] * TIMESTEP * TIMESTEP; // y coord
                coord[i + 2] -= vels[i + 2] * TIMESTEP + 0.5 * accs[i + 2] * TIMESTEP * TIMESTEP; // z coord
            }
            // calculate new velocities for every TIMESTEP.
            vels[i] += accs[i] * TIMESTEP; // x vel
            vels[i + 1] += accs[i + 1] * TIMESTEP; // y vel
            vels[i + 2] += accs[i + 2] * TIMESTEP; // z vel
        }
        // end of updateParticles(int i)
    }
 
// taken from the example classes used in Killer Game Programming in Java
// by Andrew Davison, chapter 21.
    public class ParticlesControl extends Behavior 
    // Requests an update every timedelay in ms by using the updater object.
    // It is important in order to get real-time changes to have our delay to be 
    // equal with the TIMESTEP.
    // So if every frame will be calculate with f.x. 50 ms delay, then the new 
    // velocities and position of each particle will be
    // for a TIMESTEP of 50 ms.
    {
        private WakeupCondition timedelay;
        private PUpdater updaterr;

        public ParticlesControl(int delay, PUpdater updt) {
            timedelay = new WakeupOnElapsedTime(delay);
            updaterr = updt;
        }

        @Override
        public void initialize()
        { wakeupOn(timedelay);}

        @Override
        public void processStimulus(Enumeration criteria) {
            pointParticles.updateData(updaterr); // request an update of the geometry
            wakeupOn(timedelay);
        }
    }
}
