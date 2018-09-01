
package jogodavelha;

public class Coordenadas {
   
    int xy1[][] = new int[3][2]; // Retorna 3 cordenadas (Diagonais)
    int xy2[][] = new int[4][2]; // Retorna 4 cordenadas (Cantos)
    int x1[] = new int[3];
    int y1[] = new int[3];
    int x2[] = new int[4];
    int y2[] = new int[4];
    
    public Coordenadas(){
        for(int i=0; i<3; i++){
            x1[i]=0;
            y1[i]=0;
        }
        
        for(int i=0; i<4; i++){
            x2[i]=0;
            y2[i]=0;
        }
    }
    
    public int[][] diagPrincipal(){
       
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(i==j){
                    x1[i]=i;
                    y1[i]=j;
                }
            }
        }
        
        carregaXY(x1,y1,1);
        
        return xy1;
    }
    
    public int[][] diagSecundaria(){
        
        for(int i=0; i<=2; i++){
            for(int j=2; j>=0; j--){
                if(i+j==2){
                    x1[i]=i;
                    y1[i]=j;
                }
            }
        }
        
        carregaXY(x1,y1,1);
        
        return xy1;
    }
    
    public int[][] cantosDeEsquina(){
        x2[0]=0;
        y2[0]=0;
        x2[1]=0;
        y2[1]=2;
        x2[2]=2;
        y2[2]=0;
        x2[3]=2;
        y2[3]=2;
        
        carregaXY(x2,y2,2);
        return xy2;
    }
    
    public int[][] cantosCentrais(){
        x2[0]=0;
        y2[0]=1;
        x2[1]=1;
        y2[1]=2;
        x2[2]=2;
        y2[2]=1;
        x2[3]=1;
        y2[3]=0;
        
        carregaXY(x2, y2, 2);
        return xy2;
    }
    
    public void carregaXY(int x[], int y[], int op){
        if(op==1){
            for(int i=0; i<3; i++){
                this.xy1[i][0] = x[i];
                this.xy1[i][1] = y[i];
            } 
        }else if(op==2){
            for(int i=0; i<4; i++){
                this.xy2[i][0] = x[i];
                this.xy2[i][1] = y[i];
            } 
        }
        
    }
    
    public static void main(String[] args){
        Coordenadas c = new Coordenadas();
        
        int[][] array = c.cantosCentrais();
        
       for(int i=0; i<4; i++)
           for(int j=0; j<2; j++)
                System.out.println(array[i][j]);

    }
}
