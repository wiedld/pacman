/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiedld.game;

/**
 *
 * @author wiedld
 */
public class Pacman extends GamePiece implements Metabolism {

    public Pacman(Surface.Name surface) {
        super(surface, Game.surfaceDiameter, Game.surfaceDiameter);
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void munchPacDots() {
        throw new UnsupportedOperationException();
    }

}
