public class Planet {
	
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	static double G = 6.67 * Math.pow(10, -11);

	public Planet (double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet (Planet p) {
		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName); 
	}

	public double calcDistance (Planet p) {
		return Math.sqrt((p.xxPos-this.xxPos)*(p.xxPos-this.xxPos) + (p.yyPos-this.yyPos)*(p.yyPos-this.yyPos));
	}

	public double calcForceExertedBy (Planet p) {
		double r = this.calcDistance(p);
		return G * this.mass * p.mass / (r * r);
	}

	public double calcForceExertedByX (Planet p) {
		double r = this.calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		/* if (dx < 0) dx = - dx; */
		return this.calcForceExertedBy(p) * dx / r;
	}

	public double calcForceExertedByY (Planet p) {
		double r = this.calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		/* if (dx < 0) dx = - dx; */
		return this.calcForceExertedBy(p) * dy / r;
	}

	public double calcNetForceExertedByX (Planet [] ps) {
		int N = ps.length;
		double Fx = 0;
		for (Planet element : ps){
			if (this.equals(element)) continue;
			Fx += this.calcForceExertedByX(element);
		}
		return Fx;
	}

	public double calcNetForceExertedByY (Planet [] ps) {
		int N = ps.length;
		double Fy = 0;
		for (Planet element : ps){
			if (this.equals(element)) continue;
			Fy += this.calcForceExertedByY(element);
		}
		return Fy;
	}


	public void update (double dt, double Fx, double Fy) {
		double ax = Fx / this.mass;
		double ay = Fy / this.mass;
		this.xxVel += ax * dt;
		this.yyVel += ay * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	public void draw () {
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);u
	}

}
