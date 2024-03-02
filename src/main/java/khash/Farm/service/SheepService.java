package khash.Farm.service;

import khash.Farm.dao.*;
import khash.Farm.Models.Sheep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SheepService {

    @Autowired
    private FarmRepository farm;

    public void AddFarm(Iterable<Sheep> sheeps){
        farm.saveAll(sheeps);
    }

    private static <T> List<T> toList(final Iterable<T> iterable) {
        final List<T> list = new ArrayList<T>();
        for (Iterator<T> i = iterable.iterator(); i.hasNext();) {
            list.add(i.next());// ww w. jav a2  s .  c  om
        }
        return list;
    }

    public List<Sheep> FarmsSheeps(){
        return toList(farm.findAll());
    }

    public List<Sheep> FarmSheeps(int farmNumber){
        List<Sheep> sheeps = FarmsSheeps();
        List<Sheep> result = new ArrayList<Sheep>();
        for (Iterator<Sheep> i = sheeps.iterator(); i.hasNext();){
            Sheep sheep= i.next();
            if(sheep.getFarm() == farmNumber)
                result.add(sheep);
        }
        return result;
    }

}
