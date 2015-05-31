
public class NordWest {
	
	public static void runNordWest(float[][] matriceCalculs) {
		while(isRessourcesAllouables(matriceCalculs)) {
			int[] coordonnesNordWest= getNordWestCoordonnes(matriceCalculs);
			
			if(matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1] >= matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]])
			{
				matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1] -= matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]];
				matriceCalculs[coordonnesNordWest[0]][coordonnesNordWest[1]] += matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]];
				matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]] = 0;
			}
			else
			{
				matriceCalculs[matriceCalculs.length-1][coordonnesNordWest[1]] -= matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1];
				matriceCalculs[coordonnesNordWest[0]][coordonnesNordWest[1]] += matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1];
				matriceCalculs[coordonnesNordWest[0]][matriceCalculs[0].length-1] = 0;
				
			}
			
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
