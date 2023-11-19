import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class RainyDayApplication extends JFrame {
    private JLabel cloudLabel;
    private JLabel rainLabel;
    private JLabel puddleLabel;
    private JButton startButton;
    private JButton stopButton;


    private Thread cloudThread;
    private Thread rainThread;
    private Thread puddleThread;
    private boolean animationRunning;
    private int cloudSize;
    private int rainSize;
    private int puddleSize;

    public RainyDayApplication() {
        setTitle("Rainy Day Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        cloudLabel = new JLabel();
        add(cloudLabel);
        rainLabel = new JLabel();
        add(rainLabel);
        puddleLabel = new JLabel();
        add(puddleLabel);
        // Создание кнопок
        startButton = new JButton("Старт");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });
        add(startButton, BorderLayout.NORTH);

        stopButton = new JButton("Стоп");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAnimation();
            }
        });
        stopButton.setEnabled(false);
        add(stopButton, BorderLayout.SOUTH);

        // Инициализация переменных состояния анимации
        animationRunning = false;
        cloudSize = 100;
        rainSize = 10;
        puddleSize = 50;
    }

    private void startAnimation() {
        if (!animationRunning) {
            animationRunning = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);

            cloudThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (animationRunning) {
                        // Обновление размера облака
                        cloudSize -= 1;

                        // Загрузка изображения облака
                        BufferedImage cloudImage = loadImage("cloud.png");
                        Image scaledCloudImage = cloudImage.getScaledInstance(cloudSize, -1, Image.SCALE_SMOOTH);
                        cloudLabel.setIcon(new ImageIcon(scaledCloudImage));

                        // Перерисовка компонента
                        cloudLabel.setBounds((getWidth() - cloudSize) / 2, 50, cloudSize, cloudSize);

                        // Задержка для анимации
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            cloudThread.start();

            puddleThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (animationRunning) {
                        // Обновление размера лужи
                        puddleSize += 1;

                        // Загрузка изображения лужи
                        BufferedImage puddleImage = loadImage("puddle.png");
                        Image scaledPuddleImage = puddleImage.getScaledInstance(puddleSize, -1, Image.SCALE_SMOOTH);
                        puddleLabel.setIcon(new ImageIcon(scaledPuddleImage));

                        // Перерисовка компонента
                        puddleLabel.setBounds((getWidth() - puddleSize) / 2, 200, puddleSize, puddleSize);

                        // Задержка для анимации
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            puddleThread.start();


            rainThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (animationRunning) {
                        // Создание и настройка размера дождя
                        rainSize += 5;

                        // Загрузка изображения дождя
                        BufferedImage rainImage = loadImage("rain.png");
                        Image scaledRainImage = rainImage.getScaledInstance(rainSize, -1, Image.SCALE_SMOOTH);
                        rainLabel.setIcon(new ImageIcon(scaledRainImage));

                        // Перерисовка компонента
                        rainLabel.setBounds((getWidth() - rainSize) / 2, 100, rainSize, rainSize);

                        // Задержка для анимации
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            rainThread.start();

        }
    }

    private void stopAnimation() {
        if (animationRunning) {
            animationRunning = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            URL imageUrl = getClass().getResource(imagePath);
            return ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RainyDayApplication app = new RainyDayApplication();
                app.setVisible(true);
            }
        });
    }
}
