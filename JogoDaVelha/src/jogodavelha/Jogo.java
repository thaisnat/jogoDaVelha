package jogodavelha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Jogo extends JFrame implements ActionListener{
    
    Icon iCara, iPc;
    JButton btn[][] = new JButton[3][3];
    JPanel gamePanel = new JPanel(new GridLayout(3,3,5,5));
    Font fonte = new Font("verdana", Font.BOLD, 26);
    boolean vezUsuario;
    Coordenadas c = new Coordenadas();;
    int i,j, primeiro;
    int a, b;
    int centroPreenchido = 1;
    JLabel ganhador;
    
    /*
        Essa matriz serve para eu ter o controle de quem jogou:
        1 = Usuario
        2 = Computador
    */
    int quemJogou[][] = new int[3][3];
    
    int vetLinhaU[]  = new int[3];
    int vetLinhaC[]  = new int[3];
    int vetColunaU[] = new int[3];
    int vetColunaC[] = new int[3]; 
    int diagPrincU;
    int diagPrincC;
    int diagSecU;
    int diagSecC;
    int resp;
    
    boolean clicado[][] = new boolean[3][3];
    
    public Jogo(){
        super("Jogo da Velha");
        setLayout(new BorderLayout());
        
        JPanel norte = new JPanel();
        ganhador = new JLabel();
        ganhador.setFont(fonte);
        norte.add(ganhador);
        
        add(BorderLayout.NORTH, norte);

        iniciar();
        criarBotoes();
        
        // Verifica se o primeiro a jogar é o computador
        if(primeiro==2){
            primeiro=0;
            
            btn[0][0].setIcon(iPc);
                    clicado[0][0] = true;
                    quemJogou[0][0] = 2;
                    vetLinhaC[0]++;
                    vetColunaC[0]++;  
                    if(0==0) diagPrincC++;
                    if(0+0==2) diagSecC++;
                        
                    vezUsuario = true;
                    return; 
        }
        
        
    }
    
    public void criarBotoes(){
        
        iCara = new ImageIcon(getClass().getResource("usuario.png"));
        iPc = new ImageIcon(getClass().getResource("computador.png"));
        
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                btn[i][j] = new JButton();
                btn[i][j].setForeground(Color.WHITE);
                btn[i][j].addActionListener(this);
                gamePanel.add(btn[i][j]);
            }
        }
        
        add(BorderLayout.CENTER, gamePanel);
    }
    
    public void iniciar(){

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                clicado[i][j]=false;
                vetLinhaU[i]=0;
                vetLinhaC[i]=0;
                vetColunaU[i]=0;
                vetColunaC[i]=0;
                diagPrincU = 0;
                diagPrincC = 0;
                quemJogou[i][j]=0;
            }
        }
        
        resp = JOptionPane.showConfirmDialog(null, "Deseja ser o primeiro(a) a jogar?");
        if(resp == JOptionPane.YES_OPTION){
            vezUsuario = true;
            primeiro = 1;
        }else if(resp == JOptionPane.NO_OPTION){
            vezUsuario = false;
            primeiro = 2;
        }
    }
    
    public boolean matrizCheia(){
        int soma=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(quemJogou[i][j]!=0){
                soma++;
                }
            }
        }
        
        if(soma==9){
            return true;
        }else{
            return false;
        }
    }
    
    public void computadorJoga(){
        /*
            PRIORIDADES:
            1º Verificar se o tem como o computador ganhar
            2º Verificar se o usuário tem algum 2 preenchido no vetor
        
            POSSIBLIDADES DE JOGADAS SE O USUÁRIO COMEÇAR O JOGO:
            1º Se o usuário colocar sua primeira jogada no meio, o computador
               deve jogar em algum dos cantos de esquina
            2° Se o usuário começar colocando em algum canto de esquina, o computador 
               deve jogar no meio
            3º Quando o usuário jogar em algum canto central, o computador deve
               jogar em algum canto de esquina
        
            POSSIBILIDADES DE JOGADAS SE O COMPUTADOR COMEÇAR O JOGO:
            1º O computador deve começar jogando em qualquer ponta de esquina
            2º Se o usuáro jogar no meio, o computador deve jogar na outra ponta
               de esquina seguindo sua diagonal
        */
        
        // Verifica se a linha do computador ou do usuário tem 2
        for(int i=0; i<3; i++){
            if(vetLinhaC[i]==2){
                for(int j=0; j<3; j++){
                    if(clicado[i][j]==false){
                        clicado[i][j] = true;
                        btn[i][j].setIcon(iPc);
                        quemJogou[i][j] = 2;
                        vetLinhaC[i]++;
                        vetColunaC[j]++;  
                        if(i==j) diagPrincC++;
                        if(i+j==2) diagSecC++;
                        
                        vezUsuario = true;
                        return;
                    }
                }
            }
        }
        
        for(int i=0; i<3; i++){
            if(vetLinhaU[i]==2){
                for(int k=0; k<3; k++){
                    if(clicado[i][k]==false){
                        clicado[i][k] = true;
                        btn[i][k].setIcon(iPc);
                        quemJogou[i][k] = 2;
                        vetLinhaC[i]++;
                        vetColunaC[k]++;  
                        if(i==k) diagPrincC++;
                        if(i+k==2) diagSecC++;
                        
                        vezUsuario = true;
                        return;
                    }
                }
            }
        }
        
        
        // Verifica se a coluna do computador ou do usuário tem 2
        for(int i=0; i<3; i++){
            if(vetColunaC[i]==2){
                for(int j=0; j<3; j++){
                    if(clicado[j][i]==false){
                        clicado[j][i] = true;
                        btn[j][i].setIcon(iPc);
                        quemJogou[j][i] = 2;
                        vetLinhaC[j]++;
                        vetColunaC[i]++;  
                        if(i==j) diagPrincC++;
                        if(i+j==2) diagSecC++;
                        
                        vezUsuario = true;
                        return;
                    }
                }
            }
        }
        
        for(int i=0; i<3; i++){
            if(vetColunaU[i]==2){
                for(int j=0; j<3; j++){
                    if(clicado[j][i]==false){
                        clicado[j][i] = true;
                        btn[j][i].setIcon(iPc);
                        quemJogou[j][i] = 2;
                        vetLinhaC[j]++;
                        vetColunaC[i]++;  
                        if(i==j) diagPrincC++;
                        if(i+j==2) diagSecC++;
                        
                        vezUsuario = true;
                        return;
                    }
                }
            }
        }
        
        //Verifica diagonais principais e secundarias do usuário e do computador      
        if(diagPrincC==2){
            int dP[][] = c.diagPrincipal();
            for(int i=0; i<3; i++){
                a = dP[i][0];
                b = dP[i][1];
                if(!clicado[a][b]){
                    clicado[a][b] = true;
                    btn[a][b].setIcon(iPc);
                    quemJogou[a][b] = 2;
                    vetLinhaC[a]++;
                    vetColunaC[b]++;  
                    if(a==b) diagPrincC++;
                    if(a+b==2) diagSecC++;

                    vezUsuario = true;
                    return;
                }
            }
        }
        
        
        if(diagPrincU==2){
            int dP[][] = c.diagPrincipal();
            for(int i=0; i<3; i++){
                a = dP[i][0];
                b = dP[i][1];
                if(!clicado[a][b]){
                    clicado[a][b] = true;
                    btn[a][b].setIcon(iPc);
                    quemJogou[a][b] = 2;
                    vetLinhaC[a]++;
                    vetColunaC[b]++;  
                    if(a==b) diagPrincC++;
                    if(a+b==2) diagSecC++;

                    vezUsuario = true;
                    return;
                }
            }
        }
        
        if(diagSecC==2){
            int dS[][] = c.diagSecundaria();
            for(int i=0; i<3; i++){
                a = dS[i][0];
                b = dS[i][1];
                if(!clicado[a][b]){
                    clicado[a][b] = true;
                    btn[a][b].setIcon(iPc);
                    quemJogou[a][b] = 2;
                    vetLinhaC[a]++;
                    vetColunaC[b]++;  
                    if(a==b) diagPrincC++;
                    if(a+b==2) diagSecC++;

                    vezUsuario = true;
                    return;
                }
            }
        }
        
        if(diagSecU==2){
            int dS[][] = c.diagSecundaria();
            for(int i=0; i<3; i++){
                a = dS[i][0];
                b = dS[i][1];
                if(!clicado[a][b]){
                    clicado[a][b] = true;
                    btn[a][b].setIcon(iPc);
                    quemJogou[a][b] = 2;
                    vetLinhaC[a]++;
                    vetColunaC[b]++;  
                    if(a==b) diagPrincC++;
                    if(a+b==2) diagSecC++;

                    vezUsuario = true;
                    return;
                }
            }
        }
        
        // Jogadas estratégicas 
        if(quemJogou[0][0]==2 && quemJogou[1][1]==1){
            if(!clicado[2][2]){
                btn[2][2].setIcon(iPc);
                clicado[2][2] = true;
                quemJogou[2][2] = 2;
                vetLinhaC[2]++;
                vetColunaC[2]++;  
                if(2==2) diagPrincC++;
                if(2+2==2) diagSecC++;
                vezUsuario = true;
                return;
            }
            
        }
        
        if(quemJogou[0][0]==1 && quemJogou[1][1]==2 && quemJogou[2][1]==1){
            if(!clicado[2][0]){
                btn[2][0].setIcon(iPc);
                clicado[2][0] = true;
                quemJogou[2][0] = 2;
                vetLinhaC[2]++;
                vetColunaC[0]++;  
                if(2==0) diagPrincC++;
                if(2+0==2) diagSecC++;
                vezUsuario = true;
                return;
            }
        }
        
        if(quemJogou[0][2]==1 && quemJogou[1][1]==2 && quemJogou[2][1]==1){
            if(!clicado[1][2]){
                btn[1][2].setIcon(iPc);
                clicado[1][2] = true;
                quemJogou[1][2] = 2;
                vetLinhaC[1]++;
                vetColunaC[2]++;  
                if(1==2) diagPrincC++;
                if(1+2==2) diagSecC++;
                vezUsuario = true;
                return;
            }
        }
        
        if(quemJogou[1][2]==1 && quemJogou[0][0]==2 && quemJogou[2][0]==1){
            if(!clicado[0][2]){
                btn[0][2].setIcon(iPc);
                clicado[0][2] = true;
                quemJogou[0][2] = 2;
                vetLinhaC[0]++;
                vetColunaC[2]++;  
                if(0==2) diagPrincC++;
                if(0+2==2) diagSecC++;
                vezUsuario = true;
                return;
            }
        }
        
        if(quemJogou[2][1]==1 && quemJogou[0][0]==2 && quemJogou[0][2]==1){
            if(!clicado[1][2]){
                btn[1][2].setIcon(iPc);
                clicado[1][2] = true;
                quemJogou[1][2] = 2;
                vetLinhaC[1]++;
                vetColunaC[2]++;  
                if(1==2) diagPrincC++;
                if(1+2==2) diagSecC++;
                vezUsuario = true;
                return;
            }
            
        }
       
        if(quemJogou[0][2]==2 && quemJogou[1][1]==1){
            if(!clicado[2][0]){
                btn[2][0].setIcon(iPc);
                clicado[2][0] = true;
                quemJogou[2][0] = 2;
                vetLinhaC[2]++;
                vetColunaC[0]++;  
                if(2==0) diagPrincC++;
                if(2+0==2) diagSecC++;
                vezUsuario = true;
                return;
            }
            
        }

        if(quemJogou[2][0]==1 && quemJogou[1][1]==2 & quemJogou[0][2]==1){
            if(!clicado[2][1]){
                btn[2][1].setIcon(iPc);
                clicado[2][1] = true;
                quemJogou[2][1] = 2;
                vetLinhaC[2]++;
                vetColunaC[1]++;  
                if(2==1) diagPrincC++;
                if(2+1==2) diagSecC++;
                vezUsuario = true;
                return;
            }else{
                if(!clicado[0][1]){
                    btn[0][1].setIcon(iPc);
                    clicado[0][1] = true;
                    quemJogou[0][1] = 2;
                    vetLinhaC[0]++;
                    vetColunaC[1]++;  
                    if(1==0) diagPrincC++;
                    if(0+1==2) diagSecC++;
                    vezUsuario = true;
                    return;
                }
            }
        }
        
        // Se o usuário jogar em um canto de esquina e o centro ja foi preenchido ele deve jogar em um canto central
        
        int cCs[][] = c.cantosCentrais();
        int c1, c2;
        if(centroPreenchido==2 && (clicado[0][0] || clicado[0][2] || clicado[2][2] || clicado[2][0])){
            for(int i=0; i<4; i++){
                c1 = cCs[i][0];
                c2 = cCs[i][1];

                if(!clicado[c1][c2]){
                    btn[c1][c2].setIcon(iPc);
                    clicado[c1][c2] = true;
                    quemJogou[c1][c2] = 2;
                    vetLinhaC[c1]++;
                    vetColunaC[c2]++;  
                    if(c1==c2) diagPrincC++;
                    if(c1+c2==2) diagSecC++;
                    vezUsuario = true;
                    return; 
                }
            }
        }
        
        //Se o usuário colocar sua primeira jogada no meio, o computador deve jogar em algum dos cantos de esquina
        if(clicado[1][1]){
            int cE[][] = c.cantosDeEsquina();
            for(int i=0; i<4; i++){
                a = cE[i][0];
                b = cE[i][1];
                
                if(clicado[a][b]==false){
                    btn[a][b].setIcon(iPc);
                    clicado[a][b] = true;
                    quemJogou[a][b] = 2;
                    vetLinhaC[a]++;
                    vetColunaC[b]++;  
                    if(a==b) diagPrincC++;
                    if(a+b==2) diagSecC++;
                        
                    vezUsuario = true;
                    return;
                }
            }
        }
        
        //Se o usuário começar colocando em algum canto de esquina, o computador deve jogar no meio
        
        int cE[][] = c.cantosDeEsquina();
        for(int i=0; i<4; i++){
            a = cE[i][0];
            b = cE[i][1];
            if(clicado[a][b]==true && centroPreenchido==1){
                if(!clicado[1][1]){
                    btn[1][1].setIcon(iPc);
                    clicado[1][1] = true;
                    quemJogou[1][1] = 2;
                    vetLinhaC[1]++;
                    vetColunaC[1]++;  
                    if(1==1) diagPrincC++;
                    if(1+1==2) diagSecC++;
                    vezUsuario = true;
                    centroPreenchido = 2;
                    return; 
                }
            }
        }

        //Quando o usuário jogar em algum canto central, o computador deve jogar em algum canto de esquina 
        Coordenadas cc = new Coordenadas();
        int centro[][] = cc.cantosCentrais();
        int x=0, y=2;
        for(int i=0; i<4; i++){
            int a = centro[i][0];
            int b = centro[i][1];
            if(clicado[a][b]){
               while(x<=2 && y>=0){
                   if(clicado[x][x]==false){
                    btn[x][x].setIcon(iPc);
                    clicado[x][x] = true;
                    quemJogou[x][x] = 2;
                    vetLinhaC[x]++;
                    vetColunaC[x]++;  
                    if(x==x) diagPrincC++;
                    if(x+x==2) diagSecC++;
                    vezUsuario = true;
                    return;
                }
                
                if(clicado[x][y]==false){
                    btn[x][y].setIcon(iPc);
                    clicado[x][y] = true;
                    quemJogou[x][y] = 2;
                    vetLinhaC[x]++;
                    vetColunaC[y]++;  
                    if(x==y) diagPrincC++;
                    if(x+y==2) diagSecC++;
                    vezUsuario = true;
                    return;
                }
                
                x+=2;
                y-=2;
               }
            }
        }
        
        if(quemJogou[0][0]==2 & quemJogou[1][1]==1){
            if(!clicado[2][2]){
                btn[2][2].setIcon(iPc);
                clicado[2][2] = true;
                quemJogou[2][2] = 2;
                vetLinhaC[2]++;
                vetColunaC[2]++;  
                if(2==2) diagPrincC++;
                if(2+2==2) diagSecC++;
                vezUsuario = true;
                return;
            }
            
        }

    }
    
    
    /* Retorna:
        1 se o usuario ganhou
        2 se o computador ganhou
        0 se ainda não tem ganhador
    */
    public int verificaGanhador(){  
        
        // Verifica coluna
        for(int i=0; i<3; i++){
            if(vetColunaU[i]==3)
                return 1;
            else if(vetColunaC[i]==3){
                return 2;
            }
        }
        
        // Verifica linha
        for(int i=0; i<3; i++){
            if(vetLinhaU[i]==3)
                return 1;
            else if(vetLinhaC[i]==3){
                return 2;
            }
        }
        
        // Verifica diagonal principal
        for(int i=0; i<3; i++){
            if(diagPrincU==3)
                return 1;
            else if(diagPrincC==3){
                return 2;
            }
        }
        
        // Verifica diagonal secundaria
        for(int i=0; i<3; i++){
            if(diagSecU==3)
                return 1;
            else if(diagSecC==3){
                return 2;
            }
        }
        
        return 0;
    }
    
    public static void main (String[] args){
        Jogo j = new Jogo();
        
        j.setSize(550, 580);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(e.getSource()==btn[i][j]){
   
                    if(!clicado[i][j] && verificaGanhador()==0){
                        if(vezUsuario){
                            clicado[i][j] = true;
                            btn[i][j].setIcon(iCara);
                            vezUsuario = false;
                            vetLinhaU[i]++;
                            vetColunaU[j]++;
                            
                            if(i==j) diagPrincU++;
                            if(i+j==2) diagSecU++;
                            
                            quemJogou[i][j]=1;
                            
                        }if(!vezUsuario && verificaGanhador()==0){
                            computadorJoga();
                        }  
                    }
                    
                    if(verificaGanhador()==1){
                        ganhador.setText("Usuário ganhou!");
                        ganhador.setForeground(Color.red);
                        return;
                    }else if(verificaGanhador()==2){
                        ganhador.setForeground(Color.blue);
                        ganhador.setText("O Computador ganhou!");
                        return;
                    }
                    
                    if(matrizCheia()){
                        ganhador.setText("Empate!");
                        ganhador.setForeground(Color.orange);
                    }
                }
            }
        }
    }

}
