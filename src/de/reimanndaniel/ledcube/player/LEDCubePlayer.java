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

import de.reimanndaniel.ledcube.system.LEDCube;
import de.reimanndaniel.ledcube.system.LEDCubeDimension;
import de.reimanndaniel.ledcube.system.LEDCubeLight;
import de.reimanndaniel.ledcube.system.LEDCubePoint;
import de.reimanndaniel.ledcube.system.LEDCubeState;
import java.util.Iterator;

/**
 *  This can control the cube in better and more abstract ways.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubePlayer {

    /**
     *  The cube to play with.
     */
    protected LEDCube cube;
    /**
     *  The currently playing animation.
     */
    protected LEDCubeAnimation anime;
    /**
     *  The current position in the animation.
     */
    protected Iterator pos;
    /**
     *  Pauses any running animation.
     */
    private boolean pause;
    /**
     *  Repeats the animation.
     */
    private boolean repeat;

    /**
     *  Create a player with a cube.
     *
     *  @param cube the cube to play with
     */
    public LEDCubePlayer( LEDCube cube ) {
        this.cube = cube;
        pause = false;
    }

    /**
     *  Shows a state with the cube.
     *
     *  @param state the state
     *  @return whether it could show the state
     */
    public boolean show( LEDCubeState state ) {
        LEDCubeDimension dim = state.getDimension();
        if( cube.getDimension().equals( dim ) ) {
            for( int ai = 0; ai < dim.getWidth(); ai++ ) {
                for( int bi = 0; bi < dim.getHeight(); bi++ ) {
                    for( int ci = 0; ci < dim.getDepth(); ci++ ) {
                        cube.setColor( new LEDCubePoint( ai, bi, ci ), state.get( ai, bi, ci ) );
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     *  Applies a change to the cube.
     *
     *  @param change the change
     *  @return whether it could apply the change
     */
    public boolean apply( LEDCubeLight change ) {
        if( cube.getDimension().in( change ) ) {
            cube.setLight( change );
            return true;
        }
        return false;
    }

    /**
     *  Plays an animation on the cube.
     *  Automatically repeats the animation.
     *
     *  @param anime the animation
     */
    public void play( LEDCubeAnimation anime ) {
        play( anime, true );
    }

    /**
     *  Plays a animation on the cube.
     *
     *  @param anime the animation
     *  @param repeat whether to repat the animation
     */
    public void play( LEDCubeAnimation anime, boolean repeat ) {
        this.anime = anime;
        this.repeat = repeat;
        pos = anime.iterator();
        resume();
    }

    /**
     *  Pauses the animation.
     */
    public void pause() {
        setPause( true );
    }

    /**
     *  Resumes the animation.
     */
    public void resume() {
        setPause( false );
    }

    /**
     *  Toggles the pause status of the player.
     */
    public void togglePause() {
        setPause( !pause );
    }

    /**
     *  Sets the value of the pause status of the player.
     *
     *  @param pause the new pause status
     */
    public void setPause( boolean pause ) {
        this.pause = pause;
    }

    /**
     *  @return if the player is paused.
     */
    public boolean getPause() {
        return pause;
    }

    /**
     *  Update the LED cube according to the animation.
     *
     *  @param tpf time difference to last update
     */
    public void update( float tpf ) {
        if( !pause && anime != null ) {
            if( !pos.hasNext() ) {
                if( repeat ) { pos = anime.iterator(); }
                else { return; }
            }
            Object next = pos.next();
            if( next instanceof LEDCubeState ) {
                show( (LEDCubeState) next );
            }
            else if( next instanceof LEDCubeLight ) {
                apply( (LEDCubeLight) next );
            }
        }
    }

}
