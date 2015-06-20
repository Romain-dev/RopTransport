
public class Main {

	private static String NORD_WEST = "Nord West";
	private static String VOGEL = "Vogel";
	static float sumCol = 0;
	static float sumLine = 0;
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
		
		if (verifMatrice()==true)
		{	
			afficheMatriceCouts();
			afficheMatriceCalculs();
		}
		else
		{
			modifMat();
		}
		
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
	
	//Test d'égalité ligne/Colonne
		public static boolean verifMatrice()
		{
			boolean res =true;
			int nbLigne =matriceCalculs.length;
			int nbcol =matriceCalculs[0].length;
			
			//Total de la derniere colonne
			for (int i=0; i < nbLigne-1; i++)
			{
				sumCol += matriceCalculs[i][nbcol-1];
			}
			//Vérif colonne
			System.out.print("Total colonne : "+ sumCol+ "\n");
			
			
			//Total derniere ligne
			for (int j=0; j < nbcol-1; j++)
			{
				sumLine += matriceCalculs[nbLigne-1][j];
			}
			
			//Vérif ligne
			System.out.print("Total Ligne: " + sumLine+ "\n");
			
			
			if (sumCol-sumLine !=0)
			{
				res = false;
			}
			
			return res;
		}

		public static void modifMat()
		{
			if(sumCol-sumLine > 0 )
			{
				//Modif Header
				infoTop = addHeader(infoTop);
				infoTop[infoTop.length-1] = "AddC";
				//Ajouter une colonne 
				matriceCalculs =addColonne(matriceCalculs);
			}
			else
			{
				//Modif Header
				infoLeft = addHeader(infoLeft);
				infoLeft[infoLeft.length-1] = "AddL";
				matriceCalculs =addLigne(matriceCalculs);
				
			}

		}
		
		//Clone pour modification des headers 
		public static String[] addHeader(String[] Tab) {
			  String[] cloneTab = new String[Tab.length+1];
			  
			  for(int i = 0; i < Tab.length; i++) 
			  {
			   cloneTab[i] = Tab[i];
			  }
			  
			  return cloneTab;
			 }

			//Clone pour ajout de colone
		public static float[][] addColonne(float[][] matrice) {
			  float[][] clone = new float[matrice.length][matrice[0].length+1];
			  
			  for(int i = 0; i < matrice.length; i++) 
			  {
			   for(int j = 0; j < matrice[0].length; j++) 
			   {
				   if (j== matrice[0].length-1)
				   {
					   clone[i][j] = 0;
					   if(i==matrice.length-1)
					   {
						   clone[i][j] = sumCol-sumLine;
					   }
					   clone[i][j+1] = matrice[i][j];
				   }   
				   else
				   {
					   clone[i][j] = matrice[i][j];
				   }
			   }
			  }
			  return clone;
			 }

		//Clone pour ajout de ligne
		public static float[][] addLigne(float[][] matrice) {
		  float[][] clone = new float[matrice.length+1][matrice[0].length];
		  
		  for(int i = 0; i < matrice.length; i++) 
		  {
		   for(int j = 0; j < matrice[0].length; j++) 
		   {
			   if (i== matrice.length-1)
			   {
				   clone[i][j] = 0;
				   if(j==matrice[0].length-1)
				   {
					   clone[i][j] = sumLine-sumCol;
				   }
				   clone[i+1][j] = matrice[i][j];
			   }
			   
			   else
			   {
				   clone[i][j] = matrice[i][j];
			   }
		   }
		   
		   
		  }
		  return clone;
		 }
}