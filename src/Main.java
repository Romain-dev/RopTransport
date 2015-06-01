
public class Main {

	private static String NORD_WEST = "Nord West";
	private static String VOGEL = "Vogel";
	
	//NORD_WEST ou VOGEL
	private static String methode = VOGEL;
	
	/*private static String[] infoTop = {"M","B","H","N"};
	private static String[] infoLeft = {"SP","RJ","D "};
	private static float[][] matriceCouts = {{1,5,2,10},
									  {6,3,8,5},
									  {4,3,1,7}};
	private static float[][] matriceCalculs = {{0,0,0,0,60},
									{0,0,0,0,30},
									{0,0,0,0,20},
									{30,50,20,10,0}};*/
	
	private static String[] infoTop = {"U1","U2","U3","U4","Us"};
	private static String[] infoLeft = {"R1","R2","R3","R4"};
	private static float[][] matriceCouts = {{7,5,8,6,0},
		{2,4,50,1,0},
		{9,10,7,2,0},
		{4,7,1,8,0}};
	private static float[][] matriceCalculs = {{0,0,0,0,0,20},
									{0,0,0,0,0,30},
									{0,0,0,0,0,10},
									{0,0,0,0,0,40},
									{25,10,30,10,25,0}};

	public static void main(String[] args) {
		System.out.println("La méthode de calcul choisi est \"" + methode  + "\"");
		
		//TODO ajouter lignes ou colonnes
		afficheMatriceCouts();
		afficheMatriceCalculs();
		
		if(methode == NORD_WEST) {
			NordWest.runNordWest(matriceCalculs);
		}
		else if(methode == VOGEL) {
			Vogel.runVogel(matriceCalculs,matriceCouts);
		}
		
		calculEtAfficheZ();
	}
	
	
	//Affiche la matrice des couts dans la console.
	public static void afficheMatriceCouts() {
		System.out.println();
		System.out.println();
		System.out.println("Matrice des couts");
		System.out.println(); 
		
		//Affichage de l'entète
		System.out.print("     ");
		for(int i = 0; i < infoTop.length; i++)
		{
			System.out.print("  |  "+ infoTop[i]);
		}
		System.out.println(" |");
		
		for(int i = 0; i < infoLeft.length; i++)
		{
			System.out.print("  | "+ infoLeft[i] + " |");
			
			for(int j = 0; j < matriceCouts[i].length; j++)
				System.out.print(" " + matriceCouts[i][j] + " |");
				
			System.out.println();
		}
	}

	//Affiche la matrice des calculs dans la console.
	public static void afficheMatriceCalculs() {
		System.out.println();
		System.out.println();
		System.out.println("Matrice des calculs");
		System.out.println();
		
		//Affichage de l'entète
		System.out.print("     ");
		for(int i = 0; i < infoTop.length; i++)
		{
			System.out.print("  |  "+ infoTop[i]);
		}
		System.out.println(" |");
		
		for(int i = 0; i < infoLeft.length; i++)
		{
			System.out.print("  | "+ infoLeft[i] + " |");
			
			for(int j = 0; j < matriceCalculs[i].length; j++)
				System.out.print(" " + matriceCalculs[i][j] + " |");
				
			System.out.println();
		}
		
		System.out.print("       |");
		for(int i = 0; i < matriceCalculs[0].length; i++)
		{
			System.out.print(" " + matriceCalculs[matriceCalculs.length-1][i] + " |");	
		}
	}
	
	public static void calculEtAfficheZ() {
		float z = 0;
		for(int i = 0; i < matriceCouts.length; i++) {
			for(int j = 0; j < matriceCouts[0].length; j++) {
				z += matriceCouts[i][j] * matriceCalculs[i][j];
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("Z = " + z);
	}
}