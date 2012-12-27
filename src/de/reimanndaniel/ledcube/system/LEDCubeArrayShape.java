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

import java.util.ArrayList;

/**
 *  A shape of something in the cube as list of points.
 *  Uses ArrayList as list implementation.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubeArrayShape extends ArrayList<LEDCubePoint> implements LEDCubeShape {

    /**
     *  Creates a new array shape.
     */
    public LEDCubeArrayShape() {
        super();
    }

    /**
     *  Creates a new array shape with an initial capacity.
     *
     *  @param startCapacity the initial capacity of the shape
     */
    public LEDCubeArrayShape( int startCapacity ) {
        super( startCapacity );
    }

}
