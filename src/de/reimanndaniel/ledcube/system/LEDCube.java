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

import com.jme3.math.ColorRGBA;
import java.util.Observable;

/**
 *  The LED cube.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCube extends Observable {

    /**
     *  The size of the cube.
     */
    protected final LEDCubeDimension dim;
    /**
     *  The LEDs of the cube.
     */
    protected LEDCubeState leds;

    /**
     *  Constructor with the dimension of the cube.
     *  Uses standard color {@code ColorRGBA.Black}.
     *
     *  @param dim the dimension of the cube
     */
    public LEDCube( LEDCubeDimension dim ) {
        this( dim, ColorRGBA.Black );
    }

    /**
     *  Constructor with the dimension of the cube and a special initial color.
     *
     *  @param dim the dimension of the cube
     *  @param initcolor the initial color
     */
    public LEDCube( LEDCubeDimension dim, ColorRGBA initcolor ) {
        this.dim = dim;
        this.leds = new LEDCubeState( dim );
        fill( initcolor );
    }

    /**
     *  Returns the dimension of the cube.
     *
     *  @return the dimension of the cube
     */
    public LEDCubeDimension getDimension() {
        return dim;
    }

    /**
     *  Switches on the LED at the point.
     *
     *  @param led position of the LED
     */
    public void switchOn( LEDCubePoint led ) {
        setColor( led, ColorRGBA.White );
    }

    /**
     *  Switches off the LED at the point.
     *
     *  @param led position of the LED
     */
    public void switchOff( LEDCubePoint led ) {
        setColor( led, ColorRGBA.Black );
    }

    /**
     *  Sets a LED in the cube.
     *
     *  @param led the light of the LED
     */
    public void setLight( LEDCubeLight led ) {
        setColor( led, led.getColor() );
    }

    /**
     *  Returns the LED.
     *
     *  @param led point of the LED
     *  @return the LED
     */
    protected LEDCubeLight getLight( LEDCubePoint led ) {
        return new LEDCubeLight( led, getColor( led ) );
    }

    /**
     *  Sets the color of a LED in the cube at a certain point.
     *
     *  @param led position of the LED
     *  @param color color of the LED
     */
    public void setColor( LEDCubePoint led, ColorRGBA color ) {
        if( !dim.in( led ) ) {
            throw new IllegalArgumentException( "The LED is out of range." );
        }
        leds.set( led.getX(), led.getY(), led.getZ(), color );
        setChanged();
        notifyObservers( getLight( led ) );
    }

    /**
     *  Gets the color of a LED in the cube at a certain point.
     *
     *  @param led position of the LED
     *  @return color color of the LED
     */
    public ColorRGBA getColor( LEDCubePoint led ) {
        if( !dim.in( led ) ) {
            throw new IllegalArgumentException( "The LED is out of range." );
        }
        return leds.get( led.getX(), led.getY(), led.getZ() );
    }

    /**
     *  Fills the whole LED cube with a color.
     *
     *  @param color the color to fill the LED cube with
     */
    public final void fill( ColorRGBA color ) {
        for( int ax = 0; ax < dim.getWidth(); ax++ ) {
            for( int ay = 0; ay < dim.getHeight(); ay++ ) {
                for( int az = 0; az < dim.getDepth(); az++ ) {
                    leds.set( ax, ay, az, color );
                }
            }
        }
        setChanged();
        notifyObservers();
    }

}
