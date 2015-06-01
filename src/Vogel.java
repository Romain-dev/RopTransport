import java.util.Arrays;


public class Vogel {
	
	private static float[][] matriceDesRegrets;
	private static float[][] matriceInverseDesCouts;
	private static float[][] matriceCalculs;
	private static float[][] matriceCouts;
	
	public static void runVogel(float[][] matriceCalculsv,float[][] matriceCoutsv) {
		matriceCalculs = matriceCalculsv;
		matriceCouts = matriceCoutsv;
		calculMatriceInverseDesCouts();
		
		while(isRessourcesAllouables(matriceCalculs)) {
			affecterMatriceRegrets();
			afficherMatriceRegrets();
			
			/*int[] coordonnes = getCoordonnes(cloneMatriceCouts);
			
			
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
			
			Main.afficheMatriceCalculs();*/
		}
	}
	
	public static boolean isRessourcesAllouables(float[][] matriceCalculs) {
		
		for(int i = 0; i < matriceCalculs[0].length; i++) {
			if(matriceCalculs[matriceCalculs.length-1][i] > 0)
				return true;
		}
		
		return false;
	}
	
	public static void calculMatriceInverseDesCouts() {
		matriceInverseDesCouts = new float[matriceCouts[0].length][matriceCouts.length];
		
		for(int i = 0; i < matriceCouts.length; i++) {
			for(int j = 0; j < matriceCouts[0].length; j++) {
				matriceInverseDesCouts[j][i] = matriceCouts[i][j];
			}	
		}
	}
	
	public static void affecterMatriceRegrets()
	{
		matriceDesRegrets = new float[matriceCalculs.length][matriceCalculs[0].length];
		
		for(int i = 0; i < matriceCouts.length; i++)
		{
			float[] element = copierLigneEtEnleverZero(matriceCouts[i]);
			Arrays.sort(element);
			if(element.length != 0)
			{
				matriceDesRegrets[i][matriceDesRegrets[i].length-1] = element[1] - element[0];
			}
		}
		
		for(int i = 0; i < matriceInverseDesCouts.length; i++)
		{
			float[] element = copierLigneEtEnleverZero(matriceInverseDesCouts[i]);
			Arrays.sort(element);
			if(element.length != 0)
			{
				matriceDesRegrets[matriceDesRegrets.length-1][i] = element[1] - element[0]; 	
			}
		}
	}
	
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
		
		for(int i = 0; i < total; i++)
		{
			element[i] = ligne[i];
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
}    	