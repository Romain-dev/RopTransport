import java.util.Arrays;


public class VogelOld {
	private static float[][] matriceDesRegrets;
	
	public static void runVogel(float[][] matriceCalculs,float[][] matriceCouts) {
		float[][] cloneMatriceCouts = cloneMatrice(matriceCouts);
		
		while(isRessourcesAllouables(matriceCalculs)) {
			affecteMatriceRegrets(matriceCalculs,cloneMatriceCouts);
			afficheMatriceRegrets();
			affecteMatriceRegrets(matriceCalculs,cloneMatriceCouts);
			afficheMatriceCouts(cloneMatriceCouts);
			
			
			int[] coordonnes = getCoordonnes(cloneMatriceCouts);
			
			
			if(matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1] >= matriceCalculs[matriceCalculs.length-1][coordonnes[1]])
			{
				matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1] -= matriceCalculs[matriceCalculs.length-1][coordonnes[1]];
				matriceCalculs[coordonnes[0]][coordonnes[1]] += matriceCalculs[matriceCalculs.length-1][coordonnes[1]];
				matriceCalculs[matriceCalculs.length-1][coordonnes[1]] = 0;
			}
			else
			{
				matriceCalculs[matriceCalculs.length-1][coordonnes[1]] -= matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1];
				matriceCalculs[coordonnes[0]][coordonnes[1]] += matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1];
				matriceCalculs[coordonnes[0]][matriceCalculs[0].length-1] = 0;
				
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Main.afficheMatriceCalculs();
		}
	}   		
	
	public static boolean isRessourcesAllouables(float[][] matriceCalculs) {
		
		for(int i = 0; i < matriceCalculs[0].length; i++) {
			if(matriceCalculs[matriceCalculs.length-1][i] > 0)
				return true;
		}
		
		return false;
	}
	
	public static void affecteMatriceRegrets(float[][] matriceCalculs,float[][] matriceCouts) {
		matriceDesRegrets = new float[matriceCalculs.length][matriceCalculs[0].length];
		
		float[][] cloneMatriceCouts = cloneMatrice(matriceCouts);
		float[][] matriceInverse = new float[matriceCouts[0].length][matriceCouts.length];
		
		for(int i = 0; i < matriceCouts.length; i++)
		{
			float[] cloneAgain = new float[cloneMatriceCouts[i].length-1];
			for(int j = 0; j < cloneMatriceCouts[i].length-1;j++)
			{
					cloneAgain[j] = cloneMatriceCouts[i][j];
			}
			
			Arrays.sort(cloneAgain);
			if(matriceCalculs[i][matriceCalculs[0].length-1] > 0)
			{
				matriceDesRegrets[i][matriceDesRegrets[0].length-1] = cloneAgain[1] - cloneAgain[0];
			}
			else if (matriceCalculs[i][matriceCalculs[0].length-1] == 0) {
				for(int j = 0;j<cloneMatriceCouts[i].length; j++) {
					matriceCouts[i][j] = 1000000;
				}
			}
			for(int j = 0; j < matriceCouts[0].length; j++) {
				matriceInverse[j][i] = matriceCouts[i][j];
			}
		}
		
		for(int i = 0; i < matriceInverse.length; i++)
		{
			float[] cloneAgain = new float[matriceInverse[i].length];
			for(int j = 0; j < matriceInverse[i].length;j++)
			{
					cloneAgain[j] = matriceInverse[i][j];
			}
			Arrays.sort(cloneAgain);
			if(matriceCalculs[matriceCalculs.length-1][i] > 0)
			{
				matriceDesRegrets[matriceDesRegrets.length-1][i] = cloneAgain[1] - cloneAgain[0];
			}
			else if (matriceCalculs[matriceCalculs.length-1][i] == 0) {
				for(int j = 0;j<matriceInverse[i].length; j++) {
					matriceCouts[j][i] = 1000000;
				}
			}
		}
	}
	
	public static float[][] cloneMatrice(float[][] matrice) {
		float[][] clone = new float[matrice.length][matrice[0].length];
		
		for(int i = 0; i < matrice.length; i++) {
			for(int j = 0; j < matrice[0].length; j++) {
				clone[i][j] = matrice[i][j];
			}
		}
		
		return clone;
	}
	
	public static int[] getCoordonnes(float[][] cloneMatriceCouts) {
		int index = -1;
		float valeur = -1;
		boolean isVertical = true;
		for(int i = 0; i < matriceDesRegrets.length; i++) {
			if(matriceDesRegrets[i][matriceDesRegrets[0].length-1] > valeur)
			{
				valeur = matriceDesRegrets[i][matriceDesRegrets[0].length-1];
				index = i;
			}
		}
		
		float[][] matriceInverse = new float[matriceDesRegrets[0].length][matriceDesRegrets.length];
		
		for(int i = 0; i < matriceDesRegrets.length; i++)
		{
			for(int j = 0; j < matriceDesRegrets[0].length; j++) {
				matriceInverse[j][i] = matriceDesRegrets[i][j];
			}
		}
		
		for(int i = 0; i < matriceInverse.length; i++) {
			if(matriceInverse[i][matriceInverse[0].length-1] > valeur)
			{
				valeur = matriceInverse[i][matriceInverse[0].length-1];
				index = i;
				isVertical = false;
			}
		}
		
		if(isVertical) {
			int indexx = -1;
			float val = 1000000;
			for(int i = 0; i < cloneMatriceCouts[0].length; i++)
			{
				if(cloneMatriceCouts[index][i] < val)
				{
					val = cloneMatriceCouts[index][i];
					indexx = i;
				}
			}
			
			int[] result = new int[2];
			result[0] = index;
			result[1] = indexx;
			
			return result;
		}
		else {
			
			matriceInverse = new float[cloneMatriceCouts[0].length][cloneMatriceCouts.length];
			
			for(int i = 0; i < cloneMatriceCouts.length; i++)
			{
				for(int j = 0; j < cloneMatriceCouts[0].length; j++) {
					matriceInverse[j][i] = cloneMatriceCouts[i][j];
				}
			}
			int indexx = -1;
			float val = 1000000;
			for(int i = 0; i < matriceInverse[0].length; i++)
			{
				if(matriceInverse[index][i] < val)
				{
					val = matriceInverse[index][i];
					indexx = i;
				}
			}
			
			int[] result = new int[2];
			result[0] = indexx;
			result[1] = index;
			
			return result;
			
		}
	}
	
	public static void afficheMatriceRegrets() {
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
	
	//Affiche la matrice des couts dans la console.
		public static void afficheMatriceCouts(float[][] matriceCouts) {
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
}    	