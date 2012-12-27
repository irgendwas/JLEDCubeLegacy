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
 *  A point in the cube.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubePoint {

    /**
     *  The x coordinate of the point.
     */
    private final int x;
    /**
     *  The y coordinate of the point.
     */
    private final int y;
    /**
     *  The z coordinate of the point.
     */
    private final int z;

    /**
     *  Creates a point to adress a LED in the cube.
     *
     *  @param x the x coordinate of the point
     *  @param y the y coordinate of the point
     *  @param z the z coordinate of the point
     */
    public LEDCubePoint( int x, int y, int z ) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *  Copies a point.
     *
     *  @param copy the point to copy
     */
    public LEDCubePoint( LEDCubePoint copy ) {
        this.x = copy.getX();
        this.y = copy.getY();
        this.z = copy.getZ();
    }

    /**
     *  @return the x coordinate of the point
     */
    public final int getX() {
        return x;
    }

    /**
     *  @return the y coordinate of the point
     */
    public final int getY() {
        return y;
    }

    /**
     *  @return the z coordinate of the point
     */
    public final int getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.x;
        hash = 13 * hash + this.y;
        hash = 13 * hash + this.z;
        return hash;
    }

    @Override
    public boolean equals( Object o ) {
        if( o instanceof LEDCubePoint ) {
            LEDCubePoint p = (LEDCubePoint) o;
            if(
                this.getX() == p.getX() &&
                this.getY() == p.getY() &&
                this.getZ() == p.getZ()
            ) {
                return true;
            }
        }
        return false;
    }

}
