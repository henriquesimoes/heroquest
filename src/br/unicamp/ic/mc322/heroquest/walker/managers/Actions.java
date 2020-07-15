package br.unicamp.ic.mc322.heroquest.walker.managers;

public enum Actions {
    USE_ITEM {
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
    USE_SKILL {
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
    },
    SEARCH{
        @Override
        public String toString(){
            return "Search hidden objects";
        }
    }
}