package khash.Farm.Runner;

import khash.Farm.Models.Sheep;
import khash.Farm.service.SheepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private SheepService sheepService;

    @Override
    public void run(String... args) throws Exception {
        System.setProperty("java.awt.headless", "false");
        sheepService.AddFarm(List.of(new Sheep[]{
                new Sheep(2, 55, -1, -1, 1),
                new Sheep(2, 47, -1, -1, 1),
                new Sheep(1, 63, -1, -1, 1),
                new Sheep(2, 92, -1, -1, 1),
                new Sheep(2, 59, -1, -1, 1),
                new Sheep(2, 86, -1, -1, 1),
                new Sheep(2, 44, -1, -1, 1),
                new Sheep(2, 96, -1, -1, 1),
                new Sheep(2, 85, -1, -1, 1),
                new Sheep(2, 61, -1, -1, 1)
        }));

        sheepService.AddFarm(List.of(new Sheep[]{
                new Sheep(2, 85, -1, -1, 2),
                new Sheep(2, 47, -1, -1, 2),
                new Sheep(2, 55, -1, -1, 2),
                new Sheep(2, 86, -1, -1, 2),
                new Sheep(2, 96, -1, -1, 2),
                new Sheep(1, 63, -1, -1, 2),
                new Sheep(2, 44, -1, -1, 2),
                new Sheep(2, 59, -1, -1, 2),
                new Sheep(2, 61, -1, -1, 2),
                new Sheep(2, 92, -1, -1, 2)
        }));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n1) mating in farm1.");
            System.out.println("2) mating in farm2.");
            System.out.println("3) mating in farm3.");
            System.out.println("Please Enter Number: ");


            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    MatingInFarm1();
                    break;
                case 2:
                    MatingInFarm2();
                    break;
                case 3:
                    MatingInFarm3();
                    break;
            }
        }
    }

    private void MatingInFarm1(){
        List<Sheep> farm1 = sheepService.FarmSheeps(1);
        mating(farm1);
        ShowFarm3();
    }

    private void mating(List<Sheep> farm1) {
        List<Sheep> childs = new ArrayList<>();
        Random rand = new Random();
        for (Sheep sheep : farm1) {
            if (sheep.getGender() != 1) continue;
            for (Sheep sheep1 : farm1) {
                if (sheep1.getGender() != 2 ||
                        (sheep.getFatherId() == sheep1.getFatherId() && sheep.getFatherId() != -1) ||
                        (sheep.getMotherId() == sheep1.getMotherId() && sheep.getMotherId() != -1)) continue;

                Sheep child = new Sheep();
                child.setMotherId(sheep1.getId());
                child.setFatherId(sheep.getId());
                child.setGender((long) (rand.nextInt(2) + 1));
                child.setWeight((long) (rand.nextInt(56) + 45));
                child.setFarm(3L);
                childs.add(child);
            }
        }

        sheepService.AddFarm(childs);
    }

    private void MatingInFarm2(){
        List<Sheep> farm2 = sheepService.FarmSheeps(2);
        mating(farm2);
        ShowFarm3();
    }

    private void MatingInFarm3(){
        List<Sheep> farm3 = sheepService.FarmSheeps(3);
        if(farm3.isEmpty()){
            System.out.println("There is no Sheep in farm3");
            return;
        }

        mating(farm3);
        ShowFarm3();
    }

    private void ShowFarm3(){
        List<Sheep> farm3 = sheepService.FarmSheeps(3);
        Sheep[] sheeps = new Sheep[farm3.size()];
        sheeps = farm3.toArray(sheeps);
        JFrame f;
        JTable j;
        f = new JFrame();
        f.setTitle("Farm3");


        String[][] data = new String[sheeps.length][5];

        int counter = 0;
        for(Sheep sheep : sheeps){
            data[counter][0] = "" + sheep.getId();
            data[counter][1] = "" + sheep.getGender();
            data[counter][2] = "" + sheep.getWeight();
            data[counter][3] = "" + sheep.getFatherId();
            data[counter][4] = "" + sheep.getMotherId();
            counter++;
        }

        String[] columnNames = { "id", "gender", "weight", "fatherId", "motherId" };

        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }
}
