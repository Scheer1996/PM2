/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 27.11.2016 
 * Aufgabe: Praktikum 3
 */

package aufgabenblatt03;

import java.util.Observable;

import aufgabenblatt03.TrainEvent.Action;

/**
 * A Train Station (part of model)
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 27.11.2016
 */
public class Station extends Observable{
    
    /**
     * stores the current platforms
     */
    private Train[] platforms;
    
    public Station(int platformCount) {
        platforms = new Train[platformCount];
    }
    
    /**
     * Enter the station at specific platform
     * 
     * @param platform the platform at which to enter
     */
    public synchronized void arriveOn(int platform, int driverID){
        if(platform < 0 || platform >= platforms.length){
            throw new IndexOutOfBoundsException("Platform must be between 0 and " + platforms.length);
        }
        
        // initial check with train event
        if(platforms[platform] != null){
            try{
                setChanged();
                notifyObservers(new TrainEvent(driverID, platform, Action.WAIT_ARRIVE));
                wait();
            } catch (InterruptedException e){
                // ignore
            }
        }
        
        while(platforms[platform] != null){
            try{
                // no TrainEvent here
                wait();
            } catch (InterruptedException e){
                // ignore
            }
        }
        
        setChanged();
        notifyObservers(new TrainEvent(driverID, platform, Action.ARRIVE));
        platforms[platform] = new Train();
        notifyAll();
    }
    
    /**
     * Leave the station
     * 
     * @param platform the platform to leave from
     */
    public synchronized void departFrom(int platform, int driverID){
        if(platform < 0 || platform >= platforms.length){
            throw new IndexOutOfBoundsException("Platform must be between 0 and " + platforms.length);
        }
        
        if(platforms[platform] == null){
            try{
                setChanged();
                notifyObservers(new TrainEvent(driverID, platform, Action.WAIT_DEPART));
                wait();
            } catch (InterruptedException e){
                // ignore
            }
        }
        while(platforms[platform] == null){
            try{
                // no TrainEvent here
                wait();
            } catch (InterruptedException e){
                // ignore
            }
        }
        
        setChanged();
        notifyObservers(new TrainEvent(driverID, platform, Action.DEPART));
        platforms[platform] = null;
        notifyAll();
    }

}
