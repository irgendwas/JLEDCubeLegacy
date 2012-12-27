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
 *  A momentary state of a LED cube.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubeState {

    /**
     *  The dimension of the state.
     */
    private LEDCubeDimension dim;
    /**
     *  The colors of the state.
     */
    private ColorRGBA[][][] state;

    /**
     *  Creates a state.
     *  Uses standard color {@code ColorRGBA.Black}.
     *
     *  @param dim the dimension of the state
     */
    public LEDCubeState( LEDCubeDimension dim ) {
        this( dim, ColorRGBA.Black );
    }

    /**
     *  Creates a state.
     *
     *  @param dim the dimension of the state
     *  @param color the color of the state
     */
    public LEDCubeState( LEDCubeDimension dim, ColorRGBA color ) {
        this.dim = dim;
        state = new ColorRGBA[dim.getWidth()][dim.getHeight()][dim.getDepth()];
        for( int ax = 0; ax < dim.getWidth(); ax++ ) {
            for( int ay = 0; ay < dim.getHeight(); ay++ ) {
                for( int az = 0; az < dim.getDepth(); az++ ) {
                    state[ax][ay][az] = color;
                }
            }
        }
    }

    /**
     *  Returns the dimension of the state.
     *
     *  @return the dimension of the state
     */
    public LEDCubeDimension getDimension() {
        return dim;
    }

    /**
     *  Sets the color of the LED at a certain point.
     *
     *  @param x the x coordinate of the LED
     *  @param y the y coordinate of the LED
     *  @param z the z coordinate of the LED
     *  @param color the color of the LED
     */
    public void set( int x, int y, int z, ColorRGBA color ) {
        state[x][y][z] = color;
    }

    /**
     *  Returns the color of the LED at a certain point.
     *
     *  @param x the x coordinate of the LED
     *  @param y the y coordinate of the LED
     *  @param z the z coordinate of the LED
     *  @return the color of the LED
     */
    public ColorRGBA get( int x, int y, int z ) {
        return state[x][y][z];
    }

}
