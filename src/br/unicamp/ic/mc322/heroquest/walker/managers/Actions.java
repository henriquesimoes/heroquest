package br.unicamp.ic.mc322.heroquest.walker.managers;

public enum Actions {
    USEITEM{
        @Override
        public String toString(){
            return "Use items";
        }
    },
    INTERACT{
        @Override
        public String toString(){
            return "Interact with objects";
        }
    },
    USESKILL{
        @Override
        public String toString(){
            return "Use skills";
        }
    },
    MOVE{
        @Override
        public String toString(){
            return "Execute movement";
        }
    }
}