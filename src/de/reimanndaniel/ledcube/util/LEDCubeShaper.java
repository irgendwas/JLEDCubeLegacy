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
package de.reimanndaniel.ledcube.util;

import de.reimanndaniel.ledcube.system.LEDCubeArrayShape;
import de.reimanndaniel.ledcube.system.LEDCubeDimension;
import de.reimanndaniel.ledcube.system.LEDCubePoint;
import de.reimanndaniel.ledcube.system.LEDCubeShape;

/**
 *  This creates often used shapes.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 *  @see LEDCubeShape
 */
public class LEDCubeShaper {

    /**
     *  The dimension to use.
     */
    protected final LEDCubeDimension dim;

    /**
     *  Creates a shaper from a dimension.
     *
     *  @param dim the dimension to use
     */
    public LEDCubeShaper( LEDCubeDimension dim ) {
        this.dim = dim;
    }

    /**
     *  Returns the dimension that is used.
     *
     *  @return the dimension that is used
     */
    public LEDCubeDimension getDimension() {
        return dim;
    }

    /**
     *  @return a shape of the full cube
     */
    public LEDCubeShape fullCube() {
        int size = dim.getWidth() * dim.getHeight() * dim.getDepth();
        LEDCubeArrayShape shape = new LEDCubeArrayShape( size );
        for( int ai = 0; ai < dim.getWidth(); ai++ ) {
            for( int bi = 0; bi < dim.getHeight(); bi++ ) {
                for( int ci = 0; ci < dim.getDepth(); ci++ ) {
                    shape.add( new LEDCubePoint( ai, bi, ci ) );
                }
            }
        }
        return shape;
    }

}
