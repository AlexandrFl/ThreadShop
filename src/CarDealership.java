import java.util.ArrayList;
import java.util.List;

public class CarDealership {

    List<Audi> audiList = new ArrayList<>();
    Automaker automaker = new Automaker(this);

    public Audi sellAudi() {
        return automaker.sellCar();
    }

    public void distillation() {
        automaker.distillation();
    }

    List<Audi> getAudi() {
        return audiList;
    }

}

