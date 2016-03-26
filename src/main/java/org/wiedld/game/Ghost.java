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
public class Ghost extends GamePiece implements Metabolism {

    Boolean eatable;
    private String name;
    private Color color;

    public static enum Color {

        Red, Pink, Green, Yellow,;
    }

    public Ghost(Surface.Name surface, Color color, String name) {
        super(surface, Game.surfaceDiameter, Game.surfaceDiameter / 2);
        this.color = color;
        this.name = name;
        this.eatable = false;
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void toggleEatable() {
        this.eatable = !this.eatable;
    }
}
