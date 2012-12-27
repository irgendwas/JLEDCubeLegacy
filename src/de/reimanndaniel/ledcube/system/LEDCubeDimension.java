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

/**
 *  Determines the size of the cube.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubeDimension {

    /**
     *  Enum like value for the x axis.
     */
    public static final int AxisX = 1;
    /**
     *  Enum like value for the y axis.
     */
    public static final int AxisY = 2;
    /**
     *  Enum like value for the z axis.
     */
    public static final int AxisZ = 4;

    /**
     *  The width of the dimension.
     */
    private final int width;
    /**
     *  The height of the dimension.
     */
    private final int height;
    /**
     *  The depth of the dimension.
     */
    private final int depth;

    /**
     *  Creates a 3 dimensional real cubic size of a LED cube.
     *
     *  @param cube the length of all sides of the dimension.
     */
    public LEDCubeDimension( int cube ) {
        this( cube, cube, cube );
    }

    /**
     *  Creates a 3 dimensional size of a LED cube.
     *
     *  @param width the width of the dimension.
     *  @param height the height of the dimension.
     *  @param depth the depth of the dimension.
     */
    public LEDCubeDimension( int width, int height, int depth ) {
        if( width >= 1 && height >= 1 && depth >= 1 ) {
            this.width = width;
            this.height = height;
            this.depth = depth;
        }
        else {
            throw new IllegalArgumentException( "Each value has to be >= 1." );
        }
    }

    /**
     *  @return the width of the dimension
     */
    public int getWidth() {
        return width;
    }

    /**
     *  @return the height of the dimension
     */
    public int getHeight() {
        return height;
    }

    /**
     *  @return the depth of the dimension
     */
    public int getDepth() {
        return depth;
    }

    /**
     *  Tests if the point is in the dimension.
     *
     *  @param point the point to test
     *  @return whether the point is in the dimension
     */
    public boolean in( LEDCubePoint point ) {
        if(
            point.getX() < getWidth() &&
            point.getX() >= 0 &&
            point.getY() < getHeight() &&
            point.getY() >= 0 &&
            point.getZ() < getDepth() &&
            point.getZ() >= 0
        ) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.width;
        hash = 23 * hash + this.height;
        hash = 23 * hash + this.depth;
        return hash;
    }

    @Override
    public boolean equals( Object o ) {
        if( o instanceof LEDCubeDimension ) {
            LEDCubeDimension dim = (LEDCubeDimension) o;
            if(
                this.width == dim.width &&
                this.height == dim.height &&
                this.depth == dim.depth
            ) {
                return true;
            }
        }
        return false;
    }

}
