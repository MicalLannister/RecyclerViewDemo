package com.cq.lannister.recyclerviewdome.model.entity;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.cq.lannister.recyclerviewdome.R;

import java.util.Random;

/**
 * create by lym on 2019/10/25.
 */
public class Cheese {
    public long id;
    public String name;
    @DrawableRes
    public int image;

    public Cheese(long id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public static Cheese generator(){
        Random random = new Random();
        int index = 1;
        int imageIndex = index%IMAGES.length;
        int nameIndex = index%NAMES.length;
        return new Cheese(index,NAMES[nameIndex],IMAGES[imageIndex]);
    }

    public static int[] IMAGES = new int[]{
            R.drawable.cheese_1,
            R.drawable.cheese_2,
            R.drawable.cheese_3,
            R.drawable.cheese_4,
            R.drawable.cheese_5
    };

    public static String[] NAMES = new String[] {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
            "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
            "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
            "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
            "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
            "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
            "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
            "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
            "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore"
    };

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static class DIFF_CALLBACK extends DiffUtil.ItemCallback<Cheese>{

        @Override
        public boolean areItemsTheSame(@NonNull Cheese oldItem, @NonNull Cheese newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Cheese oldItem, @NonNull Cheese newItem) {
            return oldItem.equals(newItem);
        }
    }

}
