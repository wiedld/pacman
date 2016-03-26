/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiedld.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wiedld
 */
public class Game {

    public final Pacman pac;
    public final List<Ghost> ghosts = new ArrayList<>();
    public final List<PowerPellets> powerPellets = new ArrayList<>();
    public static final int surfaceDiameter = 40;

    public Game() {
        this.pac = new Pacman(Surface.Name.A);
        // color is an enum, since loading graphics per color
        ghosts.add(new Ghost(Surface.Name.A, Ghost.Color.Red, "Shadow"));
        ghosts.add(new Ghost(Surface.Name.A, Ghost.Color.Pink, "Speedy"));
        ghosts.add(new Ghost(Surface.Name.A, Ghost.Color.Green, "Bashful"));
        ghosts.add(new Ghost(Surface.Name.A, Ghost.Color.Yellow, "Pokey"));
        // powerPellets are hacked. Later on, may vary by level.
        for (Surface.Name surface : Surface.Name.values()) {
            powerPellets.add(new PowerPellets(surfaceDiameter - 5, surfaceDiameter - 5, surface));
            powerPellets.add(new PowerPellets(5, 5, surface));
            powerPellets.add(new PowerPellets(surfaceDiameter - 5, 5, surface));
            powerPellets.add(new PowerPellets(5, surfaceDiameter - 5, surface));
        }
    }

    private static class PowerPellets {

        int x;
        int y;
        Surface.Name surface;

        public PowerPellets(int x, int y, Surface.Name surface) {
            this.x = x;
            this.y = y;
            this.surface = surface;
        }
    }

    public void timeInterval() {
        checkEatingBehaviors();

    }

    private void checkEatingBehaviors() {
        // counting for digestion time....in same time interval:
        //      consuming of pellet cannot make pac "super" to instantaneously eat a ghost at that location/time
        // therefore, ghost encounters are checked first
        if (checkCohabitation(ghosts, pac))
            gladiators();
        if (checkCohabitation(powerPellets, pac))
            givePacmanSteriods();
    }

    private boolean checkCohabitation(Iterable<?> crunchies, Pacman pac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void gladiators() {
       // GET THE CORRECT GHOST FOR THAT LOCATION? AND SEE IF EATABLE?
    }

    private void givePacmanSteriods() {
        // sthe Ghosts (alive at the moment) are eatable. Regen ghost default back to not eatable.
        for (Ghost ghost : ghosts) {
            ghost.toggleEatable();
        }
    }

}
