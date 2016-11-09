/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */

package aufgabenblatt02.threads.just_for_fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 *
 *
 * @author Moritz Höwer
 * @version 1.0 - 09.11.2016
 */
public class Airport {

    private static final int SIM_TIME_MAX = 60;

    private static final int TIME_TICK_DELAY = 500;

    private static final int MAX_PLANES = 10;

    private static final int MAX_FLIGHTNUMBER = 10000;

    private static final int MAX_FLIGHT_DURATION = 10;

    private static final String[] AIRLINES = {"Lufthansa", "Air Berlin",
            "Emirates", "Ryanair"};

    /**
     * 
     */
    private final List<Runway> runways;

    /**
     * 
     */
    private final String name;

    /**
     * 
     */
    private List<Plane> planes;

    /**
     * 
     */
    private Random random;

    /**
     * 
     */
    private int time;

    public Airport(String name, Runway... rwys) {
        this.name = name;
        runways = Arrays.asList(rwys);
        planes = new ArrayList<>();
        random = new Random(System.currentTimeMillis());
        time = 0;
    }

    public void simulate() {
        // create planes
        for (int i = 0; i < MAX_PLANES; i++) {
            createNewPlane();
        }

        while (time < SIM_TIME_MAX) {
            // print status
            System.out.println("===============================================");
            System.out.println("                 Time: " + time);
            System.out.println("===============================================");
            planes.forEach(System.out::println);
            
            try {
                Thread.sleep(TIME_TICK_DELAY);
            } catch (InterruptedException e) {
                // do nothing
            }
            time++;
            
            Iterator<Plane> it = planes.iterator();
            while(it.hasNext()){
                Plane p = it.next();
                p.update(time);
                if(p.hasLanded()){
                    it.remove();
                }
            }
            
            // create new planes
            for (int i = planes.size(); i < MAX_PLANES; i++) {
                createNewPlane();
            }
        }
        
        planes.forEach(Thread::interrupt);
    }

    private void createNewPlane() {
        String airline = AIRLINES[random.nextInt(AIRLINES.length)];
        int flightNbr = random.nextInt(MAX_FLIGHTNUMBER);
        int flightDuration = random.nextInt(MAX_FLIGHT_DURATION);
        planes.add(new Plane(airline, flightNbr, this, flightDuration, time));
    }

    /**
     * 
     * @param p
     */
    public synchronized void requestLanding(Plane p) {
        Optional<Runway> rwy;
        while (true) {
            rwy = runways.stream().filter(Runway::isAvailiable).findAny();
            if (!rwy.isPresent()) {
                try {
                    p.hold();
                    this.wait();
                } catch (InterruptedException e) {
                    // do nothing
                }
            } else {
                break;
            }
        }
        rwy.get().block(p);
        p.land(rwy.get());
    }

    /**
     * 
     * 
     * @param p
     */
    public synchronized void leftRunway(Plane p) {
        runways.stream().filter(r -> r.isBlockedBy(p)).findAny().get()
                .unblock();
        this.notifyAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }
}
