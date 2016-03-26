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
class Surface {
    
    public static enum Name {
        
        A {
                    
                    @Override
                    public void transitionUp(GamePiece gp) {
                        transitionFlipY(gp);
                        gp.surface = Surface.Name.B;
                    }
                    
                    @Override
                    public void transitionDown(GamePiece gp) {
                        transitionFlipY(gp);
                        gp.surface = Surface.Name.D;
                    }
                    
                    @Override
                    public void transitionLeft(GamePiece gp) {
                        transitionFlipX(gp);
                        gp.surface = Surface.Name.E;
                    }
                    
                    @Override
                    public void transitionRight(GamePiece gp) {
                        transitionFlipX(gp);
                        gp.surface = Surface.Name.F;
                    }
                    
                }, B {
                    
                    @Override
                    public void transitionUp(GamePiece gp) {
                        transitionFlipY(gp);
                        gp.surface = Surface.Name.C;
                    }
                    
                    @Override
                    public void transitionDown(GamePiece gp) {
                        transitionFlipY(gp);
                        gp.surface = Surface.Name.A;
                    }
                    
                    @Override
                    public void transitionLeft(GamePiece gp) {
                        transitionRotateLeft(gp);
                        gp.surface = Surface.Name.E;
                    }
                    
                    @Override
                    public void transitionRight(GamePiece gp) {
                        transitionRotateRight(gp);
                        gp.surface = Surface.Name.F;
                    }
                }, C {
                    
                    @Override
                    public void transitionUp(GamePiece gp) {
                        transitionFlipY(gp);
                        gp.surface = Surface.Name.D;
                    }
                    
                    @Override
                    public void transitionDown(GamePiece gp) {
                        transitionFlipY(gp);
                        gp.surface = Surface.Name.B;
                    }
                    
                    @Override
                    public void transitionLeft(GamePiece gp) {
                        transitionInversePlaneFlipY(gp);
                        gp.surface = Surface.Name.E;
                    }
                    
                    @Override
                    public void transitionRight(GamePiece gp) {
                        transitionInversePlaneFlipY(gp);
                        gp.surface = Surface.Name.F;
                    }
                }, D {
                    
                    @Override
                    public void transitionUp(GamePiece gp) {
                        transitionFlipY(gp);
                        gp.surface = Surface.Name.A;
                    }
                    
                    @Override
                    public void transitionDown(GamePiece gp) {
                        transitionFlipY(gp);
                        gp.surface = Surface.Name.C;
                    }
                    
                    @Override
                    public void transitionLeft(GamePiece gp) {
                        transitionRotateRight(gp);
                        gp.surface = Surface.Name.E;
                    }
                    
                    @Override
                    public void transitionRight(GamePiece gp) {
                        transitionRotateLeft(gp);
                        gp.surface = Surface.Name.F;
                    }
                }, E {
                    
                    @Override
                    public void transitionUp(GamePiece gp) {
                        transitionRotateRight(gp);
                        gp.surface = Surface.Name.B;
                    }
                    
                    @Override
                    public void transitionDown(GamePiece gp) {
                        transitionRotateLeft(gp);
                        gp.surface = Surface.Name.D;
                    }
                    
                    @Override
                    public void transitionLeft(GamePiece gp) {
                        transitionInversePlaneFlipY(gp);
                        gp.surface = Surface.Name.C;
                    }
                    
                    @Override
                    public void transitionRight(GamePiece gp) {
                        transitionFlipX(gp);
                        gp.surface = Surface.Name.A;
                    }
                }, F {
                    
                    @Override
                    public void transitionUp(GamePiece gp) {
                        transitionRotateLeft(gp);
                        gp.surface = Surface.Name.B;
                    }
                    
                    @Override
                    public void transitionDown(GamePiece gp) {
                        transitionRotateRight(gp);
                        gp.surface = Surface.Name.D;
                    }
                    
                    @Override
                    public void transitionLeft(GamePiece gp) {
                        transitionFlipX(gp);
                        gp.surface = Surface.Name.A;
                    }
                    
                    @Override
                    public void transitionRight(GamePiece gp) {
                        transitionInversePlaneFlipY(gp);
                        gp.surface = Surface.Name.C;
                    }
                },;
        
        public abstract void transitionUp(GamePiece gp);
        
        public abstract void transitionDown(GamePiece gp);
        
        public abstract void transitionLeft(GamePiece gp);
        
        public abstract void transitionRight(GamePiece gp);
    }
    
    class SurfaceImpl extends Surface {
        
        final Name surfaceType;
        
        public SurfaceImpl(Name name) {
            this.surfaceType = name;
        }
        
    }

    /**
     * sides have an inverse y plane x(0) -> x(n) not inverted y(0) inverted |
     * y(n)
     */
    // A <-> B, B <-> C, C <-> D, D <-> A
    private static void transitionFlipY(GamePiece gp) {
        gp.y = Game.surfaceDiameter - gp.y;
    }

    // A <-> F, F <-> A
    private static void transitionFlipX(GamePiece gp) {
        gp.x = Game.surfaceDiameter - gp.x;
    }

    // when 2 of the same kind meet. (e.g. a left side and a left side)
    // inverse the direction (was heading left, now is heading relative to new surface)
    private static void transitionInversePlane(GamePiece gp) {
        gp.turnLeft();
        gp.turnLeft();
    }

    // F <-> C <-> E.   transitionInversePlane, transitionFlipY
    private static void transitionInversePlaneFlipY(GamePiece gp) {
        transitionInversePlane(gp);
        transitionFlipY(gp);
    }

    // B -> F.   x = -y.  y = x  dir = down (right +1)
    // E -> B    x = -y.  y = x   dir = right (up + 1)
    // D -> E    x = -y.  y = x   dir = up (left +1)
    // F -> D
    private static void transitionRotateRight(GamePiece gp) {
        int temp = gp.x;
        gp.x = -1 * gp.y;
        gp.y = temp;
        gp.turnRight();
    }

    // F -> B    y = -x.  x = y   dir = left (up -1)
    // B -> E    y = -x.  x = y   dir = down (left -1)
    // E -> D    y = -x.  x = y   dir = right (down -1)
    // D -> F    y = -x.  x = y   dir = up (right -1)
    private static void transitionRotateLeft(GamePiece gp) {
        int temp = gp.y;
        gp.y = -1 * gp.x;
        gp.x = temp;
        gp.turnLeft();
    }
}
