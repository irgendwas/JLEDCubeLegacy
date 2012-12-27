/*
 *  Copyright 2012, 2013 Daniel Reimann
 *
 *  This file is part of JLEDCube.
 *
 *  JLEDCube is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  JLEDCube is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with JLEDCube.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package de.reimanndaniel.ledcube.system;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 *  The view and 3D node of the LED cube.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubeViewer extends Node implements Observer {

    /**
     *  The LED cube to view.
     */
    protected final LEDCube cube;
    /**
     *  The 3D view of each LED.
     */
    protected Geometry view[][][];
    /**
     *  Set after first paint().
     */
    protected boolean init;
    /**
     *  AssetManager to use for the material.
     *  Should have "Common/MatDefs/Misc/Unshaded.j3md".
     */
    protected AssetManager assetman;
    /**
     *  The distance between LEDs.
     */
    protected final float[] distbetled = { 25f, 25f, 25f };
    /**
     *  A material cache.
     */
    protected HashMap<ColorRGBA,Material> colmap;

    /**
     *  Creates a viewer for a LED cube.
     *
     *  @param cube the cube to view
     *  @param assetman the asset manager of the LED cube
     */
    public LEDCubeViewer( LEDCube cube, AssetManager assetman ) {
        this.cube = cube;
        this.assetman = assetman;
        LEDCubeDimension dim = cube.getDimension();
        this.view = new Geometry[dim.getWidth()][dim.getHeight()][dim.getDepth()];
        this.colmap = new HashMap<ColorRGBA, Material>();
    }

    /**
     *  @return the LED cube the viewer is showing
     */
    public LEDCube getLEDCube() {
        return cube;
    }

    /**
     *  Paints the LED cube.
     */
    public void paint() {
        if( init == true ) {
            repaint();
            return;
        }
        cube.addObserver( this );
        // calc
        LEDCubeDimension dim = cube.getDimension();
        float totalx = ( dim.getWidth() - 1 ) * distbetled[0];
        float totaly = ( dim.getHeight() - 1 ) * distbetled[1];
        float totalz = ( dim.getDepth() - 1 ) * distbetled[2];
        // to make the cube central
        float rx = totalx / 2 * -1;
        float ry = totaly / 2 * -1;
        float rz = totalz / 2 * -1;

        // led objects
        Sphere ledshape = new Sphere( 64, 64, 2f );
        for( int ax = 0; ax < dim.getWidth(); ax++ ) {
            for( int ay = 0; ay < dim.getHeight(); ay++ ) {
                for( int az = 0; az < dim.getDepth(); az++ ) {
                    Geometry geo = new Geometry( "LED", ledshape );
                    geo.setLocalTranslation( rx, ry, rz );
                    geo.setMaterial( getColorMat( cube.getColor( new LEDCubePoint( ax, ay, az ) ) ) );
                    view[ax][ay][az] = geo;
                    this.attachChild( view[ax][ay][az] );
                    rz += distbetled[2];
                }
                rz = totalz / 2 * -1;
                ry += distbetled[1];
            }
            ry = totaly / 2 * -1;
            rx += distbetled[0];
        }
        init = true;
    }

    /**
     *  Changes the LED cube.
     */
    public void repaint() {
        if( init == false ) {
            paint();
            return;
        }
        LEDCubeDimension dim = cube.getDimension();
        for( int ax = 0; ax < dim.getWidth(); ax++ ) {
            for( int ay = 0; ay < dim.getHeight(); ay++ ) {
                for( int az = 0; az < dim.getDepth(); az++ ) {
                    Geometry geo = view[ax][ay][az];
                    geo.setMaterial( getColorMat( cube.getColor( new LEDCubePoint( ax, ay, az ) ) ) );
                }
            }
        }
    }

    /**
     *  Get updates from the LED cube.
     *
     *  @param o the cube
     *  @param arg a change to the cube
     */
    @Override
    public void update( Observable o, Object arg ) {
        if( o == cube ) {
            if( arg instanceof LEDCubeLight ) {
                LEDCubeLight light = (LEDCubeLight) arg;
                Geometry geo = view[light.getX()][light.getY()][light.getZ()];
                geo.setMaterial( getColorMat( light.getColor() ) );
            }
            else if( arg == null ) {
                repaint();
            }
        }
    }

    /**
     *  Gets a material in a color.
     *  Uses caching to save space.
     *  Currently caches all colors until end of execution.
     *
     *  @param color the color of the material
     *  @return the material in the color
     */
    protected Material getColorMat( ColorRGBA color ) {
        if( colmap.containsKey( color ) ) {
            return colmap.get( color );
        }
        else {
            Material mat = new Material( assetman, "Common/MatDefs/Misc/Unshaded.j3md" );
            mat.setColor( "Color", color );
            mat.setColor( "GlowColor", color );
            colmap.put( color, mat );
            return mat;
        }
    }

}
