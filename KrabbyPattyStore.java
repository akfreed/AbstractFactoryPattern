import java.util.ArrayList;
import java.util.Arrays;

// ==================================================================

interface Bun {
    public abstract String GetDescription();
}

class SeaweedSeedBun implements Bun {
    @Override
    public String GetDescription() { return "fluffy seaweed-seed bun"; }
}

class PrettyBun implements Bun {
    @Override
    public String GetDescription() { return "dyed seaweed-seed bun"; }
}

class NastyBun implements Bun {
    @Override
    public String GetDescription() { return "bun dipped in toilet and dried with gym sock"; }
}

// ------------------------------------------------------------------

interface MeatPatty {
    public abstract String GetDescription();
}

class KrabbyMeatPatty implements MeatPatty {
    @Override
    public String GetDescription() { return "krabby patty made with secret ingredient"; }
}

class PrettyMeatPatty implements MeatPatty {
    @Override
    public String GetDescription() { return "dyed patty"; }
}

class NastyMeatPatty implements MeatPatty {
    @Override
    public String GetDescription() { return "meat patty dipped in toilet and dried with gym sock"; }
}

// ------------------------------------------------------------------

interface Cheese {
    public abstract String GetDescription();
}

class UnderseaCheese implements Cheese {
    @Override
    public String GetDescription() { return "undersea cheese"; }
}

class PrettyCheese implements Cheese {
    @Override
    public String GetDescription() { return "dyed cheese"; }
}

class NastyCheese implements Cheese {
    @Override
    public String GetDescription() { return "cheese dipped in toilet and dried with gym sock"; }
}

// ------------------------------------------------------------------

interface Condiment {
    public abstract String GetDescription();
}

class Ketchup implements Condiment {
    @Override
    public String GetDescription() { return "krusty ketchup"; }
}

class PrettyKetchup implements Condiment {
    @Override
    public String GetDescription() { return "dyed ketchup"; }
}

class Mustard implements Condiment {
    @Override
    public String GetDescription() { return "maritime mustard"; }
}

class PrettyMustard implements Condiment {
    @Override
    public String GetDescription() { return "dyed mustard"; }
}

class JellyfishJelly implements Condiment {
    @Override
    public String GetDescription() { return "jellyfish jelly"; }
}

class Seahorseradish implements Condiment {
    @Override
    public String GetDescription() { return "seahorseradish"; }
}

class VolcanoSauce implements Condiment {
    @Override
    public String GetDescription() { return "volcano sauce"; }
}

class ToenailClippings implements Condiment {
    @Override
    public String GetDescription() { return "toenail clippings"; }
}

// ==================================================================

interface IngredientFactory {
    public Bun createBun();
    public MeatPatty createPatty();
    public Cheese createCheese();
    public ArrayList<Condiment> createCondiments();
}

class OgIngredientFactory implements IngredientFactory {
    @Override
    public Bun createBun() {
        return new SeaweedSeedBun();
    }

    @Override
    public MeatPatty createPatty() {
        return new KrabbyMeatPatty();
    }

    @Override
    public Cheese createCheese() {
        return new UnderseaCheese();
    }

    @Override
    public ArrayList<Condiment> createCondiments() {
        return new ArrayList<Condiment>(Arrays.asList(
                new Ketchup(),
                new Mustard()
        ));
    }
}

class PrettyIngredientFactory implements IngredientFactory {
    @Override
    public Bun createBun() {
        return new PrettyBun();
    }

    @Override
    public MeatPatty createPatty() {
        return new PrettyMeatPatty();
    }

    @Override
    public Cheese createCheese() {
        return new PrettyCheese();
    }

    @Override
    public ArrayList<Condiment> createCondiments() {
        return new ArrayList<Condiment>(Arrays.asList(
            new PrettyKetchup(),
            new PrettyMustard()
        ));
    }
}

class NastyIngredientFactory implements IngredientFactory {
    @Override
    public Bun createBun() {
        return new NastyBun();
    }

    @Override
    public MeatPatty createPatty() {
        return new NastyMeatPatty();
    }

    @Override
    public Cheese createCheese() {
        return new NastyCheese();
    }

