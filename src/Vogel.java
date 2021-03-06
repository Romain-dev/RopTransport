import java.util.Arrays;


public class Vogel {
	
	private static float[][] matriceDesRegrets;
	private static float[][] matriceInverseDesCouts;
	private static float[][] matriceCalculs;
	private static float[][] matriceCouts;
	
	//Permet de resoudre le probleme avec Vogel
	public static void runVogel(float[][] matriceCalculsv,float[][] matriceCoutsv) {
		matriceCalculs = matriceCalculsv;
		matriceCouts = cloneMatrice(matriceCoutsv);
		calculMatriceInverseDesCouts();
		
		//Tant qu'il reste des ressources
		while(isRessourcesAllouables(matriceCalculs)) {
			nettoyerMatriceCouts();
			calculMatriceInverseDesCouts();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			affecterMatriceRegrets();
			afficheMatriceCouts();
			afficherMatriceRegrets();
			
			int[] coordonnes = new int[2];
			
			//IL y a des regrets
			if(isRegrets())
			{
				coordonnes = getCoordonnes();
			}
			//Plus de regrets, mais il reste des ressources
			else
			{
				float val = 1000000;
				
				for(int i = 0; i < matriceCouts.length; i++) {
					for(int j = 0; j < matriceCouts[0].length; j++) {
						if(matriceCouts[i][j] != 0 && matriceCouts[i][j] < val)
						{
							coordonnes[0] = i;
							coordonnes[1] = j;
							val = matriceCouts[i][j];
						}
					}	
				}
				
				if(val == 1000000.0)
				{
					NordWest.runNordWest(matriceCalculs);
				}
			}

			System.out.println();
			System.out.println("coordonnées:"+ coordonnes[0] + " "+ coordonnes[1]);
			System.out.println();
			
			//Une resssource ligne passe a 0
			if(matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1] >= matriceCalculs[matriceCalculs.length-1][coordonnes[1]])
			{
				matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1] -= matriceCalculs[matriceCalculs.length-1][coordonnes[1]];
				matriceCalculs[coordonnes[0]][coordonnes[1]] += matriceCalculs[matriceCalculs.length-1][coordonnes[1]];
				matriceCalculs[matriceCalculs.length-1][coordonnes[1]] = 0;
			}
			//Une resssource colonne passe a 0
			else
			{
				matriceCalculs[matriceCalculs.length-1][coordonnes[1]] -= matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1];
				matriceCalculs[coordonnes[0]][coordonnes[1]] += matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1];
				matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1] = 0;
				
			}
			
			Main.afficheMatriceCalculs();
		}
	}
	
	//Verifie s'il reste des ressources a allouer
	public static boolean isRessourcesAllouables(float[][] matriceCalculs) {
		
		for(int i = 0; i < matriceCalculs[0].length; i++) {
			if(matriceCalculs[matriceCalculs.length-1][i] > 0)
				return true;
		}
		
		return false;
	}
	
	//Inverse la matrice des couts
	public static void calculMatriceInverseDesCouts() {
		matriceInverseDesCouts = new float[matriceCouts[0].length][matriceCouts.length];
		
		for(int i = 0; i < matriceCouts.length; i++) {
			for(int j = 0; j < matriceCouts[0].length; j++) {
				matriceInverseDesCouts[j][i] = matriceCouts[i][j];
			}	
		}
	}
	
	//Calcul la matrice des regrets
	public static void affecterMatriceRegrets()
	{
		matriceDesRegrets = new float[matriceCalculs.length][matriceCalculs[0].length];
		
		for(int i = 0; i < matriceCouts.length; i++)
		{
			float[] element = copierLigneEtEnleverZero(matriceCouts[i]);
			Arrays.sort(element);
			if(element.length > 1)
			{
				matriceDesRegrets[i][matriceDesRegrets[i].length-1] = element[1] - element[0];
			}
		}
		
		for(int i = 0; i < matriceInverseDesCouts.length; i++)
		{
			float[] element = copierLigneEtEnleverZero(matriceInverseDesCouts[i]);
			Arrays.sort(element);
			if(element.length > 1)
			{
				matriceDesRegrets[matriceDesRegrets.length-1][i] = element[1] - element[0]; 	
			}
		}
	}
	
	//Retourne la ligne en enlevant les 0 (utilise pour calculer la difference entre
	//les deux plus petits elements differents de 0.
	public static float[] copierLigneEtEnleverZero(float[] ligne) {
		int total = 0;
		for(int i = 0; i < ligne.length; i++)
		{
			if(ligne[i] != 0)
			{
				total ++;
			}
		}
		
		float[] element = new float[total];
		int index = 0;
		
		for(int i = 0; i < ligne.length; i++)
		{
			if(ligne[i] != 0)
			{
				element[index] = ligne[i];
				index++;
			}
		}
		
		return element;
	}
	
	public static void afficherMatriceRegrets() {
		System.out.println();
		System.out.println();
		System.out.println("Matrice des regrets");
		System.out.println();
		for(int i = 0; i < matriceDesRegrets.length; i++)
		{
			for(int j = 0; j < matriceDesRegrets[i].length; j++)
				System.out.print(" " + matriceDesRegrets[i][j] + " |");
				
			System.out.println();
		}
	}
	
	//Donne les cordonnes du prochain coup avec Vogel
	public static int[] getCoordonnes() {
		int[] result = new int[2];
		
		int index = -1;
		boolean isVertical = true;
		float valeur = -1;
		
		for(int i = 0; i < matriceDesRegrets.length; i++)
		{
			if(matriceDesRegrets[i][matriceDesRegrets[0].length-1] > valeur)
			{
				valeur = matriceDesRegrets[i][matriceDesRegrets[0].length-1];
				index = i;
			}
		}
		
		for(int i = 0; i < matriceDesRegrets[0].length; i++)
		{
			if(matriceDesRegrets[matriceDesRegrets.length-1][i] > valeur)
			{
				valeur = matriceDesRegrets[matriceDesRegrets.length-1][i];
				index = i;
				isVertical = false;
			}
		}
		
		//Le regret max est sur la ligne
		if(isVertical) 
		{
			int idexx = -1;
			float val = 1000000;
			
			for(int i = 0; i < matriceCouts[0].length;i++)
			{
				if(matriceCouts[index][i] < val && matriceCouts[index][i] != 0)
				{
					idexx = i;
					val = matriceCouts[index][i];
				}
			}
			
			result[0] = index;
			result[1] = idexx;
		}
		//Le regret max est sur la colonne
		else
		{
			int idexx = -1;
			float val = 1000000;
			
			for(int i = 0; i < matriceCouts.length;i++)
			{
				if(matriceCouts[i][index] < val && matriceCouts[i][index] != 0)
				{
					idexx = i;
					val = matriceCouts[i][index];
				}
			}
			
			result[0] = idexx;
			result[1] = index;
		}
		
		
		return result;
	}

	//Affiche la matrice des couts dans la console.
	public static void afficheMatriceCouts() {
		System.out.println();
		System.out.println();
		System.out.println("Matrice des couts");
		System.out.println(); 
		
		for(int i = 0; i < matriceCouts.length; i++)
		{
			for(int j = 0; j < matriceCouts[i].length; j++)
				System.out.print(" " + matriceCouts[i][j] + " |");
				
			System.out.println();
		}
	}
	
	//Vide la matrice des couts
	public static void nettoyerMatriceCouts() {
		for(int i = 0; i < matriceCouts.length;i++)
		{
			if(matriceCalculs[i][matriceCalculs[0].length-1] == 0)
			{
				for(int j = 0; j<matriceCouts[0].length;j++)
				{
					matriceCouts[i][j] = 0;
				}
			}
		}
		
		for(int i = 0; i < matriceCouts[0].length;i++)
		{
			if(matriceCalculs[matriceCalculs.length-1][i] == 0)
			{
				for(int j = 0; j<matriceCouts.length;j++)
				{
					matriceCouts[j][i] = 0;
				}
			}
		}
	}
	
	//Permet de savoir s'il y a des regrets utilisables
	public static boolean isRegrets() {
		for(int i = 0; i < matriceCouts.length;i++)
		{
			if(matriceDesRegrets[i][matriceDesRegrets[0].length-1] != 0)
			{
				return true;
			}
		}
		
		for(int i = 0; i < matriceCouts[0].length;i++)
		{
			if(matriceDesRegrets[matriceDesRegrets.length-1][i] != 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	//Clone la matrice
	public static float[][] cloneMatrice(float[][] matrice) {
		float[][] clone = new float[matrice.length][matrice[0].length];
		
		for(int i = 0; i < matrice.length; i++) {
			for(int j = 0; j < matrice[0].length; j++) {
				clone[i][j] = matrice[i][j];
			}
		}
		
		return clone;
	}
}    	