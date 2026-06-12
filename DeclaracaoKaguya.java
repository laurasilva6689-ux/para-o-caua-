import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DeclaracaoKaguya {
    private static final LocalDateTime INICIO_CONVERSA = LocalDateTime.of(2026, 5, 12, 12, 0);
    private static Clip clipMusica;

    public static void main(String[] args) {
        LocalDateTime agora = LocalDateTime.now();
        long dias = ChronoUnit.DAYS.between(INICIO_CONVERSA, agora);
        long horas = ChronoUnit.HOURS.between(INICIO_CONVERSA, agora) % 24;
        long minutos = ChronoUnit.MINUTES.between(INICIO_CONVERSA, agora) % 60;

        String relatorio = "<html><body style='width: 340px; font-family: Georgia; color: white; text-align: center;'>"
                + "<h2 style='color: #FF4444;'>👑 DIÁRIO DE BORDO: CONSELHO ESTUDANTIL 👑</h2>"
                + "<hr>"
                + "<b>Estrategista:</b> Laura<br>"
                + "<b>Alvo principal:</b> Cauã<br><br>"
                + "<i>Narrador: 'Após exatos " + dias + " dias, " + horas + " horas e " + minutos + " minutos de sintonia... "
                + "Marcados por conversas profundas de madrugada e aquele carinho bom ao meio-dia, "
                + "Laura e Cauã venceram a primeira grande fase demonstrando um esforço mútuo admirável!'</i><br><br>"
                + "<b>Status:</b> Barreiras quebradas com sucesso. Pronto para o veredito do mês?"
                + "</body></html>";

        UIManager.put("OptionPane.background", new Color(25, 15, 20));
        UIManager.put("Panel.background", new Color(25, 15, 20));
        JOptionPane.showMessageDialog(null, relatorio, "Estratégia de Laura", JOptionPane.PLAIN_MESSAGE);

        tocarMusica();

        JFrame janela = new JFrame("Avançando Etapas: Laura & Cauã");
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        janela.setUndecorated(true); 
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int larg = screenSize.width;
        int alt = screenSize.height;

        JLabel fundo = new JLabel();
        fundo.setBounds(0, 0, larg, alt);
        URL imgUrl = DeclaracaoKaguya.class.getResource("kaguya.png");
        if (imgUrl != null) {
            ImageIcon iconOriginal = new ImageIcon(imgUrl);
            Image imgEscalada = iconOriginal.getImage().getScaledInstance(larg, alt, Image.SCALE_SMOOTH);
            fundo.setIcon(new ImageIcon(imgEscalada));
        } else {
            fundo.setBackground(new Color(20, 10, 15));
            fundo.setOpaque(true);
        }

        JPanel painelStatus = new JPanel();
        painelStatus.setBounds((larg - 800) / 2, 60, 800, 240);
        painelStatus.setBackground(new Color(35, 10, 15, 230)); 
        painelStatus.setLayout(new GridLayout(3, 1, 15, 15)); 

        JLabel titulo = new JLabel("GUERRA PSICOLÓGICA: O AMOR ESTÁ VENCENDO", SwingConstants.CENTER);
        titulo.setFont(new Font("Impact", Font.PLAIN, 28));
        titulo.setForeground(new Color(255, 40, 70));

        JLabel subtitulo = new JLabel("Resultados de 1 Mês: Honrando cada segundo juntos e avançando passo a passo.", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.BOLD, 15));
        subtitulo.setForeground(new Color(240, 240, 240)); 

        JLabel pergunta = new JLabel("Cauã, você aceita continuar vencendo todas as próximas etapas do meu lado?", SwingConstants.CENTER);
        pergunta.setFont(new Font("Georgia", Font.BOLD, 20));
        pergunta.setForeground(Color.WHITE);

        painelStatus.add(titulo);
        painelStatus.add(subtitulo);
        painelStatus.add(pergunta);

        Timer timerNeon = new Timer(500, new ActionListener() {
            private boolean alternarCor = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alternarCor) {
                    painelStatus.setBorder(BorderFactory.createLineBorder(new Color(255, 30, 100), 4)); 
                } else {
                    painelStatus.setBorder(BorderFactory.createLineBorder(new Color(220, 0, 0), 4));   
                }
                alternarCor = !alternarCor;
            }
        });
        timerNeon.start();

        JButton btnSim = new JButton("COM CERTEZA! ❤️");
        btnSim.setBounds((larg / 2) - 260, alt - 180, 240, 55);
        btnSim.setBackground(new Color(220, 40, 60));
        btnSim.setForeground(Color.WHITE);
        btnSim.setFont(new Font("Arial", Font.BOLD, 18));
        btnSim.setFocusPainted(false);
        btnSim.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JButton btnNao = new JButton("Parar por aqui...");
        btnNao.setBounds((larg / 2) + 20, alt - 180, 240, 55);
        btnNao.setBackground(new Color(50, 50, 50));
        btnNao.setForeground(Color.LIGHT_GRAY);
        btnNao.setFont(new Font("Arial", Font.BOLD, 16));
        btnNao.setFocusPainted(false);

        Timer timerAnimacao = new Timer(400, new ActionListener() {
            private boolean crescer = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (crescer) {
                    btnSim.setBounds((larg / 2) - 265, alt - 185, 250, 65);
                    btnSim.setFont(new Font("Arial", Font.BOLD, 20));
                } else {
                    btnSim.setBounds((larg / 2) - 260, alt - 180, 240, 55);
                    btnSim.setFont(new Font("Arial", Font.BOLD, 18));
                }
                crescer = !crescer;
            }
        });
        timerAnimacao.start();

        btnNao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int maxX = janela.getWidth() - btnNao.getWidth() - 60;
                int maxY = janela.getHeight() - btnNao.getHeight() - 60;
                
                int randomX = (int) (Math.random() * (maxX - 60) + 60);
                int randomY = (int) (Math.random() * (maxY - 340) + 340); 
                
                btnNao.setLocation(randomX, randomY);
            }
        });

        btnSim.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(janela, 
                "<html><center><font color='white'>Narrador: 'Cauã, você tem certeza absoluta de que seu orgulho caiu a zero<br>e que aceita esse compromisso de carinho eterno com a Laura?'</font></center></html>", 
                "CONFIRMAÇÃO DO CONSELHO", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.PLAIN_MESSAGE);
                
            if (resposta == JOptionPane.YES_OPTION) {
                timerAnimacao.stop();
                timerNeon.stop();
                
                String vitoriaFinal = "<html><body style='width: 360px; font-family: Georgia; text-align: center; color: white;'>"
                        + "<h1 style='color: #FF3366;'>💘 DECISÃO DO CONSELHO 💘</h1>"
                        + "<hr><br>"
                        + "<i>Narrador: 'A sintonia deles é inegável! Cada dia que passa é uma vitória compartilhada.'</i><br><br>"
                        + "<b>Resultado do Nosso Primeiro Mês:</b><br>"
                        + "Mesmo sem um rótulo oficial ainda, meu cuidado, meu carinho e meu coração já são totalmente seus.<br><br>"
                        + "<h2 style='color: #FF3366;'>EU TE AMO, CAUÃ! ❤️</h2>"
                        + "Amo o jeito que a gente se trata e como construímos nossa história passo a passo.<br>"
                        + "<b>Feliz 1 mês de nós dois!</b>"
                        + "</body></html>";
                
                JOptionPane.showMessageDialog(janela, vitoriaFinal, "VENCENDO ETAPAS", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
        });

        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(janela, "Narrador: 'Não há motivos para recuar agora! Continuem avançando!'", "AVANÇAR SEMPRE", JOptionPane.ERROR_MESSAGE);
            }
        });

        janela.add(painelStatus);
        janela.add(btnSim);
        janela.add(btnNao);
        janela.add(fundo);

        janela.setVisible(true);
    }

    private static void tocarMusica() {
        try {
            URL urlMusica = DeclaracaoKaguya.class.getResource("musica.wav");
            if (urlMusica != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(urlMusica);
                clipMusica = AudioSystem.getClip();
                clipMusica.open(audioStream);
                clipMusica.loop(Clip.LOOP_CONTINUOUSLY); 
                clipMusica.start();
            } else {
                System.out.println("Nota: Adicione o arquivo musica.wav no pacote.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
