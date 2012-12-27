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

import de.reimanndaniel.ledcube.system.LEDCubeDimension;
import de.reimanndaniel.ledcube.system.LEDCubePoint;
import java.util.Random;

/**
 *  A factory to cache points and do other useful stuff.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubePointFactory {

    /**
     *  Gets a random point within a dimension.
     *
     *  @param dim the borders of the random field to choose a point
     *  @return a random point
     */
    public static LEDCubePoint random( LEDCubeDimension dim ) {
        return random( dim, 0 );
    }

    /**
     *  Gets a random point within a dimension with offset to the border.
     *
     *  @param dim the borders of the random field to choose a point
     *  @param offset minimal dinstance to the border of the dimension
     *  @return a random point
     */
    public static LEDCubePoint random( LEDCubeDimension dim, int offset ) {
        if(
            dim.getWidth() > offset * 2 &&
            dim.getHeight() > offset * 2 &&
            dim.getDepth() > offset * 2
        )
        {
            Random rand = new Random();
            int x = rand.nextInt( dim.getWidth() - 2 * offset ) + offset;
            int y = rand.nextInt( dim.getHeight() - 2 * offset ) + offset;
            int z = rand.nextInt( dim.getDepth() - 2 * offset ) + offset;
            return new LEDCubePoint( x, y, z );
        }
        else {
            throw new IllegalArgumentException( "The dimension has to be bigger than twice the offset on each side." );
        }
    }

}
