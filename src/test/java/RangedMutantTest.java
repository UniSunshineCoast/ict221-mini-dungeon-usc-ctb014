import dungeon.engine.RangedMutant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RangedMutantTest {

    @Test
    void testConstructorAndFields() {
        RangedMutant ranged = new RangedMutant(12, 7);
        assertEquals(12, ranged.getDamage(), "Damage should match constructor input");
        assertEquals(7, ranged.getValue(), "Value should match constructor input");
        assertFalse(ranged.isBlocksMovement(), "RangedMutant should not block movement");
    }

    @Test
    void testGetSymbol() {
        RangedMutant ranged = new RangedMutant(8, 3);
        assertEquals("R", ranged.getSymbol(), "Symbol should be 'R'");
    }

    @Test
    void testGetImagePath() {
        RangedMutant ranged = new RangedMutant(8, 3);
        assertEquals("/RangedMonster.png", ranged.getImagePath(), "Image path should match");
    }

    @Test
    void testSetAndGetPosition() {
        RangedMutant ranged = new RangedMutant(5, 2);
        ranged.setPosition(4, 7);
        assertEquals(4, ranged.getX(), "X position should match");
        assertEquals(7, ranged.getY(), "Y position should match");
    }

    @Test
    void testTryAttackIsRandom50Percent() {
        RangedMutant ranged = new RangedMutant(10, 5);
        int trueCount = 0;
        int falseCount = 0;

        // Run tryAttack 1000 times and expect ~50/50 distribution
        for (int i = 0; i < 1000; i++) {
            if (ranged.tryAttack()) trueCount++;
            else falseCount++;
        }

        // Roughly 40-60% true/false is acceptable for randomness
        double truePercentage = (trueCount / 1000.0) * 100;
        double falsePercentage = (falseCount / 1000.0) * 100;

        assertTrue(truePercentage > 40 && truePercentage < 60, "True percentage not within expected range");
        assertTrue(falsePercentage > 40 && falsePercentage < 60, "False percentage not within expected range");
    }
}
