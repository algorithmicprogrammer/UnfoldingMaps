package module1;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

/** HelloWorld
  * An application with two maps side-by-side zoomed in on different locations.
  * Author: UC San Diego Coursera Intermediate Programming team
  * @author Algorithmic Programmer
  * Date: August 11, 2023
  * */
public class HelloWorld extends PApplet
{


	private static final long serialVersionUID = 1L;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	

	private static final boolean offline = false;
	
	/** The map we use to display our home town: La Jolla, CA */
	UnfoldingMap map1;
	
	/** The map you will use to display your home town */ 
	UnfoldingMap map2;

	
	public void setup() { //Setting up the applet window to be 800x600
	    size(800, 600);
	    
	
		// This sets the background color for the Applet.  
		this.background(200, 200, 200);
		
		// Select a map provider
		AbstractMapProvider provider = new Google.GoogleTerrainProvider();
		// Set a zoom level
		int zoomLevel = 10;
		
		if (offline) {
			// If you are working offline, you need to use this provider 
			// to work with the maps that are local on your computer.  
			provider = new MBTilesMapProvider(mbTilesString);
			// 3 is the maximum zoom level for working offline
			zoomLevel = 3;
		}
		
		// Create a new UnfoldingMap to be displayed in this window.  
		// The 2nd-5th arguments give the map's x, y, width and height
		// When you create your map we want you to play around with these 
		// arguments to get your second map in the right place.
		// The 6th argument specifies the map provider.  
		// There are several providers built-in.
		// Note if you are working offline you must use the MBTilesMapProvider
		map1 = new UnfoldingMap(this, 50, 50, 200, 300, provider);

		// The next line zooms in and centers the map at 
	    // 32.9 (latitude) and -117.2 (longitude)
	    map1.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
		
		// This line makes the map interactive
		MapUtils.createDefaultEventDispatcher(this, map1);
		
		
		map2 = new UnfoldingMap(this, 250, 50, 200, 300, provider);
		map2.zoomAndPanTo(zoomLevel, new Location(32.6f, -115.4f));
		MapUtils.createDefaultEventDispatcher(this, map2);

	}

	/** Draw the Applet window.  */
	public void draw() {
		map1.draw();
		map2.draw();
	}
	

	
}
