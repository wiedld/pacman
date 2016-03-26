/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiedld.game;

//import java.annotatation.Nonnull;
/**
 *
 * @author wiedld
 */
class GamePiece {

    Direction direction;
    int x;
    int y;
    Surface.Name surface;

    public static enum Direction {

        Up(0), Right(1), Down(2), Left(3),;
        private final int num;

        private Direction(int num) {
            this.num = num;
        }

        public int getDirection() {
            return this.num;
        }
    }

    public GamePiece(Surface.Name surface, int x, int y) {
        this.surface = surface;
        this.x = x;
        this.y = y;
        this.direction = Direction.Down;
    }

    public Direction setDirection(int num) {
        switch (num) {
            case 0:
                return Direction.Up;
            case 1:
                return Direction.Right;
            case 2:
                return Direction.Down;
            case 3:
                return Direction.Left;
        }
        return null;
    }

    public void turnLeft() {
        this.direction = setDirection((this.direction.getDirection() - 1) == -1 ? 3 : (this.direction.getDirection() + 1));
    }

    public void turnRight() {
        this.direction = setDirection((this.direction.getDirection() + 1) == 4 ? 0 : (this.direction.getDirection() + 1));
    }

    private void move() {
        switch (this.direction) {
            case Up:
                if (this.y == 0) {
                    this.surface.transitionUp(this);
                } else {
                    this.y--;
                }
                break;
            case Down:
                if (this.y == Game.surfaceDiameter) {
                    this.surface.transitionDown(this);
                } else {
                    this.y++;
                }
                break;
            case Left:
                if (this.x == 0) {
                    this.surface.transitionLeft(this);
                } else {
                    this.x--;
                }
                break;
            case Right:
                if (this.x == Game.surfaceDiameter) {
                    this.surface.transitionRight(this);
                } else {
                    this.x++;
                }
                break;
        }
    }
}