    @Override
    public ArrayList<Condiment> createCondiments() {
        return new ArrayList<Condiment>(Arrays.asList(
            new VolcanoSauce(),
            new Seahorseradish(),
            new ToenailClippings()
        ));
    }
}

// ==================================================================

abstract class Burger {
    protected String m_name;
    protected IngredientFactory m_ingredientFactory;

    public void setName(String name) {
        m_name = name;
    }

    public String getName() {
        return m_name;
    }

    public abstract void assemble();

    public abstract void printIngredients();
}

class KrabbyPatty extends Burger {
    private Bun m_bun;       // It's a Bun instead of a String.
    private MeatPatty m_patty;   // It's a Patty instead of a String.
    private Cheese m_cheese; // It's a Cheese instead of a String.
    private ArrayList<Condiment> m_condiments = new ArrayList<>();

    public KrabbyPatty(IngredientFactory ingredientFactory) {
        m_ingredientFactory = ingredientFactory;
    }

    @Override
    public void assemble() {
        System.out.println("Assembling " + getName());
        m_bun = m_ingredientFactory.createBun();
        m_patty = m_ingredientFactory.createPatty();
        m_cheese = m_ingredientFactory.createCheese();
        m_condiments = m_ingredientFactory.createCondiments();
    }

    @Override
    public void printIngredients() {
        System.out.println("    bottom " + m_bun.GetDescription());
        System.out.println("    " + m_patty.GetDescription());
        System.out.println("    " + m_cheese.GetDescription());
        for (var condiment : m_condiments) {
            System.out.println("    " + condiment.GetDescription());
        }
        System.out.println("    top " + m_bun.GetDescription());
    }
}

class AllBunPatty extends Burger {
    private Bun m_bun;       // It's a Bun instead of a String.
    private Bun m_middleBun;

    public AllBunPatty(IngredientFactory ingredientFactory) {
        m_ingredientFactory = ingredientFactory;
    }

    @Override
    public void assemble() {
        System.out.println("Assembling " + getName());
        m_bun = m_ingredientFactory.createBun();
        m_middleBun = m_ingredientFactory.createBun();
    }

    @Override
    public void printIngredients() {
        System.out.println("    bottom " + m_bun.GetDescription());
        System.out.println("    another " + m_middleBun.GetDescription());
        System.out.println("    top " + m_bun.GetDescription());
    }
}

// ==================================================================

abstract class KrustyKrabRestaurant {
    protected abstract Burger createKrabbyPatty(String item);

    public Burger orderKrabbyPatty(String item) {
        Burger kp = createKrabbyPatty(item);
        kp.assemble();
        return kp;
    }
}

class OgKrustyKrabRestaurant extends KrustyKrabRestaurant {
    @Override
    protected Burger createKrabbyPatty(String item) {
        Burger ret = null;
        IngredientFactory ingredientFactory = new OgIngredientFactory();

        if (item.equals("KrabbyPatty")) {
            ret = new KrabbyPatty(ingredientFactory);
            ret.setName("krabby patty");
        } else if (item.equals("AllBunPatty")) {
            ret = new AllBunPatty(ingredientFactory);
            ret.setName("all-bun patty");
        }

        return ret;
    }
}

class PrettyKrabRestaurant extends KrustyKrabRestaurant {
    @Override
    protected Burger createKrabbyPatty(String item) {
        Burger ret = null;
        IngredientFactory ingredientFactory = new PrettyIngredientFactory();

        if (item.equals("KrabbyPatty")) {
            ret = new KrabbyPatty(ingredientFactory);
            ret.setName("pretty patty");
        } else if (item.equals("AllBunPatty")) {
            ret = new AllBunPatty(ingredientFactory);
            ret.setName("pretty all-bun patty");
        }

        return ret;
    }
}

class NastyKrabRestaurant extends KrustyKrabRestaurant {
    @Override
    protected Burger createKrabbyPatty(String item) {
        Burger ret = null;
        IngredientFactory ingredientFactory = new NastyIngredientFactory();

        if (item.equals("KrabbyPatty")) {
            ret = new KrabbyPatty(ingredientFactory);
            ret.setName("nasty patty");
        } else if (item.equals("AllBunPatty")) {
            ret = new AllBunPatty(ingredientFactory);
            ret.setName("nasty all-bun patty");
        }

        return ret;
    }
}
