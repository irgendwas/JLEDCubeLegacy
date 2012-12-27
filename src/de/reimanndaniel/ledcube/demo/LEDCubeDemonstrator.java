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
package de.reimanndaniel.ledcube.demo;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.system.AppSettings;
import de.reimanndaniel.ledcube.player.LEDCubeAnimation;
import de.reimanndaniel.ledcube.player.LEDCubePlayer;
import de.reimanndaniel.ledcube.system.LEDCube;
import de.reimanndaniel.ledcube.system.LEDCubeDimension;
import de.reimanndaniel.ledcube.system.LEDCubeLight;
import de.reimanndaniel.ledcube.system.LEDCubePoint;
import de.reimanndaniel.ledcube.system.LEDCubeShape;
import de.reimanndaniel.ledcube.system.LEDCubeState;
import de.reimanndaniel.ledcube.system.LEDCubeViewer;
import de.reimanndaniel.ledcube.util.LEDCubeShaper;

/**
 *  The example for a working LED cube.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubeDemonstrator extends SimpleApplication implements ActionListener {

    /**
     *  Stops the demonstation.
     */
    private boolean stop = false;
    /**
     *  The player to use.
     */
    private LEDCubePlayer player;

    /**
     *  Starts the example.
     *
     *  @param args shell arguments
     */
    public static void main( String[] args ) {
        LEDCubeDemonstrator demo = new LEDCubeDemonstrator();
        AppSettings settings = new AppSettings( true );
        settings.setTitle( "JLEDCube Demo" );
        settings.setResolution( 800, 600 );
        demo.setSettings( settings );
        demo.setShowSettings( false );
        demo.start();
    }

    /**
     *  Initiates the demonstration.
     */
    @Override
    public void simpleInitApp() {
        // view
        LEDCubeDimension dim = new LEDCubeDimension( 8 );
        LEDCube cube = new LEDCube( dim, ColorRGBA.Green );
        LEDCubeViewer viewer = new LEDCubeViewer( cube, assetManager );
        viewer.paint();

        // post processor bloom
        FilterPostProcessor fpp = new FilterPostProcessor( assetManager );
        BloomFilter bloom = new BloomFilter( BloomFilter.GlowMode.Objects );
        fpp.addFilter( bloom );
        viewPort.addProcessor( fpp );

        // for the variable view
        flyCam.setEnabled( false );
        LEDCubeCamera chaser = new LEDCubeCamera( cam, viewer );
        chaser.registerWithSpecialInput( inputManager );
        inputManager.setCursorVisible( false );

        // pause function
        inputManager.addMapping( "DemoPause", new KeyTrigger( KeyInput.KEY_P ), new KeyTrigger( KeyInput.KEY_PAUSE ) );
        inputManager.addListener( this, new String[] { "DemoPause" } );

        // make it visible
        rootNode.attachChild( viewer );

        // animation
        LEDCubeAnimation anime = new LEDCubeAnimation( new LEDCubeState( dim ) );
        LEDCubeShaper shaper = new LEDCubeShaper( dim );
        LEDCubeShape points = shaper.fullCube();
        for( LEDCubePoint point: points ) {
            anime.add( new LEDCubeLight( point, ColorRGBA.Blue ) );
        }
        for( LEDCubePoint point: points ) {
            anime.add( new LEDCubeLight( point, ColorRGBA.Black ) );
        }

        // player
        player = new LEDCubePlayer( cube );
        player.play( anime );
    }

    /**
     *  Updates the demonstration state.
     *
     *  @param tpf time difference to last update
     */
    @Override
    public void simpleUpdate( float tpf ) {
        super.simpleUpdate( tpf );
        if( stop ) {
            this.stop();
        }
        player.update( tpf );
    }

    @Override
    public void onAction( String name, boolean keyPressed, float tpf ) {
        if( name.equals( "DemoPause" ) ) {
            player.togglePause();
        }
    }

}
