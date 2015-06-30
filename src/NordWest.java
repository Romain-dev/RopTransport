
public class NordWest {

	//Permet de lancer la resolution du probleme avec NordWest
	public static void runNordWest(float[][] matriceCalculs) {
		//Tant qu'il reste des ressources a allouer
		while(isRessourcesAllouables(matriceCalculs)) {
			int[] coordonnesNordWest= getNordWestCoordonnes(matriceCalculs);
			
			//Une resssource ligne passe a 0
			if(matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1] >= matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]])
			{
				matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1] -= matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]];
				matriceCalculs[coordonnesNordWest[0]][coordonnesNordWest[1]] += matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]];
				matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]] = 0;
			}
			//Une resssource colonne passe a 0
			else
			{
				matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]] -= matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1];
				matriceCalculs[coordonnesNordWest[0]][coordonnesNordWest[1]] += matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1];
				matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1] = 0;
				
			}
			
			Main.afficheMatriceCalculs();
		}
	}
	
	//Recherche s'il reste des ressources a affecter
	public static boolean isRessourcesAllouables(float[][] matriceCalculs) {
		//Recherche si tout est à zéro dans la dernière ligne. 
		for(int i = 0; i < matriceCalculs[0].length; i++) {
			if(matriceCalculs[matriceCalculs.length-1][i] > 0)
				return true;
		}
		
		return false;
	}
	
	//Trouve la prochaine coordonnes de l'algo NordWest
	public static int[] getNordWestCoordonnes(float[][] matriceCalculs) {
		int[] result = new int[2];
		
		for(int i = 0; i < matriceCalculs.length-1; i++) {
			for(int j = 0; j < matriceCalculs[0].length-1; j++) {
				if(matriceCalculs[i][j] == 0 && 
				   matriceCalculs[i][matriceCalculs[0].length-1] != 0 &&
				   matriceCalculs[matriceCalculs.length-1][j] != 0) {
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
		
		return result;
	}
}
