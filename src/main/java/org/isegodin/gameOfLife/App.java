package org.isegodin.gameOfLife;

import org.isegodin.gameOfLife.core.Field;
import org.isegodin.gameOfLife.core.Parser;
import org.isegodin.gameOfLife.core.Renderer;
import org.isegodin.gameOfLife.core.LifeSimulator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 * https://en.wikipedia.org/wiki/Cellular_automaton
 *
 * @author i.segodin
 */
public class App {

    public static void main(String[] args) {
        Field field = Parser.parse(
                ClassLoader.getSystemClassLoader().getResourceAsStream("org/isegodin/gameOfLife/patterns/GosperGunWithEater.txt"),
                80, 80);

        Timer timer = new Timer();
        timer.schedule(new Task(field), 0, 500);
    }

    private static class Task extends TimerTask {

        private Field field;

        public Task(Field field) {
            this.field = field;
        }

        @Override
        public void run() {
            long stamp = field.getTimestamp();
            Renderer.render(field, field.getWidth(), field.getHeight());
            field = LifeSimulator.simulate(field);
            if (stamp == field.getTimestamp()) {
                cancel();
                System.out.println();
                System.out.println("All cells died or stalled.");
            }
        }
    }
}
