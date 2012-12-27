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

/**
 *  A LED in the cube.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubeLight extends LEDCubePoint {

    /**
     *  The color of the light.
     */
    private ColorRGBA color;

    /**
     *  Creates a LED for a cube.
     *  Uses standard color {@code ColorRGBA.Black}.
     *
     *  @param x the x coordinate of the light
     *  @param y the y coordinate of the light
     *  @param z the z coordinate of the light
     */
    public LEDCubeLight( int x, int y, int z ) {
        this( x, y, z, ColorRGBA.Black );
    }

    /**
     *  Creates a LED for a cube with a given color.
     *
     *  @param x the x coordinate of the light
     *  @param y the y coordinate of the light
     *  @param z the z coordinate of the light
     *  @param color the color of the light
     */
    public LEDCubeLight( int x, int y, int z, ColorRGBA color ) {
        super( x, y, z );
        this.color = color;
    }

    /**
     *  Creates a LED for a cube.
     *  Uses standard color {@code ColorRGBA.Black}.
     *
     *  @param led the point of the light
     */
    public LEDCubeLight( LEDCubePoint led ) {
        this( led, ColorRGBA.Black );
    }

    /**
     *  Creates a LED for a cube with a given color.
     *
     *  @param led the point of the light
     *  @param color the color of the light
     */
    public LEDCubeLight( LEDCubePoint led, ColorRGBA color ) {
        this( led.getX(), led.getY(), led.getZ(), color );
    }

    /**
     *  Copies a LED for a cube.
     *
     *  @param led the light
     */
    public LEDCubeLight( LEDCubeLight led ) {
        this( led, led.getColor() );
    }

    /**
     *  Turns the light on.
     *  Uses standard color {@code ColorRGBA.White}
     */
    public void on() {
        this.color = ColorRGBA.White;
    }

    /**
     *  Turns the light on with a special color.
     *
     *  @param color the color of the light
     */
    public void on( ColorRGBA color ) {
        this.color = color;
    }

    /**
     *  Turns the light off.
     */
    public void off() {
        this.color = ColorRGBA.Black;
    }

    /**
     *  Returns the color of the light.
     *
     *  @return the color of the light
     */
    public ColorRGBA getColor() {
        return color;
    }

}
