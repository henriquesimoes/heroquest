package br.unicamp.ic.mc322.heroquest.engine.gui.states.gamerunning;

public enum RenderableObject {
    WIZARD,
    ELF,
    BARBARIAN,
    DWARF,
    COMMON_SKELETON,
    WIZARD_SKELETON,
    GOBLIN,
    WALL,
    DOOR,
    CHEST_CLOSE,
    CHEST_OPEN,
    TRAP_ARMED,
    TRAP_UNARMED,
    FLOOR,
    UNKNOWN;

    public String getImageName() {
        switch (this) {
            case WIZARD: return "wizard_front_f";
            case ELF: return "elf_front_f";
            case BARBARIAN: return "barbarian_front_f";
            case DWARF: return "dwarf_front_f";
            case COMMON_SKELETON: return "skeleton_f";
            case WIZARD_SKELETON: return "skeleton_wizard_f";
            case GOBLIN: return "goblin_f";
            case WALL: return "wall_f";
            case DOOR: return "door_f";
            case CHEST_CLOSE: return "chest_close_f";
            case CHEST_OPEN: return "chest_open_f";
            case TRAP_ARMED: return "trap_armed_f";
            case TRAP_UNARMED: return "trap_unarmed_f";
            case FLOOR: return "floor_f";
            case UNKNOWN: return "unknown_f";
            default:
                return "";
        }
    }
    public int getImageFrames() {
        switch (this) {
            case WIZARD:
            case ELF:
            case BARBARIAN:
            case DWARF:
                return 3;
            case COMMON_SKELETON:
            case WIZARD_SKELETON:
            case GOBLIN:
                return 4;
            case WALL:
            case DOOR:
            case CHEST_CLOSE:
            case CHEST_OPEN:
            case TRAP_ARMED:
            case TRAP_UNARMED:
            case FLOOR:
            case UNKNOWN:
            default:
                return 1;
        }
    }

    public boolean needsToShiftImageUp() {
        switch (this) {
            case WIZARD:
            case ELF:
            case BARBARIAN:
            case DWARF:
            case COMMON_SKELETON:
            case WIZARD_SKELETON:
            case GOBLIN:
            case WALL:
            case DOOR:
                return true;
            default:
                return false;
        }
    }
}
