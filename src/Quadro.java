import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Quadro extends JFrame implements ActionListener, KeyListener {
    int larguraQuadro = 500;
    int alturaQuadro = 500;

    JLabel jogador;
    int x0Jogador = 250;
    int y0Jogador = 400;
    int alturaJogador = 15;
    int larguraJogador = 15;
    char direcaoJogador;
    int vJogador = 2;

    JLabel chefao;
    int x0Chefao = 200;
    int y0Chefao = 100;
    int alturaChefao = 20;
    int larguraChefao = 15;
    int vChefao = 2;
    Timer timerChefao;
    double pMudanca = 0.2;

    ImageIcon iconeChefao;
    ImageIcon novoChefao;
    Image imagemChefao;
    Image novaImagemChefao;

    ImageIcon icone;
    Image imagem;
    Image novaImagem;

    JLabel bala;
    int x0Bala = 250;
    int y0Bala = 400;
    int alturaBala = 2;
    int larguraBala = 2;
    int vBala = 5;

    JLabel balaChefao;
    int x0BalaChefao = 250;
    int y0BalaChefao = 100;
    int alturaBalaChefao = 4;
    int larguraBalaChefao = 4;
    int vBalaChefao = 15;
    double pDisparo = 0.05;





    Timer timerBala;
    Timer timerBalaChefao;
    Timer timerJogador;
    int vTimer = 25;
    int leftPlayer = 20;
    int rightPlayer = 20;

    ImageIcon novoIconeExplosao;
    Image novaImagemExplosao;
    Image imagemExplosao;

    JLabel mensagem;
    Label mensagemP;
    Quadro () {
        //Jogador
        jogador = new JLabel();
        jogador.setBounds(x0Jogador, y0Jogador, larguraJogador,alturaJogador);
        //jogador.setBackground(Color.blue);
        //jogador.setOpaque(true);
        timerJogador = new Timer(vTimer, this);
        timerJogador.start();

        //Chefão
        chefao = new JLabel();
        chefao.setBounds(x0Chefao, y0Chefao, larguraChefao,alturaChefao);
        //jogador.setBackground(Color.blue);
        //jogador.setOpaque(true);
        timerChefao = new Timer(vTimer, this);
        timerChefao.start();

        //Bala do Jogador
        bala = new JLabel();
        bala.setBounds(x0Jogador + larguraJogador/2, y0Bala, larguraBala,alturaBala);
        bala.setBackground(Color.yellow);
        bala.setOpaque(true);

        //Bala do Chefão
        balaChefao = new JLabel();
        balaChefao.setBounds(x0Chefao + larguraChefao/2, y0Chefao, larguraBalaChefao,alturaBalaChefao);
        balaChefao.setBackground(Color.yellow);
        balaChefao.setOpaque(true);


        //Timer das balas
        timerBala = new Timer(vTimer, this);
        timerBalaChefao = new Timer(vTimer, this);
        timerBalaChefao.start();


        //Icons
        ImageIcon icone = new ImageIcon("nave.png");
        jogador.setIcon(icone);
        imagem = icone.getImage();
        novaImagem = imagem.getScaledInstance(larguraJogador, alturaJogador,Image.SCALE_SMOOTH);
        ImageIcon novoIcone = new ImageIcon(novaImagem);
        jogador.setIcon(novoIcone);

        //Icons chefão
        ImageIcon iconeChefao = new ImageIcon("chefao.png");
        chefao.setIcon(iconeChefao);
        imagemChefao = iconeChefao.getImage();
        novaImagemChefao = imagemChefao.getScaledInstance(larguraChefao, alturaChefao,Image.SCALE_SMOOTH);
        ImageIcon novoIconeChefao = new ImageIcon(novaImagemChefao);
        chefao.setIcon(novoIconeChefao);

        //Icons explode
        ImageIcon explosao = new ImageIcon("explode.png");
        imagemExplosao = explosao.getImage();
        novaImagemExplosao = imagemExplosao.getScaledInstance(2 * larguraJogador, 2 * alturaJogador,Image.SCALE_SMOOTH);
        novoIconeExplosao = new ImageIcon(novaImagemExplosao);


        //Msg
        mensagem = new JLabel();
        mensagem.setForeground(new Color(0x00FF00));
        mensagem.setBounds(250,250,250,250);
        mensagem.setText("Pires o Pior KKK!");
        mensagem.setVisible(false);

        mensagemP = new Label();
        mensagemP.setForeground(new Color(0x00FF00));
        mensagemP.setBounds(150,100,100,100);
        mensagemP.setText("Valeu chefão");
        mensagemP.setVisible(false);

        //Adicionando ao quadro
        this.add(mensagem);
        this.add(mensagemP);
        this.add(jogador);
        this.add(chefao);
        this.addKeyListener(this);
        this.setSize(larguraQuadro,alturaQuadro);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    //Método de ações
    public void actionPerformed(ActionEvent e) {
        //Quando ativar o timer do chefão(Automático);
        if (e.getSource() == timerChefao) {
            //Mudança de direção
            if (Math.random() <= this.pMudanca){
                vChefao = vChefao * (-1);
            }
            //Hora do disparo
            if (Math.random() <= this.pDisparo && balaChefao.getY() >= 500) {
                balaChefao.setLocation(chefao.getX() + larguraChefao / 2, chefao.getY());
                this.add(balaChefao);
            }

            //Posição inicial do chefão
            chefao.setLocation(chefao.getX() - this.vChefao, chefao.getY());
        }

        if(e.getSource() == timerBalaChefao) {
            balaChefao.setLocation(balaChefao.getX(), balaChefao.getY() + this.vBalaChefao);
            if (Math.abs(balaChefao.getX() - (jogador.getX() + larguraJogador/2)) <= larguraJogador/2 && Math.abs(balaChefao.getY() - jogador.getY()) <= alturaJogador/2) {
                jogador.setBounds(jogador.getX(), jogador.getY(),2 * larguraJogador, 2 * alturaJogador);
                jogador.setIcon(novoIconeExplosao);
                mensagem.setVisible(true);
                timerJogador.stop();
                timerChefao.stop();
                timerBala.stop();
                timerBalaChefao.stop();
            }
        }
        if(e.getSource() == timerBala) {
            bala.setLocation(bala.getX(), bala.getY() - this.vBala);
             if (bala.getX() >= chefao.getX() && bala.getX() <= chefao.getX() + larguraChefao && bala.getY() <= chefao.getY() + alturaChefao)  {
                chefao.setBounds(chefao.getX(), chefao.getY(),2 * larguraChefao, 2 * alturaChefao);
                chefao.setIcon(novoIconeExplosao);
                mensagemP.setVisible(true);
                timerJogador.stop();
                timerChefao.stop();
                timerBala.stop();
                timerBalaChefao.stop();
            }
        }
        if (e.getSource() == timerJogador) {
            if (direcaoJogador == 'a') {
                jogador.setLocation(jogador.getX() - this.vJogador, jogador.getY());
            }if (direcaoJogador == 'd') {
                jogador.setLocation(jogador.getX() + this.vJogador, jogador.getY());
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if(e.getKeyChar() == ' ') {
            bala.setLocation(jogador.getX() + larguraJogador / 2, jogador.getY());
            this.add(bala);
            timerBala.start();
        }
        //Mudança de direção
        if(e.getKeyChar() == 'a') {
            direcaoJogador = 'a';
        }
        if(e.getKeyChar() == 'd') {
            direcaoJogador = 'd';
        }



    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
