/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.model.festival;

import java.util.Map;

/**
 *
 * @author Tom Finlayson
 */
public class BaseFestival implements IFestival {
    
    private FestivalType type;
    
    private String name;
    
    private Location location;
    
    private Map flags;
}
