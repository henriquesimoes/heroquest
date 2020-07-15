package br.unicamp.ic.mc322.heroquest.map.objects.fixed;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.HiddenObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Trap extends FixedObject implements HiddenObject {
    private boolean armed, discovered;

    public Trap(){
        armed = true;
        discovered = false;
    }

    @Override
    public void interact(Walker agent) {
        if(armed){
            armed = false;
            discovered = true;
            agent.defendsPhysicalSkill(1);
        }
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return true;
    }

    @Override
    public String getRepresentationOnMenu() {
        return null;
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public boolean isArmed() {
        return armed;
    }

    public void discover() {
        discovered = true;
    }
}
