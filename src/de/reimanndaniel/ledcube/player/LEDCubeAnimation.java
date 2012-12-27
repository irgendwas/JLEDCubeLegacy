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
package de.reimanndaniel.ledcube.player;

import de.reimanndaniel.ledcube.system.LEDCubeState;
import java.util.LinkedList;

/**
 *  An animation that can be played with the {@code LEDCubePlayer}.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 *  @see LEDCubePlayer
 */
public class LEDCubeAnimation extends LinkedList<Object> {

    /**
     *  Creates an animation.
     *  They always have to start with a state!
     *
     *  @param state the first state of the animation
     */
    public LEDCubeAnimation( LEDCubeState state ) {
        add( state );
    }

}
