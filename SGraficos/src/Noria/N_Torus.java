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
package Noria;


import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class N_Torus extends Shape3D {

    // how many triangles make up the torus
    private static final int MAJOR_STEPS = 80;
    private static final int MINOR_STEPS = 70;

    // sets the torus size
    private static final float MAJOR_RADIUS = 3.0f;
    private static final float MINOR_RADIUS = 0.2f;

    // set odd-shaped torus (oval)
    private static final float ECCENTRICITY = 0.8f;


    // how to generate the torus (wheel)
    public Node Torus(float majorRadius, float minorRadius, float eccentricity,Appearance app) {

        // number of steps to generate each torus
        int majorSteps = MAJOR_STEPS;
        int minorSteps = MINOR_STEPS;

        // generate points
        Point3f[] v = new Point3f[minorSteps];
        for (int j = 0; j < minorSteps; j++) {
            float angle = (float) (2.0 * Math.PI * ((double) j) / minorSteps);
            float x = (float) (eccentricity * minorRadius * Math.cos(angle));
            float y = (float) (minorRadius * Math.sin(angle));
            v[j] = new Point3f(x, y + majorRadius, 0.0f);
        }

        // generate vertices from the points, with the proper rotations
        Point3f[][] vertices = new Point3f[majorSteps][minorSteps];
        for (int i = 0; i < majorSteps; i++) {
            float angle = (float) (2.0 * Math.PI * ((double) i) / majorSteps);
            Transform3D rotation = new Transform3D();
            AxisAngle4f axisAngle = new AxisAngle4f(1.0f, 0.0f, 0.0f, angle);
            rotation.setRotation(axisAngle);
            for (int j = 0; j < minorSteps; j++) {
                vertices[i][j] = new Point3f();
                rotation.transform(v[j], vertices[i][j]);
            }
        }

        // draw lines with the torus
        int numberOfLines = 2 * majorSteps * minorSteps;
        int code = GeometryArray.COORDINATES;
        LineArray lines = new LineArray(numberOfLines * 2, code);

        // set coordinates of the object
        TriangleArray t = new TriangleArray(6 * majorSteps * minorSteps, GeometryArray.COORDINATES);
        int k = 0;
        int index = 0;
        for (int i = 0; i < majorSteps; i++) {
            for (int j = 0; j < minorSteps; j++) {
                t.setCoordinate(index++, vertices[i][j]);
                t.setCoordinate(index++, vertices[i][(j + 1) % minorSteps]);
                t.setCoordinate(index++, vertices[(i + 1) % majorSteps][j]);
                t.setCoordinate(index++, vertices[i][(j + 1) % minorSteps]);
                t.setCoordinate(index++, vertices[(i + 1) % majorSteps][(j + 1) % minorSteps]);
                t.setCoordinate(index++, vertices[(i + 1) % majorSteps][j]);
            }
        }

        // set up geometry info
        GeometryInfo geometryInfo = new GeometryInfo(t);
        NormalGenerator normalGenerator = new NormalGenerator();
        normalGenerator.generateNormals(geometryInfo);
        Stripifier stripifier = new Stripifier();
        stripifier.stripify(geometryInfo);


        return new Shape3D(geometryInfo.getGeometryArray(),app);
    }

    // created object, empty
    public N_Torus() {
    }

  // set up graphics canvas
    // calculate an average off of t
    private float avg(float a, float b, float t) {
        return (1 - t) * a + t * b;
    }

    // generate an average point (vector) from two points
    private Point3f avg(Point3f a, Point3f b, float t) {
        float x = avg(a.x, b.x, t);
        float y = avg(a.y, b.y, t);
        float z = avg(a.z, b.z, t);
        return new Point3f(x, y, z);
    }

    // generate an average point (vector) from three points
    private Point3f avg(Point3f a, Point3f b, Point3f c, float t) {
        return avg(avg(a, b, t), avg(b, c, t), t);
    }

    // generate an average point (vector) from four points
    private Point3f avg(Point3f a, Point3f b, Point3f c, Point3f d, float t) {
        return avg(avg(a, b, c, t), avg(b, c, d, t), t);
    }

    public BranchGroup getSceneGraph() {

        // create the root of the branch graph
        BranchGroup objRoot = new BranchGroup();
        //SharedGroup objShare = new SharedGroup();
        objRoot.addChild(Torus(MAJOR_RADIUS, MINOR_RADIUS, ECCENTRICITY,new Appearance()));

        return objRoot;
    }

}
