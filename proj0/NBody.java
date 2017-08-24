public class NBody {
	
	public static double readRadius (String filename) {
		In planets = new In(filename);
		int N = planets.readInt();
		double R = planets.readDouble();
		return R;
	}

	public static Planet [] readPlanets (String filename) {
		In planets = new In(filename);
		int N = planets.readInt();
		double R = planets.readDouble();
		Planet [] universe = new Planet[N];
		for (int i = 0; i < N; i ++) 
			universe[i] = new Planet(planets.readDouble(), planets.readDouble(), planets.readDouble(), planets.readDouble(), planets.readDouble(), planets.readString());
		return universe;
	}

	public static void main (String [] args) {

		double time = 0;

		double T =  Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double R = NBody.readRadius(filename);
		Planet [] universe = NBody.readPlanets(filename);
		int N = universe.length;

		StdDraw.setXscale(-R, R);
		StdDraw.setYscale(-R, R);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet element : universe)
			element.draw();
	
		StdAudio.play("audio/2001.mid");

		while (time <= T) {
			double [] xForces = new double[5];
			double [] yForces = new double[5];
			for (int i = 0; i < N; i++) {
				xForces[i] = universe[i].calcNetForceExertedByX(universe);
				yForces[i] = universe[i].calcNetForceExertedByY(universe);
			}
			for (int i = 0; i < N; i++) 
				universe[i].update(dt, xForces[i], yForces[i]);
			
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet element : universe)
				element.draw();			

			StdDraw.show(10);

			time += dt;
		}


	
	}


}
