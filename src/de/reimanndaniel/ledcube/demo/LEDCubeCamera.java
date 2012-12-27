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

import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.renderer.Camera;
import de.reimanndaniel.ledcube.system.LEDCubeDimension;
import de.reimanndaniel.ledcube.system.LEDCubeViewer;

/**
 *  A circling camera for the LED cube.
 *
 *  @author Daniel Reimann <coding@reimanndaniel.de>
 *  @version 0.9.0
 *  @since 0.9.0
 */
public class LEDCubeCamera extends ChaseCamera {

    /**
     *  Constructs the LED cube camera.
     *
     *  @param cam the camera to use
     *  @param target the viewer to look at
     */
    public LEDCubeCamera( Camera cam, final LEDCubeViewer target ) {
        super( cam, target );
        LEDCubeDimension dim = target.getLEDCube().getDimension();
        float max = Math.max( Math.max( dim.getWidth(), dim.getHeight() ), dim.getDepth() ) * 25f;
        setDefaultDistance( max * 2f );
        setMinDistance( max );
        setMaxDistance( max * 4f );
    }

    /**
     *  Registers the inputs for the LED cube camera controls.
     *
     *  @param inputManager the manager of the application to register inputs
     */
    public void registerWithSpecialInput( InputManager inputManager ) {
        String[] inputs = {
            "ChaseCamToggleRotate",
            "ChaseCamDown",
            "ChaseCamUp",
            "ChaseCamMoveLeft",
            "ChaseCamMoveRight",
            "ChaseCamZoomIn",
            "ChaseCamZoomOut"
        };

        this.inputManager = inputManager;
        // look out for concurrent access to invertYaxis & invertXaxis
        inputManager.addMapping( "ChaseCamDown", new MouseAxisTrigger( MouseInput.AXIS_Y, !invertYaxis ), new KeyTrigger( KeyInput.KEY_S ) );
        inputManager.addMapping( "ChaseCamUp", new MouseAxisTrigger( MouseInput.AXIS_Y, invertYaxis ), new KeyTrigger( KeyInput.KEY_W ) );
        inputManager.addMapping( "ChaseCamMoveLeft", new MouseAxisTrigger( MouseInput.AXIS_X, !invertXaxis ), new KeyTrigger( KeyInput.KEY_A ) );
        inputManager.addMapping( "ChaseCamMoveRight", new MouseAxisTrigger( MouseInput.AXIS_X, invertXaxis ), new KeyTrigger( KeyInput.KEY_D ) );
        inputManager.addMapping( "ChaseCamZoomIn", new MouseAxisTrigger( MouseInput.AXIS_WHEEL, false ), new KeyTrigger( KeyInput.KEY_Q ) );
        inputManager.addMapping( "ChaseCamZoomOut", new MouseAxisTrigger( MouseInput.AXIS_WHEEL, true ), new KeyTrigger( KeyInput.KEY_E ) );
        inputManager.addMapping(
            "ChaseCamToggleRotate",
            new MouseButtonTrigger( MouseInput.BUTTON_LEFT ),
            new MouseButtonTrigger( MouseInput.BUTTON_RIGHT ),
            new KeyTrigger( KeyInput.KEY_R )
        );
        inputManager.addListener( this, inputs );
    }

    @Override
    public void onAction( String name, boolean keyPressed, float tpf ) {
        if( dragToRotate ) {
            if( enabled && name.equals( ChaseCamToggleRotate ) ) {
                if( keyPressed ) {
                    canRotate = true;
                }
                else {
                    canRotate = false;
                }
            }
        }

    }

}
