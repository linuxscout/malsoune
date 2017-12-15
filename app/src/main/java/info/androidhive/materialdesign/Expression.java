package info.androidhive.materialdesign;

/**
 * Created by condor on 25/04/2016.
 */
public class Expression {

    public int id;
    public String name;
   // private double price;

    public Expression(){
        super();
    }

    public Expression(int id, String name/*, double price*/) {
        super();
        this.id = id;
        this.name = name;
      //  this.price = price;
    }

   @Override
    public String toString() {
        return this.id + ". " + this.name /*+ " [$" + this.price + "]"*/;
    }
}
